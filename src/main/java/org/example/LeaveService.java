package org.example;


public class LeaveService {

    // Employee applies for leave
    public String applyForLeave(int requestedDays, int availableDays) {
        if (requestedDays <= 0) {
            throw new IllegalArgumentException("Requested days must be greater than zero");
        }
        if (requestedDays > availableDays) {
            throw new IllegalArgumentException("Not enough leave balance");
        }
        return "PENDING";
    }

    // Calculate remaining leave balance
    public int calculateLeaveBalance(int totalDays, int usedDays) {
        if (usedDays > totalDays) {
            throw new IllegalArgumentException("Used days cannot exceed total days");
        }
        return totalDays - usedDays;
    }

    // Manager approves leave
    public String approveLeave(String status) {
        if (!status.equals("PENDING")) {
            throw new IllegalStateException("Leave already processed");
        }
        return "APPROVED";
    }

    // Manager rejects leave
    public String rejectLeave(String status) {
        if (!status.equals("PENDING")) {
            throw new IllegalStateException("Leave already processed");
        }
        return "REJECTED";
    }

    // View current leave status
    public String getLeaveStatus(String status) {
        return status;
    }
}
