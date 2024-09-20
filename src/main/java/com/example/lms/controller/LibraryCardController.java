//package com.example.lms.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.lms.dtos.LibraryCardRequestDto;
//import com.example.lms.dtos.LibraryCardResponseDto;
//import com.example.lms.service.LibraryCardService;
//
//@RestController
//@RequestMapping("/card")
//public class LibraryCardController {
//	
//	@Autowired
//	private LibraryCardService libService;
//
//	@PostMapping("/add")
//	public LibraryCardResponseDto addStudent(@RequestBody LibraryCardRequestDto libRequestDto) {
//
//		return libService.addCard(libRequestDto);
//	}
//}
