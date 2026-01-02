package org.bug_bounty.entity;

import lombok.Getter;

@Getter
public enum BugReportStatus {
    OPEN,
    REPORT_REVIEW,
    REJECTED,
    ACKNOWLEDGED,
    BOUNTY_REVIEW,
    BOUNTY_PAID,
    CLOSED

}
