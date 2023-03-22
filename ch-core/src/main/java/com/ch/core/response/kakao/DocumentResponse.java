package com.ch.core.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DocumentResponse {
    @JsonProperty("title")
    private String title;
    @JsonProperty("contents")
    private String contents;
    @JsonProperty("url")
    private String url;
    @JsonProperty("blogname")
    private String blogName;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("datetime")
    private String datetime;
}
