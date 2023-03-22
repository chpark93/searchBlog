package com.ch.search.service;

import com.ch.core.persistence.vo.search.request.SearchRequest;
import com.ch.core.persistence.vo.search.response.SearchResponse;

public interface SearchService {

    SearchResponse getSearchKakaoBlogs(SearchRequest searchRequest);

    SearchResponse getSearchNaverBlogs(SearchRequest searchRequest);

}
