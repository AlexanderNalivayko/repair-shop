package com.nalivayko.pool.filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final String ENCODING_TYPE = "encoding_type";
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
        response.setContentType("text/html; charset=" + encodingTypeValue);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
