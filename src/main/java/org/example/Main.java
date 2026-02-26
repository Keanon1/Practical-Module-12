package org.example;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LeaveService leaveService = new LeaveService();
        Scanner scanner = new Scanner(System.in);

        int totalLeaveDays = 20;
        int usedLeaveDays = 5;
        String leaveStatus = "NONE";

        boolean running = true;

        while (running) {
            System.out.println("\n=== Leave Management System ===");
            System.out.println("1. View Leave Balance");
            System.out.println("2. Apply for Leave");
            System.out.println("3. Manager Approve Leave");
            System.out.println("4. Manager Reject Leave");
            System.out.println("5. View Leave Status");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    int balance = leaveService.calculateLeaveBalance(totalLeaveDays, usedLeaveDays);
                    System.out.println("Remaining Leave Balance: " + balance + " days");
                    break;

                case 2:
                    System.out.print("Enter number of leave days to apply for: ");
                    int requestedDays = scanner.nextInt();

                    try {
                        int available = leaveService.calculateLeaveBalance(totalLeaveDays, usedLeaveDays);
                        leaveStatus = leaveService.applyForLeave(requestedDays, available);
                        usedLeaveDays += requestedDays;
                        System.out.println("Leave application submitted. Status: " + leaveStatus);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        leaveStatus = leaveService.approveLeave(leaveStatus);
                        System.out.println("Leave approved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        leaveStatus = leaveService.rejectLeave(leaveStatus);
                        System.out.println("Leave rejected.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Current Leave Status: " + leaveStatus);
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}