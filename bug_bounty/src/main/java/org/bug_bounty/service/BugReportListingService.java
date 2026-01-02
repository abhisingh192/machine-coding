package org.bug_bounty.service;

import lombok.Data;

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
    }

    public void listAssignedBugReports() {
        // Implementation to list assigned bug reports
    }

    public void listAssignedAndCompletedBugReports() {
        // Implementation to list assigned and completed bug reports
    }

    public void listAssignedAndIncompleteBugReports() {
        // Implementation to list assigned and in-progress bug reports
    }
}
