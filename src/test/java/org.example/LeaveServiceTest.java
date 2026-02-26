package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeaveServiceTest {

    LeaveService leaveService = new LeaveService();

    @Test
    void testApplyForLeave() {
        String status = leaveService.applyForLeave(3, 10);
        assertEquals("PENDING", status);
    }

    @Test
    void testApplyForLeaveInsufficientBalance() {
        assertThrows(IllegalArgumentException.class, () ->
                leaveService.applyForLeave(15, 10));
    }

    @Test
    void testCalculateLeaveBalance() {
        assertEquals(15, leaveService.calculateLeaveBalance(20, 5));
    }

    @Test
    void testApproveLeave() {
        assertEquals("APPROVED", leaveService.approveLeave("PENDING"));
    }

    @Test
    void testRejectLeave() {
        assertEquals("REJECTED", leaveService.rejectLeave("PENDING"));
    }

    @Test
    void testApproveAlreadyProcessedLeave() {
        assertThrows(IllegalStateException.class, () ->
                leaveService.approveLeave("APPROVED"));
    }

    @Test
    void testFullLeaveWorkflow() {
        LeaveService service = new LeaveService();

        int totalDays = 20;
        int usedDays = 5;

        int balance = service.calculateLeaveBalance(totalDays, usedDays);
        String status = service.applyForLeave(3, balance);
        status = service.approveLeave(status);

        assertEquals("APPROVED", status);
    }

    @Test
    void testFullLeaveWorkflowRejection() {

        LeaveService service = new LeaveService();

        int balance = service.calculateLeaveBalance(20, 2);
        String status = service.applyForLeave(5, balance);
        status = service.rejectLeave(status);

        assertEquals("REJECTED", status);
    }
}