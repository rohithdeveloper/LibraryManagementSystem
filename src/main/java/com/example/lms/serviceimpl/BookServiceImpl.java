package com.example.lms.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dtos.BookRequestDto;
import com.example.lms.dtos.BookResponseDto;
import com.example.lms.exceptions.AuthorNotFoundException;
import com.example.lms.model.Author;
import com.example.lms.model.Book;
import com.example.lms.repository.AuthorRepository;
import com.example.lms.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    // Injecting the AuthorRepository to interact with the database
    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        // Fetch the Author entity based on the ID from the BookDto
        Optional<Author> authorOptional = authorRepo.findById(bookRequestDto.getAuthorId());
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();

            // Create a new Book entity and set its attributes using the data from BookDto
            Book book = new Book();
            book.setTitle(bookRequestDto.getTitle());
            book.setGenre(bookRequestDto.getGenre());
            book.setPrice(bookRequestDto.getPrice());
            book.setGenre(bookRequestDto.getGenre());
            book.setIssued(false); // Initial state: Not issued
            book.setAuthor(author);

            // Add the newly created book to the author's list of books
            author.getBooks().add(book);
            
            // Save the updated Author entity to the database
            authorRepo.save(author);

            // Create a new BookDto for the response
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setTitle(book.getTitle());
            bookResponseDto.setPrice(book.getPrice());
            bookResponseDto.setGenre(book.getGenre());

            // Return the BookDto representing the newly added book
            return bookResponseDto;
        } else {
            // Handle author not found
            throw new AuthorNotFoundException("Author not found");
        }
    }
}