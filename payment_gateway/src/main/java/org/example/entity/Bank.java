package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Bank {
    
    private String bankId;
    private String bankName;
    private String bankCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
