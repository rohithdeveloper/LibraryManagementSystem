package com.example.lms.dtos;

import java.util.Date;

import com.example.lms.enums.CardStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto {

	private int cardno; // ID of the card (if you want to include it in creation)
	private CardStatus status; // Status of the card
	private Date creationDate; // Creation date of the card (generally set by the system)
	private Date updationDate; // Updation date of the card (generally set by the system)
	private int studentId;
}
