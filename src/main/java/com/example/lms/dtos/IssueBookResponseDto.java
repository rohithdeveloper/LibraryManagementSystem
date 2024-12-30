package com.example.lms.dtos;

import java.util.Date;

import com.example.lms.enums.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IssueBookResponseDto {

	private String transactionId;
	private TransactionStatus transactionStatus;
	private Date transactionDate;
	private Boolean isIssuedOperation;
	private String bookName;
	private String message;

}
