package com.ch.search.controller;

import com.ch.core.exception.BusinessException;
import com.ch.core.persistence.vo.search.request.SearchRequest;
import com.ch.core.persistence.vo.search.response.SearchResponse;
import com.ch.core.security.response.Response;
import com.ch.core.utils.HelperUtil;
import com.ch.search.service.SearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class SearchController {

    private final Response response;
    private final SearchService searchService;

    @GetMapping("/search/blog")
    public ResponseEntity<?> getSearchBlogs(@ParameterObject @Valid SearchRequest searchRequest, Errors errors) {
        SearchResponse searchBlogs;
        if ( errors.hasErrors() ) {
            return response.invalidFields(HelperUtil.refineErrors(errors));
        }

        try {
            searchBlogs = searchService.getSearchKakaoBlogs(searchRequest);
        } catch ( BusinessException e ) {
            if ( e.getErrorCode().getStatus() == 500 ) {
                searchBlogs = searchService.getSearchNaverBlogs(searchRequest);
            } else {
                throw e;
            }
        }

        return response.success(searchBlogs, "Success", HttpStatus.OK);
    }
}
