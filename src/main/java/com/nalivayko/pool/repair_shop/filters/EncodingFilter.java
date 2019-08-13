package com.nalivayko.pool.repair_shop.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * {@code EncodingFilter} - sets request and response encoding to
 * encoding which it takes from initial parameters of web.xml
 */

public class EncodingFilter implements Filter {
    private static final String ENCODING_TYPE = "encoding_type";
    private static final String contentType = "text/html; charset=";
    private String encodingTypeValue;

    @Override
    public void init(FilterConfig filterConfig) {
        encodingTypeValue = filterConfig.getInitParameter(ENCODING_TYPE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encodingTypeValue);
        response.setCharacterEncoding(encodingTypeValue);
        response.setContentType(contentType + encodingTypeValue);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
