# 완성 화면
![https://storage.googleapis.com/imdoring-contents/thumbnail/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-08-24%20110350.png](https://storage.googleapis.com/imdoring-contents/thumbnail/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-08-24%20110350.png)

# 목표
주어진 과제를 풀기

# 단축 URL 서비스 요구사항
1. bitly(https://bitly.com/)와 같은 단축 URL 서비스를 만들어야 합니다.
2. 단축된 URL의 키(Key)는 8글자로 생성되어야 합니다. '단축된 URL의 키'는 'https://bit.ly/3onGWgK' 에서 경로(Path)에 해당하는 '3onGWgK'를 의미합니다. bitly에서는 7글자의 키를 사용합니다.
3. 키 생성 알고리즘은 자유롭게 구현하시면 됩니다.
4. 단축된 URL로 사용자가 요청하면 원래의 URL로 리다이렉트(Redirect)되어야 합니다.
5. 원래의 URL로 다시 단축 URL을 생성해도 항상 새로운 단축 URL이 생성되어야 합니다. 이때 기존에 생성되었던 단축 URL도 여전히 동작해야 합니다.
6. 단축된 URL -> 원본 URL로 리다이렉트 될 때마다 카운트가 증가되어야하고, 해당 정보를 확인할 수 있는 API가 있어야합니다.
7. 데이터베이스 없이 컬렉션을 활용하여 데이터를 저장해야합니다.
8. 기능이 정상 동작하는 것을 확인할 수 있는 적절한 테스트 코드가 있어야 합니다.
9. (선택) 해당 서비스를 사용할 수 있는 UI 페이지를 구현해주세요.

# 필요 API
1. 단축 URL 생성 API
2. 단축 URL 리다이렉트 API
3. 단축 URL 정보 조회 API
