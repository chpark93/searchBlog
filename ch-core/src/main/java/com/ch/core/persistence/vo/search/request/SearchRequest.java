package com.ch.core.persistence.vo.search.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchRequest {

    @NotBlank(message = "Keyword must not be blank")
    private String keyword;

    private String sort;

    @Positive
    private Integer page;

    @Positive
    private Integer size;

}
