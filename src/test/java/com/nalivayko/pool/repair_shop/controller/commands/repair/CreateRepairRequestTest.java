package com.nalivayko.pool.controller.commands.repair;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.services.RepairRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.nalivayko.pool.util.ParametersAndAttributes.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateRepairRequestTest {

    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RepairRequestService repairRequestService;
    @Mock
    private User user;
    @Mock
    private OpenRepairPage openRepairPage;

    @Test
    public void shouldCreateRepairRequest() throws ServletException, IOException {
        String type = "some type";
        String brand = "some brand";
        String name = "some name";
        String description = "some description";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(USER)).thenReturn(user);

        when(request.getParameter(PRODUCT_TYPE)).thenReturn(type);
        when(request.getParameter(BRAND)).thenReturn(brand);
        when(request.getParameter(NAME)).thenReturn(name);
        when(request.getParameter(DESCRIPTION)).thenReturn(description);

        new CreateRepairRequest(repairRequestService, openRepairPage).execute(request, response);

        verify(repairRequestService).createRepairRequest(user, type, brand, name, description);
    }
}
