package com.ch.core.response.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemResponse {
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("link")
    private String link;
    @JsonProperty("bloggerlink")
    private String bloggerLink;
    @JsonProperty("bloggername")
    private String bloggerName;
    @JsonProperty("postdate")
    private String postDate;
}
