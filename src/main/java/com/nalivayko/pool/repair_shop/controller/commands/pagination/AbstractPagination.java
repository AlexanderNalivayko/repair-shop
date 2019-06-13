package com.nalivayko.pool.repair_shop.controller.commands.pagination;

import com.nalivayko.pool.repair_shop.util.ParametersAndAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * provide methods and constants for pagination
 *
 * @param <T>
 */
public abstract class AbstractPagination<T> {

    public abstract void paginate(HttpServletRequest request, T service);

    /**
     * Set attributes of pagination to provide them to ui
     *
     * @param data           - data for current page
     * @param recordsCount   - count of all records of displayable data
     * @param recordsPerPage - how many records there will be on page
     * @param currentPage    - number pf current page (starts from 1)
     */
    void setPaginationAttributes(HttpServletRequest request, List data, int recordsCount,
                                 int recordsPerPage, int currentPage) {
        int numberOfPages = (int) Math.ceil((float) recordsCount / recordsPerPage);
        request.setAttribute(ParametersAndAttributes.RECORDS, data);
        request.setAttribute(ParametersAndAttributes.NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(ParametersAndAttributes.CURRENT_PAGE, currentPage);
    }

    /**
     * read page attribute from request? and return it`s value or 1 if page == null
     *
     * @return - current page
     */
    int getCurrentPage(HttpServletRequest request) {
        return request.getParameter(ParametersAndAttributes.PAGE) == null ? 1
                : Integer.parseInt(request.getParameter(ParametersAndAttributes.PAGE));
    }
}