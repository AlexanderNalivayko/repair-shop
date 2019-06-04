package com.nalivayko.pool.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * {@code InternationalisationFilter} - changes locale (if parameter locale is not null) and
 * set it to session attribute. Also if session locale attribute is null and parameter locale
 * is null - it takes default locale from initial parameters of web.xml
 */
public class InternationalisationFilter implements Filter {
    private static final String LOCALE = "locale";
    private static final String BUNDLE = "bundle";
    private String defaultBundle;
    private String defaultLocale;

    @Override
    public void init(FilterConfig filterConfig) {
        defaultBundle = filterConfig.getInitParameter(BUNDLE);
        defaultLocale = filterConfig.getInitParameter(LOCALE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String localeParameter = request.getParameter(LOCALE);
        HttpSession session = request.getSession();

        if (localeParameter != null) {
            session.setAttribute(LOCALE, localeParameter);
        } else {
            if (request.getSession().getAttribute(LOCALE) == null) {
                session.setAttribute(LOCALE, defaultLocale);
            }
        }
        session.setAttribute(BUNDLE, defaultBundle);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
