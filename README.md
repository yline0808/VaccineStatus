# VaccineInformation

## 프로젝트 계획
> 안드로이드에서 api 호출과 코루틴 테스트를 위함

## API
1. 예방접종 현황 api 사용법
+ url : https://infuser.odcloud.kr/oas/docs
    + params
        + namespace=15077756/v1
    + ex : https://infuser.odcloud.kr/oas/docs?namespace=15077756/v1

3. 예방접종 현황 api
+ url : https://api.odcloud.kr/api/15077756/v1/vaccine-stat
    + params
        + page=1
        + perPage=10
        + serviceKey=key
        + cond[baseDate::EQ]=2021-03-11 00:00:00
    + ex : https://api.odcloud.kr/api/15077756/v1/vaccine-stat?serviceKey=key&perPage=20&page=1&cond[baseDate::EQ]=2021-03-11 00:00:00