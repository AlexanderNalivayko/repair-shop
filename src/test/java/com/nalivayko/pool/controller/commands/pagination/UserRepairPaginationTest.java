package com.nalivayko.pool.controller.commands.pagination;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.RepairRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepairPaginationTest {
    private static final String PAGE = "page";
    private static final String RECORDS = "records";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    @Mock
    private HttpServletRequest request;
    @Mock
    private RepairRequestService repairRequestService;
    @Mock
    private RepairRequest repairRequest;
    @Mock
    private HttpSession session;
    @Mock
    private User user;

    @Test
    public void shouldPaginateData() {
        int count = 5;
        UserRepairPagination pagination = new UserRepairPagination(2);
        List<RepairRequest> list = Arrays.asList(repairRequest, repairRequest);

        when(request.getParameter(PAGE)).thenReturn("1");
        when(repairRequestService.getAllByUserId(anyInt(), anyInt(), anyInt())).thenReturn(list);
        when(repairRequestService.countByUserId(anyInt())).thenReturn(count);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        pagination.paginate(request, repairRequestService);

        verify(request).setAttribute(RECORDS, list);
        verify(request).setAttribute(NUMBER_OF_PAGES, 3);
        verify(request).setAttribute(CURRENT_PAGE, 1);
    }
}