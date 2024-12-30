	package com.example.lms.model;
	
	import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.example.lms.enums.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	
	@Entity
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@Table(name = "Transaction")
	public class Transaction {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "Transaction_Id")
		private int id;
	
		@Column(name = "Transaction_No")
		private String transactionNumber;
	
		@Column(name = "Transaction_Status")
		@Enumerated(EnumType.STRING)
		private TransactionStatus transactionStatus;
	
		@Column(name = "Transaction_Date")
		@CreationTimestamp
		private Date transactionDate;
	
		@Column(name = "Transaction_IssuedOp")
		private Boolean IsissuedOperation;
	
		@Column(name = "Transaction_Message")
		private String Message;
	
		@ManyToOne
		@JoinColumn
		Book book;
	
		@ManyToOne
		@JoinColumn
		LibraryCard card;
	}
