package org.bug_bounty.service;

import lombok.Data;
import org.bug_bounty.entity.BugReport;
import org.bug_bounty.entity.BugReportStatus;
import org.bug_bounty.entity.Role;
import org.bug_bounty.entity.Severity;

import java.util.HashMap;
import java.util.Map;

@Data
public class BugReportingService {

    private final UserService userService;
    private static int bugReportIdCounter = 1;
    private final Map<String, BugReport> bugReportMap = new HashMap<>();
    private final Map<String, BugReport> reporterBugReportMap = new HashMap<>();
    private final Map<String, BugReport> assigneeBugReportMap = new HashMap<>();

    public BugReportingService(UserService userService) {
        this.userService = userService;
    }


    public void reportBug(String title, String description, String reporterEmail, String severity) {
        if (bugReportMap.containsKey(title)) {
            System.out.println("Bug with the same title already exists.");
            return;
        }

        BugReport bugReport = new BugReport();
        bugReport.setId(bugReportIdCounter++);
        bugReport.setTitle(title);
        bugReport.setDescription(description);
        bugReport.setReporterEmail(reporterEmail);
        bugReport.setSeverity(Severity.valueOf(severity));
        bugReportMap.put(title, bugReport);
        reporterBugReportMap.put(reporterEmail, bugReport);
        System.out.println("Bug reported with ID: " + bugReport.getId());

    }

    public void changeBugStatus(String title, String status) {
        if (!bugReportMap.containsKey(title)) {
            System.out.println("Bug with the given title does not exist.");
            return;
        }
        BugReport bugReport = bugReportMap.get(title);
        bugReport.setStatus(BugReportStatus.valueOf(status));
        System.out.println("Bug status updated to: " + status);

    }

    public void assignBugToDeveloper(String title, String developerEmail) {
        if (!bugReportMap.containsKey(title)) {
            System.out.println("Bug with the given title does not exist.");
            return;
        }

        if (!userService.getUsers().containsKey(developerEmail)) {
            System.out.println("Developer with the given email does not exist.");
            return;
        }
        BugReport bugReport = bugReportMap.get(title);
        bugReport.setAssigneeEmail(developerEmail);
        assigneeBugReportMap.put(developerEmail, bugReport);
        System.out.println("Bug assigned to developer: " + developerEmail);

    }

    public void editBugReport(String title, String description, String reporterEmail, String severity) {
        if (!bugReportMap.containsKey(title)) {
            System.out.println("Bug with the given title does not exist.");
            return;
        }
        BugReport bugReport = bugReportMap.get(title);
        bugReport.setDescription(description);
        bugReport.setReporterEmail(reporterEmail);
        bugReport.setSeverity(Severity.valueOf(severity));
        System.out.println("Bug report updated successfully.");

    }

    public void addCommentToBugReport(String title, String comment) {
        if (!bugReportMap.containsKey(title)) {
            System.out.println("Bug with the given title does not exist.");
            return;
        }
        // For simplicity, we are just printing the comment here.
        // In a real application, you would store comments in a list within the BugReport entity.
        BugReport bugReport = bugReportMap.get(title);
        bugReport.getComments().add(comment);
        System.out.println("Comment added to bug '" + title + "': " + comment);

    }

    public void deleteBugReport(String title) {
        // Only admin can delete bug report
        if (!bugReportMap.containsKey(title)) {
            System.out.println("Bug with the given title does not exist.");
            return;
        }

        if (userService.getLoggedinUser() == null || userService.getLoggedinUser().getRole() != Role.ADMIN) {
            System.out.println("user not logged in or user not admin");
            return;
        }
        bugReportMap.remove(title);
        System.out.println("Bug report deleted successfully.");
        // delete from the userBugMap as well
        reporterBugReportMap.entrySet().stream().filter(entry -> entry.getValue().getTitle().equals(title)).findFirst()
                .ifPresent(entry -> reporterBugReportMap.remove(entry.getKey()));

        assigneeBugReportMap.entrySet().stream().filter(entry -> entry.getValue().getTitle().equals(title)).findFirst()
                .ifPresent(entry -> assigneeBugReportMap.remove(entry.getKey()));


    }


}
