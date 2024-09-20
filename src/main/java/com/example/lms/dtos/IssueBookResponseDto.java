package com.example.lms.dtos;

import com.example.lms.enums.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IssueBookResponseDto {
    private String transactionId;
    private String bookName;
    private TransactionStatus transactionStatus;
}
