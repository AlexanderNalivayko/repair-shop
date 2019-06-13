package com.nalivayko.pool.repair_shop.controller.commands.manager;

import com.nalivayko.pool.repair_shop.controller.commands.Command;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AcceptRepairRequestTest {
    private static final String PRICE = "price";
    private static final String REPAIR_ID = "repairId";
    private static final String PRICE_STRING = "100";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RepairRequestService repairRequestService;
    @Mock
    private Command openManagerPage;

    @Test
    public void shouldAcceptRepaidRequest() throws ServletException, IOException {
        String idString = "1";

        when(request.getParameter(REPAIR_ID)).thenReturn(idString);
        when(request.getParameter(PRICE)).thenReturn(PRICE_STRING);

        new AcceptRepairRequest(repairRequestService, openManagerPage).execute(request, response);

        verify(repairRequestService).acceptRepairRequest(idString, PRICE_STRING);
        verify(openManagerPage).execute(request, response);
    }

    @Test
    public void shouldAcceptRepaidRequestOnEmptyPrice() throws ServletException, IOException {
        String idString = "";

        when(request.getParameter(REPAIR_ID)).thenReturn(idString);
        when(request.getParameter(PRICE)).thenReturn(PRICE_STRING);

        new AcceptRepairRequest(repairRequestService, openManagerPage).execute(request, response);

        verify(repairRequestService, never()).acceptRepairRequest(anyString(), anyString());
    }

    @Test
    public void shouldAcceptRepaidRequestOnNullPrice() throws ServletException, IOException {
        String idString = null;

        when(request.getParameter(REPAIR_ID)).thenReturn(idString);
        when(request.getParameter(PRICE)).thenReturn(PRICE_STRING);

        new AcceptRepairRequest(repairRequestService, openManagerPage).execute(request, response);

        verify(repairRequestService, never()).acceptRepairRequest(anyString(), anyString());
    }
}