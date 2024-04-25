package com.ssafy.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.model.dto.Member;
import com.ssafy.model.dto.Product;
import com.ssafy.model.service.MemberService;
import com.ssafy.model.service.ProductService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;


@Controller
@RequestMapping("/product")
public class MainController {
	
	@Autowired
	private ServletContext servletContext;
	
	private ProductService productService;
	private MemberService memberService;
	
	public MainController(ProductService productService,MemberService memberService) {
		super();
		this.productService=productService;
		this.memberService=memberService;
	}
	
	
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/list")
	public ModelAndView list() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<Product> list = productService.listProduct();
		mav.addObject("products", list);
		mav.setViewName("list");
		return mav;
	}
	
	@GetMapping("/manage")
	public ModelAndView manage() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<Product> list = productService.listProduct();
		mav.addObject("products", list);
		mav.setViewName("manage");
		return mav;
	}
	
	@GetMapping("/regist")
	public String write(@RequestParam Map<String, String> map, Model model) {
		return "/regist";
	}
	
	@PostMapping("/regist")
	public String write(Product productDto,RedirectAttributes redirectAttributes) throws Exception {

		productService.registProduct(productDto);
		return "redirect:/product/list";
	}
	
	@GetMapping("/view/{code}")
	public String view(@PathVariable("code") String code, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		Product productDto = productService.getProduct(code);
		model.addAttribute("product", productDto);
		return "/view";
	}
	
	
	@GetMapping("/delete/{code}")
	public String delete(@PathVariable("code") String code, RedirectAttributes redirectAttributes) throws Exception {
		productService.deleteProduct(code);
		return "redirect:/product/manage";
	}
	
	@GetMapping("/modify/{code}")
	public String modify(@PathVariable("code") String code, Model model)
			throws Exception {
		Product productDto=productService.getProduct(code);
		model.addAttribute("product", productDto);
		return "/modify";
	}

	@PostMapping("/modify")
	public String modify(Product productDto,RedirectAttributes redirectAttributes) throws Exception {
		productService.updateProduct(productDto);
		return "redirect:/product/manage";
	}
	
	@GetMapping("/search")
	public ModelAndView search(String searchDate) throws Exception {
		
		if(searchDate.isEmpty()) {
			ModelAndView mav = new ModelAndView();
			List<Product> list = productService.listProduct();
			mav.addObject("products", list);
			mav.setViewName("list");
	        return mav;
		}
		
		String[] sdate=searchDate.split("-");
		String year=sdate[0];
		String month=sdate[1];
		String day=sdate[2];
		
		ModelAndView mav = new ModelAndView();
		List<Product> list = productService.searchProduct(year, month, day);
		mav.addObject("products", list);
		mav.addObject("searchDate",searchDate);
		mav.setViewName("list");
		return mav;
		
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		try {
			Member memberDto = memberService.login(map);
			if(memberDto != null) {
				session.setAttribute("userinfo", memberDto);
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
}
