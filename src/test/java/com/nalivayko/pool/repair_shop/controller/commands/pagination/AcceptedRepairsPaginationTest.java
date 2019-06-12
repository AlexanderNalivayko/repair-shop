package com.nalivayko.pool.repair_shop.controller.commands.pagination;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.services.RepairRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AcceptedRepairsPaginationTest {
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

    @Test
    public void shouldPaginateData() {
        int count = 5;
        AcceptedRepairsPagination pagination = new AcceptedRepairsPagination(2);
        List<RepairRequest> list = Arrays.asList(repairRequest, repairRequest);

        when(request.getParameter(PAGE)).thenReturn("1");
        when(repairRequestService.getAllAccepted(anyInt(), anyInt())).thenReturn(list);
        when(repairRequestService.countAccepted()).thenReturn(count);

        pagination.paginate(request, repairRequestService);

        verify(request).setAttribute(RECORDS, list);
        verify(request).setAttribute(NUMBER_OF_PAGES, 3);
        verify(request).setAttribute(CURRENT_PAGE, 1);
    }
}