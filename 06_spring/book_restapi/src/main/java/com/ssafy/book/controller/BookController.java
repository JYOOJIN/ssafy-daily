package com.ssafy.book.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.util.StringUtils;
import com.ssafy.book.model.BookDto;
import com.ssafy.book.model.service.BookService;
import com.ssafy.member.controller.MemberController;

@Controller
@RequestMapping("/book")
public class BookController {
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/list")
	public String list(Model model) throws SQLException {
		model.addAttribute("books", bookService.list());
		return "book/list";
	}

	@GetMapping("/view")
	public String view(@RequestParam("bookIsbn") String isbn, Model model) throws SQLException {
		model.addAttribute("book", bookService.view(isbn));
		return "book/view";
	}

	@GetMapping("/register")
	public String write() {
		return "book/register";
	}

	@PostMapping("/register")
	public String write(BookDto bookDto) throws SQLException {
		
		  if(StringUtils.isNullOrEmpty(bookDto.getImg())) { 
			  bookDto.setImg("No Image");
		  }
		 
		System.out.println(bookDto);
		bookService.register(bookDto);
		return "redirect:/book/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("isbn") String isbn) throws SQLException {
		bookService.delete(isbn);
		return "redirect:/book/list";
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("isbn") String isbn, Model model) throws SQLException {
		System.out.println("Test");
		model.addAttribute("book", bookService.view(isbn));
		return "book/modify";
	}

	@PostMapping("/modify")
	public String modify(BookDto bookDto, Model model) throws SQLException {
		if(StringUtils.isNullOrEmpty(bookDto.getImg())) {
			bookDto.setImg("No Image");
		}
		bookService.modify(bookDto);
		model.addAttribute("book", bookDto);
		return "book/view";
	}
}
