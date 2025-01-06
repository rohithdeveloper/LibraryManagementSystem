package com.example.lms.scheduler;

import com.example.lms.dtos.BookRequestDto;
import com.example.lms.exceptions.AuthorNotFoundException;
import com.example.lms.serviceimpl.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class BookServiceScheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    private BookServiceImpl bookService;

    // Schedule the addBook task to execute once after a specified delay
    public void scheduleAddBook(BookRequestDto bookRequestDto, long delayInSeconds) {
        Runnable task = () -> {
            try {
                log.info("Attempting to add a book with title: {}", bookRequestDto.getTitle());
                bookService.addBook(bookRequestDto);
                log.info("Book added successfully.");
            } catch (AuthorNotFoundException e) {
                log.error("Error adding book: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Unexpected error occurred: {}", e.getMessage());
            }
        };
        scheduler.schedule(task, delayInSeconds, TimeUnit.SECONDS);
    }


    // Schedule the addBook task to execute periodically at a fixed rate
    public void scheduleAddBookAtFixedRate(BookRequestDto bookRequestDto, long initialDelay, long periodInSeconds) {
        Runnable task = () -> {
            try {
                log.info("Attempting to add a book with title: {}", bookRequestDto.getTitle());
                bookService.addBook(bookRequestDto);
                log.info("Book added successfully.");
            } catch (AuthorNotFoundException e) {
                log.error("Error adding book: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Unexpected error occurred: {}", e.getMessage());
            }
        };
        scheduler.scheduleAtFixedRate(task, initialDelay, periodInSeconds, TimeUnit.SECONDS);
    }

    // Gracefully shut down the scheduler
    public void shutdownScheduler() {
        scheduler.shutdown();
        log.info("Scheduler has been shut down.");
    }
}
