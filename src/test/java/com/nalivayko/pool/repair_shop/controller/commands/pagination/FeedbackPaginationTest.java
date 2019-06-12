package com.nalivayko.pool.controller.commands.pagination;

import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.services.FeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackPaginationTest {
    private static final String PAGE = "page";
    private static final String RECORDS = "records";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    @Mock
    private HttpServletRequest request;
    @Mock
    private FeedbackService feedbackService;
    @Mock
    private Feedback feedback;

    @Test
    public void shouldPaginateData() {
        int count = 5;
        FeedbackPagination pagination = new FeedbackPagination(2);
        List<Feedback> list = Arrays.asList(feedback, feedback);

        when(request.getParameter(PAGE)).thenReturn("1");
        when(feedbackService.getAll(anyInt(), anyInt())).thenReturn(list);
        when(feedbackService.countAll()).thenReturn(count);

        pagination.paginate(request, feedbackService);

        verify(request).setAttribute(RECORDS, list);
        verify(request).setAttribute(NUMBER_OF_PAGES, 3);
        verify(request).setAttribute(CURRENT_PAGE, 1);
    }
}
