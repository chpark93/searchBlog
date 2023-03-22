package com.ch.rank.controller;

import com.ch.rank.SpringMockMvcTestSupport;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.mock.web.MockHttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Blog Search 테스트")
public class RankControllerTest extends SpringMockMvcTestSupport {

    @Test
    @DisplayName("인기 검색어 목록 테스트")
    public void getSearchRankTest() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();

        this.mockMvc.perform(get("/search/rank")
                        .session(mockHttpSession))
                .andExpect(status().isOk())
                .andDo(print());
    }
}