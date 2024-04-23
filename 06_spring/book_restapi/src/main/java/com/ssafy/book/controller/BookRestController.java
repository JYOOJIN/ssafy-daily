package com.ssafy.book.controller;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.util.StringUtils;
import com.ssafy.book.model.BookDto;
import com.ssafy.book.model.service.BookService;
import com.ssafy.member.controller.MemberController;


@RestController
@RequestMapping("/bookapi")
@CrossOrigin("*")
public class BookRestController {
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private BookService bookService;

	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}
	
	//전체목록
	@GetMapping("/list")
	public ResponseEntity<?> list() {
		logger.debug("bookList");
		
		try {
			List<BookDto> list=bookService.list();
			return new ResponseEntity<List<BookDto>>(list,HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
		
	}

	
	//상세보기
	@GetMapping("/view/{isbn}")
	public ResponseEntity<?> view(@PathVariable("isbn") String isbn) {
		logger.debug("bookView "+ isbn);
		try {
			BookDto bookDto=bookService.view(isbn);
			return new ResponseEntity<BookDto>(bookDto,HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
		
	}


	//등록
	@PostMapping("/register")
	public ResponseEntity<?> regist(@RequestBody BookDto bookDto) {
		logger.debug("regist",bookDto);
		try {
			bookService.register(bookDto);
			List<BookDto> list=bookService.list();
			return new ResponseEntity<List<BookDto>>(list,HttpStatus.OK); 
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
		
	}

	//수정
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody BookDto bookDto) {

		logger.debug("modify",bookDto);
		
		try {
			bookService.modify(bookDto);
			List<BookDto> list=bookService.list();
			return new ResponseEntity<List<BookDto>>(list, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}

	}

	//삭제
	@DeleteMapping("/delete/{isbn}")
	public ResponseEntity<?> delete(@PathVariable("isbn") String isbn) {
		
		logger.debug("delete",isbn);
		try {
			bookService.delete(isbn);
			List<BookDto> list=bookService.list();
			return new ResponseEntity<List<BookDto>>(list, HttpStatus.OK);
			
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
		
	}

	//예외
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
