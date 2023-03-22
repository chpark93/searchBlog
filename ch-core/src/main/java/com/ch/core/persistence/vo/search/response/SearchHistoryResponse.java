package com.ch.core.persistence.vo.search.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchHistoryResponse {

    private List<SearchHistoryResponse.Rank> rank;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Rank {
        private String keyword;
        private Long count;
    }

    @Builder
    public SearchHistoryResponse(List<SearchHistoryResponse.Rank> rank) {
        this.rank = rank;
    }

}
