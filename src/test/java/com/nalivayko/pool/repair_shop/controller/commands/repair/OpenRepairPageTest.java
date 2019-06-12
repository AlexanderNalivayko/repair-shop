package com.nalivayko.pool.controller.commands.repair;

import com.nalivayko.pool.controller.commands.pagination.UserRepairPagination;
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
public class OpenRepairPageTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RepairRequestService repairRequestService;
    @Mock
    private UserRepairPagination userRepairPagination;
    @Mock
    private RequestDispatcher dispatcher;

    @Test
    public void shouldForwardToRepairPage() throws ServletException, IOException {
        when(request.getRequestDispatcher(PagesPath.REPAIR)).thenReturn(dispatcher);

        new OpenRepairPage(repairRequestService, userRepairPagination).execute(request, response);

        verify(dispatcher).forward(request, response);
    }
}