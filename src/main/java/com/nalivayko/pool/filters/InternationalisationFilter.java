package com.nalivayko.pool.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class InternationalisationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        servletRequest.setAttribute("locale", "ru");

        String localeParameter = servletRequest.getParameter("locale");
        String defaultLocale = "ru";

        if (localeParameter != null) {
            Locale locale = new Locale(localeParameter);

            ((HttpServletRequest) servletRequest).getSession().setAttribute("locale", locale);

            String uri = ((HttpServletRequest) servletRequest).getRequestURI();
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect(uri);
        } else {
            Locale locale = (Locale) ((HttpServletRequest) servletRequest).getSession().getAttribute("locale");
            if (locale == null) {
                ((HttpServletRequest) servletRequest).getSession().setAttribute("locale", new Locale(defaultLocale));
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
