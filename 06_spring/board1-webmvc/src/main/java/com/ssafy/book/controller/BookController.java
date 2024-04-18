package com.ssafy.book.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RedirectAttributesMethodArgumentResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.controller.BoardController;
import com.ssafy.board.model.BoardDto;
import com.ssafy.util.PageNavigation;

import jakarta.servlet.http.HttpSession;

import com.ssafy.book.model.BookDto;
import com.ssafy.member.model.MemberDto;

@Controller
@RequestMapping("/book")
public class BookController {
	
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		//logger.debug("list parameter pgno : {}", map.get("pgno"));
		ModelAndView mav = new ModelAndView();
		
		List<BookDto> books=new ArrayList<>();
		
		books.add(new BookDto("111-222-3333", "홍길동", "책제목1", 10000, "좋은 책 1", "abc1.png"));
		books.add(new BookDto("111-222-4444", "임꺽정", "책제목2", 20000, "좋은 책 2", "abc2.png"));
		books.add(new BookDto("111-333-4444", "장길산", "책제목3", 30000, "좋은 책 3", "abc3.png"));
		
		
		mav.addObject("books",books);
		
		return mav;

	}
	
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> map, Model model) {
		logger.debug("write call parameter {}", map);
		return "book/write";
	}
	
	@PostMapping("/write")
	public String write(BookDto bookDto, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		
		
		return "redirect:/book/list";
	}
	
}
