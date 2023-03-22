package com.ch.core.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.stereotype.Component;

/**
 * ResponseHeaderWriter
 */
@Component
public class ResponseHeaderWriter implements HeaderWriter {
    /**
     * writeHeaders
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
        /**
            max-age=[seconds] — Expires와 동일한 의미지만 고정된 절대시간 값이아닌 요청 시간으로부터의 상대적 시간을 표시한다. 명시된경우 Expires보다 우선시된다.
            s-maxage=[seconds] — max-age와 동일한 의미지만  shared caches (예: proxy)에만 적용됨. 명시된경우 max-age나 Expires보다 우선시된다.3
            public — 일반적으로 HTTP 인증이 된 상태에서 일어나는 응답은 자동으로 private 이 되지만 public을 명시적으로 설정하면 인증이된 상태더라도 캐시하도록 한다.
            private — 특정 유저(사용자의 브라우저)만 캐쉬하도록 설정. 여러사람이 사용하는 네트워크상의 중간자(intermediaries)역할을 하는 shared caches (예: proxy) 에는 경우 캐쉬되지 않는다.
            no-cache — 응답 데이터를 캐쉬하고는 있지만, 일단 먼저 서버에 요청해서 유효성 검사(validation)을 하도록 강제한다. 어느정도 캐쉬의 효용을 누리면서도 컨텐츠의 freshness를 강제로 유지하는데 좋다.
            no-store — 어떤 상황에서도 해당 response 데이터를 저장하지 않음.
            no-transform — 어떤 프록시들은 어떤 이미지나 문서들을 성능향상을위해 최적화된 포멧으로 변환하는 등의 자동화된 동작을 하는데 이러한 것을 원치 않는다면 이 옵션을 명시해주는 것이 좋다.
            must-revalidate —  HTTP는 특정 상황(네트워크 연결이 끊어졌을때 등)에서는 fresh하지 않은 캐쉬 데이터임에도 불구하고 사용하는 경우가 있는데, 금융거래 등의 상황에서는 이러한 동작이 잘못된 결과로 이어질 가능성이 있기 때문에 이 지시자를 통해서 그러한 사용을 방지한다.
            proxy-revalidate — must-revalidate와 비슷하지만 proxy caches 에만 적용된다.
        */
        
        // 캐시하지 않음
        response.setHeader("Cache-Control", "no-cache, max-age=0, must-revalidate");
    }
}