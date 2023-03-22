package com.ch.search.controller;

import com.ch.search.SpringMockMvcTestSupport;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.mock.web.MockHttpSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Blog Search 테스트")
public class SearchControllerTest extends SpringMockMvcTestSupport {

    @Test
    @DisplayName("Blog 검색 테스트")
    public void getSearchBlogsTest() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();

        for ( int i = 0 ; i < 50 ; i++ )
            searchByKeyword("kakao", mockHttpSession);

        for ( int i = 0 ; i < 40 ; i++ )
            searchByKeyword("bank", mockHttpSession);

        for ( int i = 0 ; i < 20 ; i++ )
            searchByKeyword("test", mockHttpSession);

        for ( int i = 0 ; i < 10 ; i++ )
            searchByKeyword("keyword", mockHttpSession);

        for ( int i = 0 ; i < 3 ; i++ )
            searchByKeyword("볼빨간사춘기", mockHttpSession);
    }

    void searchByKeyword(String keyword, MockHttpSession mockHttpSession) throws Exception {

        this.mockMvc.perform(get("/search/blog")
                        .param("keyword", keyword)
                        .param("sort", "accuracy")
                        .param("page", "1")
                        .param("size", "10")
                        .session(mockHttpSession))
                .andExpect(status().isOk());
    }
}