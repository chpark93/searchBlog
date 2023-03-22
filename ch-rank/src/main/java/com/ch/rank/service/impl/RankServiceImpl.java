package com.ch.rank.service.impl;

import com.ch.core.persistence.vo.search.response.SearchHistoryResponse;
import com.ch.core.security.response.Response;
import com.ch.rank.service.RankService;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ch.core.persistence.domain.search.QSearchHistoryEntity.searchHistoryEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService {

    private final Response response;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ResponseEntity<?> getSearchRank() {
        StringPath orderByAlias = Expressions.stringPath("count");
        List<SearchHistoryResponse.Rank> searchHistoryRanks = jpaQueryFactory
                .select(
                        Projections.fields(SearchHistoryResponse.Rank.class,
                                searchHistoryEntity.keyword,
                                searchHistoryEntity.keyword.count().as("count")
                        )
                )
                .from(searchHistoryEntity)
                .groupBy(searchHistoryEntity.keyword)
                .orderBy(orderByAlias.desc())
                .fetch();

        return response.success(new SearchHistoryResponse(searchHistoryRanks), "Success", HttpStatus.OK);
    }
}
