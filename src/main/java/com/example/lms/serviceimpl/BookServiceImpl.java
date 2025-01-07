package com.example.lms.serviceimpl;

import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dtos.BookRequestDto;
import com.example.lms.dtos.BookResponseDto;
import com.example.lms.exceptions.AuthorNotFoundException;
import com.example.lms.model.Author;
import com.example.lms.model.Book;
import com.example.lms.repository.AuthorRepository;
import com.example.lms.repository.BookRepository;
import com.example.lms.service.BookService;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;  // Inject the BookRepository

    @Override
    @Transactional
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Optional<Author> authorOptional = authorRepo.findById(bookRequestDto.getAuthorId());
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();

            // Create a new Book entity
            Book book = new Book();
            book.setTitle(bookRequestDto.getTitle());
            book.setGenre(bookRequestDto.getGenre());
            book.setPrice(bookRequestDto.getPrice());
            book.setIssued(false);
            book.setAuthor(author);

            // Add the book to the author's list
            author.getBooks().add(book);

            // Save the book to the database
            bookRepo.save(book);

            // Create the response DTO
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setId(book.getId());
            bookResponseDto.setTitle(book.getTitle());
            bookResponseDto.setPrice(book.getPrice());
            bookResponseDto.setGenre(book.getGenre());

            // Log the generated book ID
            log.info("Book added with id - {}", bookResponseDto.getId());

            return bookResponseDto;
        } else {
            throw new AuthorNotFoundException("Author not found");
        }
    }

}