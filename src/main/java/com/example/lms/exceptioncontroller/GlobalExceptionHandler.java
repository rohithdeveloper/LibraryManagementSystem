package com.example.lms.exceptioncontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.lms.dtos.IssueBookResponseDto;
import com.example.lms.enums.TransactionStatus;
import com.example.lms.exceptions.BookIssueException;
import com.example.lms.exceptions.BookNotFoundException;
import com.example.lms.exceptions.CardNotActivatedException;
import com.example.lms.exceptions.CardNotPresentException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");

            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return errorDetail;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
    
    @ExceptionHandler(CardNotActivatedException.class)
    public ResponseEntity<IssueBookResponseDto> handleCardNotActivatedException(CardNotActivatedException ex) {
        IssueBookResponseDto response = new IssueBookResponseDto();
        response.setTransactionStatus(TransactionStatus.FAILED);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookIssueException.class)
    public ResponseEntity<IssueBookResponseDto> handleBookIssueException(BookIssueException ex) {
        IssueBookResponseDto response = new IssueBookResponseDto();
        response.setTransactionStatus(TransactionStatus.FAILED);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<IssueBookResponseDto> handleBookNotFoundException(BookNotFoundException ex) {
        IssueBookResponseDto response = new IssueBookResponseDto();
        response.setTransactionStatus(TransactionStatus.FAILED);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardNotPresentException.class)
    public ResponseEntity<IssueBookResponseDto> handleCardNotPresentException(CardNotPresentException ex) {
        IssueBookResponseDto response = new IssueBookResponseDto();
        response.setTransactionStatus(TransactionStatus.FAILED);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
