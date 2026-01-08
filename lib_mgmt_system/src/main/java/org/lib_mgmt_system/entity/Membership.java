package org.lib_mgmt_system.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Membership {
    private Long id;
    private String type;
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
