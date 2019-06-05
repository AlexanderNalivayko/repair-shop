package com.nalivayko.pool.controller.commands.master;

import com.nalivayko.pool.controller.commands.pagination.AbstractPagination;
import com.nalivayko.pool.services.RepairRequestService;
import com.nalivayko.pool.util.PagesPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenMasterPageTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RepairRequestService repairRequestService;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private AbstractPagination<RepairRequestService> pagination;

    @Test
    public void shouldRedirectToMasterPage() throws ServletException, IOException {
        when(request.getRequestDispatcher(PagesPath.MASTER)).thenReturn(requestDispatcher);

        new OpenMasterPage(repairRequestService, pagination).execute(request, response);

        verify(requestDispatcher).forward(request, response);
    }
}