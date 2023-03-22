package com.ch.core.response.naver;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NaverResponse {

    private int total;

    private int start;

    private int display;

    private List<ItemResponse> items;



}
