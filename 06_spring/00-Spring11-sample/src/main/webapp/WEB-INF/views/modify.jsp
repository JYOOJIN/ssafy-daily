<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리 사이트</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.navbar {
	background-color: #333;
	color: white;
	padding: 10px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.navbar a {
	color: white;
	text-decoration: none;
	padding: 10px 15px;
}

.container {
	max-width: 800px;
	margin: 20px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label, input, textarea, button {
	display: block;
	width: 100%;
	margin-top: 10px;
}

input, textarea {
	padding: 8px;
	box-sizing: border-box;
}

button {
	padding: 10px 15px;
	margin-top: 10px;
	background-color: #333;
	color: white;
	border: none;
	cursor: pointer;
}

button.btn-secondary {
	background-color: #777;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/nav.jsp" %>
	
	<div
		style="max-width: 800px; margin: 20px auto; padding: 20px; background-color: white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
		<h2>상품 정보 수정</h2>
		<form id="registForm" method="post" action="/product/modify">
			<label for="productCode">고유번호</label> 
			<input type="text" id="productCode" name="code" value="${product.code }" readonly="readonly"> 
			<label for="model">모델명</label> 
			<input type="text" id="model" name="model" value="${product.model }"> 
			<label for="price">가격</label>
			<input type="number" id="price" name="price" value="${product.price }"> 
			<label for="id">등록자 ID</label> 
			<input type="text" id="id" name="id" value=${userinfo.id } readonly="readonly"> 
			<label for="description">상세설명</label>
			<textarea id="description" name="detail"> ${product.detail }</textarea>

			<button type="submit" class="btn-primary">수정</button>
			<button type="button" class="btn-secondary"
				onclick="location.href=''">취소</button>
		</form>
	</div>
</body>
</html>
