
package com.example.lms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dtos.AuthorRequestDto;
import com.example.lms.dtos.AuthorResponseDto;
import com.example.lms.dtos.BookRequestDto;
import com.example.lms.dtos.BookResponseDto;
import com.example.lms.model.Author;
import com.example.lms.model.Book;
import com.example.lms.repository.AuthorRepository;
import com.example.lms.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
	    // Create Author entity from DTO
	    Author author = new Author();
	    author.setName(authorRequestDto.getName());
	    author.setAge(authorRequestDto.getAge());
	    author.setMobno(authorRequestDto.getMobno());
	    author.setEmail(authorRequestDto.getEmail());

	 // Save the author entity first
	    Author savedAuthor = authorRepo.save(author);

	    // Handle the books if they exist in the request DTO
	    if (authorRequestDto.getBooks() != null && !authorRequestDto.getBooks().isEmpty()) {
	        List<Book> books = authorRequestDto.getBooks().stream()
	                .map(bookRequestDto -> {
	                    Book book = new Book();
	                    book.setTitle(bookRequestDto.getTitle());
	                    book.setPrice(bookRequestDto.getPrice());
	                    book.setGenre(bookRequestDto.getGenre());
	                    book.setIssued(false); // Default value
	                    book.setAuthor(savedAuthor); // Set the saved author for the book
	                    return book;
	                }).collect(Collectors.toList());

	        // Set the books for the author
	        savedAuthor.setBooks(books);

	        // No need to call save again; the cascade should handle it
	        authorRepo.save(savedAuthor);
	    }

	 

	    // Map saved Author entity to AuthorResponseDto
	    AuthorResponseDto authorResponseDto = modelMapper.map(savedAuthor, AuthorResponseDto.class);

	    // Convert list of Book entities to list of BookResponseDto using ModelMapper
	    List<BookResponseDto> bookResponseDtos = savedAuthor.getBooks().stream()
	            .map(book -> modelMapper.map(book, BookResponseDto.class)).collect(Collectors.toList());

	    authorResponseDto.setBooks(bookResponseDtos);

	    return authorResponseDto;
	}


	@Override
	public List<AuthorResponseDto> getAuthor() {
		// Retrieve all Author entities from the repository
		List<Author> authors = authorRepo.findAll();

		// Convert the list of Author entities to a list of AuthorResponseDto using
		// ModelMapper
		return authors.stream().map(author -> {
			// Map Author to AuthorResponseDto
			AuthorResponseDto authorResponseDto = modelMapper.map(author, AuthorResponseDto.class);

			// Convert list of Book entities to list of BookResponseDto using ModelMapper
			List<BookResponseDto> bookDtos = author.getBooks().stream()
					.map(book -> modelMapper.map(book, BookResponseDto.class)).collect(Collectors.toList());

			authorResponseDto.setBooks(bookDtos); // Set the list of books in the AuthorResponseDto
			return authorResponseDto;
		}).collect(Collectors.toList());
	}
}

