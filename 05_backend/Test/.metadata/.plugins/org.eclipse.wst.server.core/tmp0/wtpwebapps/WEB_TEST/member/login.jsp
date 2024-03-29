<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- 시작 페이지에 컨텍스트 패스 동적으로 가져와서 설정하기 -->
<% 
	String root= request.getContextPath();
	application.setAttribute("root", root);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<style>
nav {
	display: flex;
	justify-content: space-between;
	align-items: center
}

nav a {
	margin-right: 10px
}
</style>
</head>
<body>
	<nav>
		<h2>로그인</h2>
	</nav>

	<form id="loginForm" method="POST" action="<%=application.getAttribute("root")%>/member?action=login">
		<input type="hidden" name="action" value="login">
		<div>
			<label for="productCode">아이디</label> <input type="text"
				id="userId" name="userId" placeholder="아이디 입력" required="required">
		</div>
		<div>
			<label for="model">비밀번호</label> <input type="password"
				id="userPassword" name="userPassword" placeholder="비밀번호 입력" required="required">
		</div>
		
		<button type="submit" id="login">로그인</button>
		<a href="#">취소</a>
	</form>
	
	<!-- 로그인 버튼 이벤트 리스너 script 작성하기 -->
<%-- 	<script>
		document.querySelector("#login").addEventListener("click",function(){
			let form=document.querySelector("loginForm");
			form.setAttribute("action",<%=application.getAttribute("root")%>/product?action=login);
			form.submit();
		})
	
	
	</script> --%>
</body>
</html>