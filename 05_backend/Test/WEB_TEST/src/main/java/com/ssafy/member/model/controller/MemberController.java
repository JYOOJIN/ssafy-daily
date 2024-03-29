package com.ssafy.member.model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.servicce.MemberService;
import com.ssafy.member.model.servicce.MemberServiceImpl;
import com.ssafy.product.model.service.ProductService;
import com.ssafy.product.model.service.ProductServiceImpl;


public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String root;
	private MemberService service = MemberServiceImpl.getInstance();
	
	public void init() throws ServletException{
		root=getServletContext().getContextPath();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		System.err.println("debug action: "+action);
		
		switch(action) { 
			case "mvlogin": //로그인 창 이동
				mvlogin(request,response);
				break;
			case "login": //로그인
				login(request,response);
				break;
			case "logout": //로그아웃
				logout(request,response);
				break;
			default:
				response.sendRedirect(root+"/index.jsp");
				break;
		}
	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request,response);
	}
	
	
	protected void mvlogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// redirect는 context path를 반드시 표기하고 경로를 지정해야한다.
		System.out.println("debug: mvlogin");
		response.sendRedirect(root+"/member/login.jsp");
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("debug: login");
		
		//로그인 처리 프로세스
		
		// 1.로그인 페이지에서 로그인 정보 가져오기(name="userId", name="userPassword")
		String userId=request.getParameter("userId"); //name으로 가져옴
		String userPassword=request.getParameter("userPassword"); 
		
		try {
			// 2.서비스 객체에게 로그인 요청 의뢰하기
			//	: service에서 login 처리 - 로그인 성공하면 memberDto 반환
			MemberDto member=service.login(userId, userPassword);
			
			// 3.로그인 성공하면 로그인 사용자를 위한 HttpSession 객체 생성하기
			//	: true 일시, 존재하면 기존 session, 없으면 새로운 session 만들기
			HttpSession session=request.getSession(true);
			
			// 4.생성한 HttpSession에 로그인 정보 객체 설정하기
			session.setAttribute("memberInfo",member);
			
			// 5.로그인 성공 후 응답페이지 이동하기: 현재 프로젝트는 시작 페이지(index.jsp)
			//	: forward, redirect 상관없다. session이 정보를 가지고 있기 때문. 
			response.sendRedirect(root+"/index.jsp");
				
		} catch (Exception e) {
			// 6.로그인 실패 후 응답페이지 이동하기: 현재 프로젝트는 시작 페이지(index.jsp)
			response.sendRedirect(root+"/index.jsp");
		}
		
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("debug: logout");
		
		// 1.기존 세션 객체 가져오기
		HttpSession session=request.getSession();
		
		// 2 세션 존재하고, 로그인 인증 시에 설정한 속성 정보가 존재하는지 비교해서
		if(session!=null && session.getAttribute("memberInfo") !=null) {
			// 3. 로그인 인증받은 사용자이면 인증시에 저장한 속성 정보를 삭제하고: removeAttribute()
			session.removeAttribute("memberInfo");
			// 4. 세션 객체 삭제: invalidate()
			session.invalidate();
		}
		
		// 5. 로그아웃 응답 페이지 이동하기: 현재 프로젝트는 index.jsp
		response.sendRedirect(root+"/index.jsp");
	
	}
}
