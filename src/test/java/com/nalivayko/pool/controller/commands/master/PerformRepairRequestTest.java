package com.nalivayko.pool.controller.commands.master;

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
public class PerformRepairRequestTest {
    private static final String REPAIR_REQUEST_ID = "repairId";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RepairRequestService repairRequestService;
    @Mock
    private Command openMasterPage;

    @Test
    public void shouldPerformRepairRequest() throws ServletException, IOException {
        String repairId = "1";

        when(request.getParameter(REPAIR_REQUEST_ID)).thenReturn(repairId);

        new PerformRepairRequest(repairRequestService, openMasterPage).execute(request, response);

        verify(repairRequestService).performRepairRequest(Integer.parseInt(repairId));
    }

    @Test
    public void shouldNotPerformRepairRequestWhenIdIsEmpty() throws ServletException, IOException {
        String repairId = "";

        when(request.getParameter(REPAIR_REQUEST_ID)).thenReturn(repairId);

        new PerformRepairRequest(repairRequestService, openMasterPage).execute(request, response);

        verify(repairRequestService, never()).performRepairRequest(anyInt());
    }

    @Test
    public void shouldNotPerformRepairRequestWhenIdIsNull() throws ServletException, IOException {
        String repairId = null;

        when(request.getParameter(REPAIR_REQUEST_ID)).thenReturn(repairId);

        new PerformRepairRequest(repairRequestService, openMasterPage).execute(request, response);

        verify(repairRequestService, never()).performRepairRequest(anyInt());
    }
}