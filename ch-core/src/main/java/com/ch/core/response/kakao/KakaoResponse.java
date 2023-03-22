package com.ch.core.response.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoResponse {

    private MetaResponse meta;

    private List<DocumentResponse> documents;

}
