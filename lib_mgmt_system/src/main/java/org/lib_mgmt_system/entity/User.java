package org.lib_mgmt_system.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class User {
    private Long id;
    private String username;
    private Membership membership;
    private Map<Long, Map<LocalDate, LocalDate>> bookIdBorrowedDateMap = new HashMap<>(); // <bookId, <issueDate, dueDate
}
