package com.ch.search.service.impl;

import com.ch.core.code.CommonCode;
import com.ch.core.code.Errors;
import com.ch.core.exception.BusinessException;
import com.ch.core.persistence.domain.search.SearchHistoryEntity;
import com.ch.core.persistence.repository.search.SearchHistoryRepository;
import com.ch.core.persistence.vo.search.request.SearchRequest;
import com.ch.core.persistence.vo.search.response.SearchHistoryResponse;
import com.ch.core.persistence.vo.search.response.SearchResponse;
import com.ch.core.response.kakao.KakaoResponse;
import com.ch.core.response.naver.NaverResponse;
import com.ch.core.security.response.Response;
import com.ch.search.service.SearchService;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

import static com.ch.core.persistence.domain.search.QSearchHistoryEntity.searchHistoryEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final WebClient webClient;
    //private final Response response;
    //private final JPAQueryFactory jpaQueryFactory;
    private final SearchHistoryRepository searchHistoryRepository;

    @Value("${naver.openapi.url}")
    private String NAVER_URL;
    @Value("${naver.openapi.client.id}")
    private String NAVER_CLIENT_ID;
    @Value("${naver.openapi.client.secret}")
    private String NAVER_CLIENT_SECRET;

    @Override
    @Transactional
    public SearchResponse getSearchKakaoBlogs(SearchRequest searchRequest) {
        SearchResponse searchResponse;

        if ( Objects.isNull(searchRequest.getPage()) ) searchRequest.setPage(1);
        if ( Objects.isNull(searchRequest.getSize()) ) searchRequest.setSize(10);

        ResponseEntity<KakaoResponse> searchResponses = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/blog")
                        .queryParam("query", searchRequest.getKeyword())
                        .queryParam("sort", searchRequest.getSort())
                        .queryParam("page", searchRequest.getPage())
                        .queryParam("size", searchRequest.getSize())
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(
                                new BusinessException(
                                        response.statusCode().toString(),
                                        Errors.of(response.statusCode().value())
                                )
                        )
                )
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(
                                new BusinessException(
                                        response.statusCode().toString(),
                                        Errors.of(response.statusCode().value())
                                )
                        )
                )
                .toEntity(KakaoResponse.class)
                .block();

        // Documents
        List<SearchResponse.Info> infos = new ModelMapper().map(Objects.requireNonNull(Objects.requireNonNull(searchResponses).getBody()).getDocuments(),
                new TypeToken<List<SearchResponse.Info>>() {}.getType());

        // Pages
        int pageableCount = Objects.requireNonNull(searchResponses.getBody()).getMeta().getPageableCount();
        int totalPage = (pageableCount % searchRequest.getSize() == 0) ? pageableCount / searchRequest.getSize() : (pageableCount / searchRequest.getSize()) + 1;

        searchResponse = new SearchResponse(infos, null, new SearchResponse.PageInfo(searchRequest.getPage(), searchRequest.getSize(), totalPage, pageableCount));

        // 키워드 저장
        SearchHistoryEntity searchHistoryEntity = SearchHistoryEntity.builder()
                .searchType(CommonCode.KAKAO.getLabel())
                .keyword(searchRequest.getKeyword())
                .build();
        searchHistoryRepository.save(searchHistoryEntity);

        return searchResponse;
    }

    @Override
    @Transactional
    public SearchResponse getSearchNaverBlogs(SearchRequest searchRequest) {
        SearchResponse searchResponse;

        if ( searchRequest.getSort().equals("accuracy") ) {
            searchRequest.setSort("sim");
        } else if ( searchRequest.getSort().equals("recency") ) {
            searchRequest.setSort("date");
        }

        ResponseEntity<NaverResponse> searchResponses = webClient.mutate()
                .baseUrl(NAVER_URL)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/blog.json")
                        .queryParam("query", searchRequest.getKeyword())
                        .queryParam("sort", searchRequest.getSort())
                        .queryParam("start", searchRequest.getPage() == 1 ? 1 : ((searchRequest.getPage() - 1) * searchRequest.getSize() + 1) )
                        .queryParam("display", searchRequest.getSize())
                        .build())
                .header("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(
                                new BusinessException(
                                        response.statusCode().toString(),
                                        Errors.of(response.statusCode().value())
                                )
                        )
                )
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(
                                new BusinessException(
                                        response.statusCode().toString(),
                                        Errors.of(response.statusCode().value())
                                )
                        )
                )
                .toEntity(NaverResponse.class)
                .block();

        // Documents
        List<SearchResponse.NaverInfo> infos = new ModelMapper().map(Objects.requireNonNull(searchResponses.getBody()).getItems(),
                new TypeToken<List<SearchResponse.NaverInfo>>() {}.getType());

        int totalCount = Objects.requireNonNull(searchResponses.getBody()).getTotal();
        int startCount = Objects.requireNonNull(searchResponses.getBody()).getStart();
        int displayCount = Objects.requireNonNull(searchResponses.getBody()).getDisplay();

        // Pages
        int totalPageCount = ((totalCount - 1) / displayCount) + 1;
        int pageableCount = totalPageCount - ((startCount / displayCount) + 1);

        searchResponse = new SearchResponse(null, infos, new SearchResponse.PageInfo(searchRequest.getPage(), searchRequest.getSize(), totalPageCount, pageableCount));

        // 키워드 저장
        SearchHistoryEntity searchHistoryEntity = SearchHistoryEntity.builder()
                .searchType(CommonCode.NAVER.getLabel())
                .keyword(searchRequest.getKeyword())
                .build();
        searchHistoryRepository.save(searchHistoryEntity);

        return searchResponse;
    }

}
