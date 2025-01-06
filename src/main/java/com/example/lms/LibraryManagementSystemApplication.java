package com.example.lms;

import com.example.lms.dtos.BookRequestDto;
import com.example.lms.enums.Genre;
import com.example.lms.scheduler.BookServiceScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@Slf4j
@SpringBootApplication
public class LibraryManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private BookServiceScheduler bookServiceScheduler;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) {
		// Create a sample BookRequestDto
		BookRequestDto bookRequestDto = new BookRequestDto();
		bookRequestDto.setTitle("The Great Adventure");
		bookRequestDto.setGenre(Genre.BIOGRAPHY);
		bookRequestDto.setPrice(200);
		bookRequestDto.setAuthorId(1); // Adjust as per your database


		// Schedule tasks
		log.info("Scheduling a one-time book addition with a delay of 5 seconds.");
		bookServiceScheduler.scheduleAddBook(bookRequestDto, 5);

		log.info("Scheduling periodic book addition every 5 seconds after an initial delay of 5 seconds.");
		bookServiceScheduler.scheduleAddBookAtFixedRate(bookRequestDto, 5, 5);

		// Schedule the shutdown of the scheduler after 1 minute
		log.info("Scheduler will shut down after 1 minute.");
		bookServiceScheduler.scheduleShutdown(60);
	}
}
