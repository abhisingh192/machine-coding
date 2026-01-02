package org.bug_bounty.service;

import lombok.Data;
import org.bug_bounty.entity.BugReportStatus;

@Data
public class BugReportListingService {

    private final UserService userService;
    private final BugReportingService bugReportingService;

    public BugReportListingService(UserService userService, BugReportingService bugReportingService) {
        this.userService = userService;
        this.bugReportingService = bugReportingService;
    }

    public void listAllBugReports() {
        // Implementation to list all bug reports
        bugReportingService.getBugReportMap().forEach((title, bugReport) -> {
            System.out.println("ID: " + bugReport.getId() + ", Title: " + bugReport.getTitle() +
                    ", Status: " + bugReport.getStatus() + ", Severity: " + bugReport.getSeverity());
        });
    }

    public void listAssignedBugReports() {
        // Implementation to list assigned bug reports
        bugReportingService.getAssigneeBugReportMap().forEach((assigneeEmail, bugReport) -> {
            System.out.println("ID: " + bugReport.getId() + ", Title: " + bugReport.getTitle() +
                    ", Status: " + bugReport.getStatus() + ", Severity: " + bugReport.getSeverity());
        });
    }

    public void listAssignedAndCompletedBugReports() {
        // Implementation to list assigned and completed bug reports
        bugReportingService.getAssigneeBugReportMap().forEach((assigneeEmail, bugReport) -> {
            if (bugReport.getStatus() == BugReportStatus.CLOSED) {
                System.out.println("ID: " + bugReport.getId() + ", Title: " + bugReport.getTitle() +
                        ", Status: " + bugReport.getStatus() + ", Severity: " + bugReport.getSeverity());
            }
        });
    }

    public void listAssignedAndIncompleteBugReports() {
        // Implementation to list assigned and in-progress bug reports
        bugReportingService.getAssigneeBugReportMap().forEach((assigneeEmail, bugReport) -> {
            if (bugReport.getStatus() != BugReportStatus.CLOSED) {
                System.out.println("ID: " + bugReport.getId() + ", Title: " + bugReport.getTitle() +
                        ", Status: " + bugReport.getStatus() + ", Severity: " + bugReport.getSeverity());
            }
        });
    }
}
