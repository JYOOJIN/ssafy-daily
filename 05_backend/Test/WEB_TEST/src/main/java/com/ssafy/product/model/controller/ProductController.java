package com.ssafy.product.model.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.ssafy.product.model.dao.ProductDaoImpl;
import com.ssafy.product.model.dto.ProductDto;
import com.ssafy.product.model.service.ProductService;
import com.ssafy.product.model.service.ProductServiceImpl;

// Servlet 클래스 생성 시에 web.xml 자동으로 <url-pattern>/product/</url-pattern> 생성
// @WebServlet("/product") //web.xml에 있기 때문에 다시 쓰면 안된다

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String root;
	private ProductService service = ProductServiceImpl.getInstance();
	
	public void init() throws ServletException{
		root=getServletContext().getContextPath();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		System.out.println(action);
		
		switch(action) { 
			case "list": //목록조회
				list(request,response);
				break;
			case "get": //상세조회
				get(request,response);
				break;
			case "mvregist": //등록창으로 이동
				mvregist(request,response);
				break; 
			case "regist": //등록
				regist(request,response);
				break;
			case "mvmodify": //수정창으로 이동
				mvmodify(request,response);
				break;
			case "modify": //수정
				modify(request,response);
				break;
			case "delete": //삭제
				delete(request,response);
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
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("degub:list");
		try {
			List<ProductDto> list=service.listProduct();
			System.out.println("debug list:" + list);
			request.setAttribute("products", list);
			
			RequestDispatcher nextView=request.getRequestDispatcher("/product/list.jsp");
			nextView.forward(request, response);
			
		} catch (Exception e) {
			
			request.setAttribute("error", "[오류]목록보기에서 오류가 발생했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		}	
		
	}
	
	protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code=request.getParameter("code");
		
	}
	
	protected void mvregist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("debug: mvregist");
		response.sendRedirect(root+"/product/regist.jsp");
	}
	
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("debug: regist");
		
		String code=request.getParameter("code");
		String model=request.getParameter("model");
		int price=Integer.parseInt(request.getParameter("price"));
		
		ProductDto product=new ProductDto();
		product.setCode(code);
		product.setModel(model);
		product.setPrice(price);
		
		try {
			service.registProduct(product);
			response.sendRedirect(root+"/product?action=list");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(root+"/index.jsp");
		}
	
	}
	
	protected void mvmodify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("debug: mvmodify");
		
	}
	protected void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("debug: modify");
		
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("debug: delete");
		
	}


}
