package com.nalivayko.pool.repair_shop.controller.commands.manager;

import com.nalivayko.pool.repair_shop.controller.commands.pagination.AbstractPagination;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import com.nalivayko.pool.repair_shop.util.PagesPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OpenManagerPageTest {

    @Mock
    private RepairRequestService repairRequestService;
    @Mock
    private AbstractPagination<RepairRequestService> pagination;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void shouldForwardToManagerPage() throws ServletException, IOException {
        when(request.getRequestDispatcher(PagesPath.MANAGER)).thenReturn(requestDispatcher);

        new OpenManagerPage(repairRequestService, pagination).execute(request, response);

        verify(requestDispatcher).forward(request, response);
    }
}