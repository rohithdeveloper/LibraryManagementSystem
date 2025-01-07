package com.example.lms.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dtos.IssueBookRequestDto;
import com.example.lms.dtos.IssueBookResponseDto;
import com.example.lms.enums.CardStatus;
import com.example.lms.enums.TransactionStatus;
import com.example.lms.exceptions.BookIssueException;
import com.example.lms.exceptions.BookNotFoundException;
import com.example.lms.exceptions.CardNotActivatedException;
import com.example.lms.exceptions.CardNotPresentException;
import com.example.lms.model.Book;
import com.example.lms.model.LibraryCard;
import com.example.lms.model.Transaction;
import com.example.lms.repository.BookRepository;
import com.example.lms.repository.CardRepository;
import com.example.lms.repository.TransactionRepository;
import com.example.lms.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Transactional
	@Override
	public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) {
	    // Initialize a new transaction
	    Transaction transaction = new Transaction();
	    transaction.setTransactionNumber(UUID.randomUUID().toString());
	    transaction.setIsissuedOperation(true);

	    // Fetch the library card
	    Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(issueBookRequestDto.getCardId());
	    if (!optionalLibraryCard.isPresent()) {
	        throw new CardNotPresentException("Card not present");
	    }

	    LibraryCard libraryCard = optionalLibraryCard.get();

	    // Fetch the book
	    Optional<Book> optionalBook = bookRepository.findById(issueBookRequestDto.getBookId());
	    if (!optionalBook.isPresent()) {
	        throw new BookNotFoundException("Book not found");
	    }

	    Book book = optionalBook.get();

	    if (libraryCard.getStudent() == null) {
	        throw new CardNotActivatedException("No student is linked to this card");
	    }

	    // Check card status
	    if (libraryCard.getStatus() != CardStatus.ACTIVATED) {
	        throw new CardNotActivatedException("Your card is not activated");
	    }

	    // Check book availability
	    if (book.isIssued()) {
	        throw new BookIssueException("Sorry, the book is already issued");
	    }

	    // Proceed with issuing the book
	    transaction.setTransactionStatus(TransactionStatus.SUCCESS);
	    transaction.setMessage("Transaction was successful");
	    book.setIssued(true);
	    book.setCard(libraryCard);
	    book.getTransactions().add(transaction);
	    libraryCard.getTransaction().add(transaction);
	    libraryCard.getBookIssued().add(book);

	    // Save entities
	    bookRepository.save(book);
	    cardRepository.save(libraryCard);
	    transactionRepository.save(transaction);

	    // Create and return response DTO
	    IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
	    issueBookResponseDto.setTransactionId(transaction.getTransactionNumber());
	    issueBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());
	    issueBookResponseDto.setBookName(book.getTitle());
	    issueBookResponseDto.setMessage(transaction.getMessage()); // Set message
	    issueBookResponseDto.setTransactionDate(transaction.getTransactionDate()); // Set transaction date
	    issueBookResponseDto.setIsIssuedOperation(transaction.getIsissuedOperation()); // Set operation type

		log.info("Transaction successful with id - {}",transaction.getTransactionNumber());
	    return issueBookResponseDto;
	}

}
