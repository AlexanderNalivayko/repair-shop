package com.nalivayko.pool.filters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InternationalisationFilterTest {
    private InternationalisationFilter internationalisationFilter = new InternationalisationFilter();

    private static final String LOCALE = "locale";
    private static final String BUNDLE = "bundle";
    private String defaultBundle = "default bundle";
    private String defaultLocale = "default locale";

    @Mock
    private HttpServletRequest request;
    @Mock
    private ServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        when(filterConfig.getInitParameter(LOCALE)).thenReturn(defaultLocale);
        when(filterConfig.getInitParameter(BUNDLE)).thenReturn(defaultBundle);
        internationalisationFilter.init(filterConfig);
    }

    @Test
    public void shouldSetLocaleToLocaleParameterIfItsNotNull() throws IOException, ServletException {
        String locale = "some locale";

        when(request.getParameter(LOCALE)).thenReturn(locale);
        when(request.getSession()).thenReturn(session);
        internationalisationFilter.doFilter(request, response, filterChain);

        verify(session).setAttribute(LOCALE, locale);
    }

    @Test
    public void shouldSetDefaultLocaleIfItsNull() throws IOException, ServletException {
        when(request.getParameter(LOCALE)).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        internationalisationFilter.doFilter(request, response, filterChain);

        verify(session).setAttribute(LOCALE, defaultLocale);
    }

    @Test
    public void shouldSetBundle() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        internationalisationFilter.doFilter(request, response, filterChain);

        verify(session).setAttribute(BUNDLE, defaultBundle);
    }
}
