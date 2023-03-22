package com.ch.core.persistence.vo.search.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchResponse {

    private List<SearchResponse.Info> kakao;

    private List<SearchResponse.NaverInfo> naver;

    private PageInfo pageInfo;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbnail;
        private String datetime;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NaverInfo {
        private String title;
        private String link;
        private String description;
        private String bloggername;
        private String bloggerlink;
        private String postdate;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageInfo {
        private Integer page;
        private Integer size;
        private Integer totalPages;
        private Integer pageableCount;
    }

    public SearchResponse(List<Info> kakao, List<NaverInfo> naver, PageInfo pageInfo) {
        this.kakao = kakao;
        this.naver = naver;
        this.pageInfo = pageInfo;
    }

}
