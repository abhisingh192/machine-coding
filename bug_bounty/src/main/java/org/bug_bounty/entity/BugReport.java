package org.bug_bounty.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BugReport {

    private int id;
    private String title;
    private String description;
    private BugReportStatus status;
    private Severity severity;
    private double bountyAmount;
    private String reporterEmail;
    private String assigneeEmail;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private List<String> comments;
}
