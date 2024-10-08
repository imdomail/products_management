package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.domain.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShortenUrlController.class)
class ShortenUrlControllerUnitTest {

    @MockBean
    private ShortenUrlService shortenUrlService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("리다이렉트 되어야 한다.")
    void redirectTest() throws Exception {
        String expectedOriginalUrl = "https://linea.kr/product/%EB%A9%94%ED%83%88-%ED%95%91%ED%81%AC-%EB%A9%94%ED%83%88%EA%B7%B8%EB%A6%B0-%EA%B5%AC%EB%A7%A4-%EA%B0%80%EB%8A%A5-%EB%A6%AC%EB%84%A4%EC%95%84-%EB%A9%94%ED%83%88%EC%96%80-%ED%93%A8%EC%96%B4-%EC%BD%94%EB%93%9C-%EB%A6%AC%EB%84%A4%EC%95%84-%ED%99%94%EC%9D%B4%ED%8A%B8-%ED%8F%B4%EB%A6%AC%EC%96%80%EC%97%90-%EB%A9%94%ED%83%88%EC%96%80%EC%9D%84-%ED%95%A9%EC%82%AC%ED%95%9C-%ED%99%94%EC%82%AC%ED%95%9C-%EB%8A%90%EB%82%8C%EC%9D%98-%EB%A9%94%ED%83%88%EC%96%80/4731/category/79/display/1/";

        when(shortenUrlService.findByShortenUrlKeyAndIncreaseCount(any())).thenReturn(new ShortenUrlDTO(expectedOriginalUrl));

        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently());
    }

    @Test
    @DisplayName("없는 키로 리다이렉트 시도하면 404 Not Found 에러를 받는다.")
    void redirectWithInvalidShortenUrlKeyTest() throws Exception {
        String notExistShortenUrlKey = "XXXXXXXX";

        when(shortenUrlService.findByShortenUrlKeyAndIncreaseCount(any())).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/" + notExistShortenUrlKey))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("단축 URL을 생성할 수 있다.")
    void createShortenUrlTest() throws Exception {
        String requestJson = "{\"originalUrl\": \"https://www.ravelry.com/projects/barbelklee/passementerie\"}";
        String responseJson = "{\"shortenUrlKey\":null}";

        when(shortenUrlService.createShortenUrl(any())).thenReturn(new CreateShortenUrlResponseDTO());

        mockMvc.perform(post("/shorten-url")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
                ).andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    @DisplayName("body 값 없이 단축 URL 생성요청을 하면 400 Bad Request 에러를 받는다.")
    void createShortenUrlEmptyBodyTest() throws Exception {
        when(shortenUrlService.createShortenUrl(any())).thenReturn(new CreateShortenUrlResponseDTO());

        mockMvc.perform(post("/shorten-url"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("originalUrl 값 없이 단축 URL 생성요청을 하면 400 Bad Request 에러를 받는다.")
    void createShortenUrlEmptyURLTest() throws Exception {
        when(shortenUrlService.createShortenUrl(any())).thenReturn(new CreateShortenUrlResponseDTO());

        mockMvc.perform(post("/shorten-url")
                .contentType("application/json")
                .content("{ \"originalUrl\": \" \"}")
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("없는 키로 정보 조회하면 404 Not Found 에러를 받는다.")
    void entityNotFoundExceptionTest() throws Exception {
        String notExistShortenUrlKey = "XXXXXXXX";

        when(shortenUrlService.findByShortenUrlKey(any())).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/shorten-url/" + notExistShortenUrlKey))
                .andExpect(status().isNotFound());
    }

}