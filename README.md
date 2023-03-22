# Blog Search Project

## 과제 내용
오픈 API를 이용한 블로그 검색 서비스.


## 실행방법
### server
1. java -jar ch-eureka-1.0-SNAPSHOT.jar
2. java -jar ch-gateway-1.0-SNAPSHOT.jar
3. java -jar ch-search-1.0-SNAPSHOT.jar
4. java -jar ch-rank-1.0-SNAPSHOT.jar
5. http://localhost:8900 사용 (gateway)
6. 블로그 검색 API - http://localhost:8900/search-service/search/blog
   인기 검색어 목록 API - http://localhost:8900/rank-service/search/rank


### 1. 블로그 검색 API 
  * Host : http://localhost:8900/search-service
  * GET /search/blog
###  [ Request ]
|Name|  Type   | Required |
|:-------:|:--------:|:---:|
|keyword| String  |    O     |
|sort| String  |    X     |
|page| Integer |    X     |
|size|  Integer  |    X     |

### 2. 인기 검색어 목록 API
* Host : http://localhost:8900/rank-service
* GET /search/rank

<br/>

## 프로젝트 구성환경

#### server
* Spring Boot
    * RESTful API
    * Spring Cloud Eureka
    * Spring Cloud Gateway
    * Build with Gradle
* Java17
* H2 DB
* Junit

<br/>

## 구현과정
* Spring Cloud를 사용하여 부하 분산.
  블로그 검색 기능과 인기 검색어 기능 모듈 분리 처리.
* openapi의 페이지 Info를 가져와 pagination 형태로 가공.
* Global Exception Handling을 통한 Exception 처리 구현.
* kakao openapi 장애시 naver openapi 호출 및 검색 처리 구현.

* 테스트 케이스
    * Controller 레벨 테스트케이스 작성


---
### 과제 기능 요구사항

1. 블로그 검색
- 키워드를 통해 블로그를 검색할 수 있어야 합니다.
- 검색 결과에서 Sorting(정확도순, 최신순) 기능을 지원해야 합니다.
- 검색 결과는 Pagination 형태로 제공해야 합니다.
- 검색 소스는 카카오 API의 키워드로 블로그 검색(https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog)을 활용합니다.
- 추후 카카오 API 이외에 새로운 검색 소스가 추가될 수 있음을 고려해야 합니다.

2. 인기 검색어 목록
- 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공합니다.
- 검색어 별로 검색된 횟수도 함께 표기해 주세요.


### 우대사항

- 프로젝트 구성 추가 요건
  멀티 모듈 구성 및 모듈간 의존성 제약

- Back-end 추가 요건
  트래픽이 많고, 저장되어 있는 데이터가 많음을 염두에 둔 구현
  동시성 이슈가 발생할 수 있는 부분을 염두에 둔 구현 (예시. 키워드 별로 검색된 횟수의 정확도)
  카카오 블로그 검색 API에 장애가 발생한 경우, 네이버 블로그 검색 API를 통해 데이터 제공
  * 네이버 블로그 검색 API: https://developers.naver.com/docs/serviceapi/search/blog/blog.md


### 제약사항

- JAVA 11 이상 또는 Kotlin 사용
- Spring Boot 사용
- Gradle 기반의 프로젝트
- 블로그 검색 API는 서버(백엔드)에서 연동 처리
- DB는 인메모리 DB(예: h2)를 사용하며 DB 컨트롤은 JPA로 구현
- 외부 라이브러리 및 오픈소스 사용 가능 (단, README 파일에 사용한 오픈 소스와 사용 목적을 명시해 주세요)
