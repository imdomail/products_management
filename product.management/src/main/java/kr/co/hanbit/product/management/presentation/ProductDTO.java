package kr.co.hanbit.product.management.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.hanbit.product.management.domain.Product;

import java.util.Objects;

public class ProductDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount;

    public ProductDTO() {}

    public ProductDTO(String name, Integer price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public ProductDTO(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { // 동일하면 동등함
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductDTO productDto = (ProductDTO) o;
        return Objects.equals(id, productDto.id);
    }

    public static Product toEntity(ProductDTO productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getAmount()
        );
    }

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getAmount()
        );
    }
}
