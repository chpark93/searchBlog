package com.ch.core.code;

import lombok.Getter;

@Getter
public enum CommonCode {

    KAKAO("KAKAO"),
    NAVER("NAVER"),
    ;

    @Getter
    private final String label;

    CommonCode(String label) {
        this.label = label;
    }

}
