package com.nalivayko.pool.controller.commands.manager;

import com.nalivayko.pool.controller.commands.Command;
import com.nalivayko.pool.services.RepairRequestService;
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
public class RejectRepairRequestTest {
    private static final String REASON = "reason";
    private static final String REPAIR_ID = "repairId";

    @Mock
    private Command openManagerPage;
    @Mock
    private RepairRequestService repairRequestService;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldRejectRepairRequest() throws ServletException, IOException {
        String stringRepairId = "1";
        String reasonMessage = "msg";

        when(request.getParameter(REPAIR_ID)).thenReturn(stringRepairId);
        when(request.getParameter(REASON)).thenReturn(reasonMessage);

        new RejectRepairRequest(repairRequestService, openManagerPage).execute(request, response);

        verify(repairRequestService).rejectRepairRequest(Integer.parseInt(stringRepairId), reasonMessage);
    }

    @Test
    public void shouldNotRejectRepairRequestIfReasonIsEmpty() throws ServletException, IOException {
        String stringRepairId = "1";
        String reasonMessage = "";

        when(request.getParameter(REPAIR_ID)).thenReturn(stringRepairId);
        when(request.getParameter(REASON)).thenReturn(reasonMessage);

        new RejectRepairRequest(repairRequestService, openManagerPage).execute(request, response);

        verify(repairRequestService, never()).rejectRepairRequest(anyInt(), anyString());
    }

    @Test
    public void shouldNotRejectRepairRequestIfReasonIsNull() throws ServletException, IOException {
        String stringRepairId = "1";
        String reasonMessage = null;

        when(request.getParameter(REPAIR_ID)).thenReturn(stringRepairId);
        when(request.getParameter(REASON)).thenReturn(reasonMessage);

        new RejectRepairRequest(repairRequestService, openManagerPage).execute(request, response);

        verify(repairRequestService, never()).rejectRepairRequest(anyInt(), anyString());
    }

    @Test
    public void shouldNotRejectRepairRequestIfIdIsNull() throws ServletException, IOException {
        String stringRepairId = null;
        String reasonMessage = "msg";

        when(request.getParameter(REPAIR_ID)).thenReturn(stringRepairId);
        when(request.getParameter(REASON)).thenReturn(reasonMessage);

        new RejectRepairRequest(repairRequestService, openManagerPage).execute(request, response);

        verify(repairRequestService, never()).rejectRepairRequest(anyInt(), anyString());
    }
}