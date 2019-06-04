package com.nalivayko.pool.controller.commands.pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * provide methods and constants for pagination
 * @param <T>
 */
public abstract class Pagination<T> {
    protected static final String PAGE = "page";
    private static final String RECORDS = "records";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    public abstract void paginate(HttpServletRequest request, T service);

    /**
     * set attributes of pagination to provide them to user
     * @param data - data for current page
     * @param recordsCount - count of all records of displayable data
     * @param recordsPerPage - how many records there will be on page
     * @param currentPage - number pf current page (starts from 1)
     */
    void setPaginationAttributes(HttpServletRequest request, List data, int recordsCount,
                                 int recordsPerPage, int currentPage) {
        int numberOfPages = (int) Math.ceil((float) recordsCount / recordsPerPage);
        request.setAttribute(RECORDS, data);
        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
    }

    /**
     * read page attribute from request? and return it`s value or 1 if page == null
     * @return - current page
     */
    int getCurrentPage(HttpServletRequest request){
        return request.getParameter(PAGE) == null ? 1 : Integer.parseInt(request.getParameter(PAGE));
    }
}