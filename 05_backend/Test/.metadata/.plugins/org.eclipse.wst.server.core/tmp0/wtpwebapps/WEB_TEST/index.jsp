<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- 시작 페이지에 컨텍스트 패스 동적으로 가져와서 설정하기 -->

<% 
	String root= request.getContextPath();
	application.setAttribute("root", root);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
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

	<!-- 로그인 전 메뉴구성 -->
	<!-- 조각 페이지 추가하기 -->
	<nav>
		<h1>메인 페이지 입니다.</h1>
		<%@ include file="nav.jsp" %>
	</nav>
	
	<br>
	<br>
	<!-- 로그인 후 메뉴 구성 -->
	
	

</body>
</html>