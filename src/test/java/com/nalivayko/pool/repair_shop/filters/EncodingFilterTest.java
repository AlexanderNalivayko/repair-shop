package com.nalivayko.pool.filters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.*;
import java.io.IOException;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {
    private static final String contentType = "text/html; charset=";
    private String encodingTypeValue = "encoding";

    private EncodingFilter encodingFilter = new EncodingFilter();

    @Mock
    private ServletRequest request;
    @Mock
    private ServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private FilterConfig filterConfig;

    @Before
    public void setUp() {
        when(filterConfig.getInitParameter(anyString())).thenReturn(encodingTypeValue);
        encodingFilter.init(filterConfig);
    }

    @Test
    public void shouldSetEncodingAndContinueFilterChain() throws IOException, ServletException {
        encodingFilter.doFilter(request, response, filterChain);

        verify(request).setCharacterEncoding(anyString());
        verify(response).setCharacterEncoding(anyString());
        verify(response).setContentType(contentType + encodingTypeValue);
        verify(filterChain).doFilter(request, response);
    }
}
