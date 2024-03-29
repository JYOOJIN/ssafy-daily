<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ssafy.product.model.dto.ProductDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

nav {
display : flex;
justify-content : space-between;
align-items : center
}
nav a {
margin-right: 10px
}
</style>
</head>
<body>

<%
	List<ProductDto> list= (List<ProductDto>)request.getAttribute("products");
%>
	<nav>
		<h1>상품 목록 페이지</h1>
		<!-- 로그인 전/후 메뉴 조각 페이지 삽입 -->
		<%@ include file="/nav.jsp" %>
	</nav>
	<a href="#">메인 페이지</a>
	<br>
	<a href="#">등록하기</a>
	<table>
		<thead>
			<tr>
				<th>고유번호</th>
				<th>모델명</th>
				<th>가격</th>
			</tr>
			<%
				for(ProductDto product:list){
			%>
			<tr>
				<td><a href=""><%= product.getCode() %></a>
				<td><%= product.getModel() %>
				<td><%= product.getPrice() %>
			</tr>
			<%
				}
			%>
		</thead>
		<tbody>

		</tbody>
	</table>
</body>
</html>