<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
	<nav>
		<h1>상품 목록 페이지</h1>
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
		</thead>
		<tbody>

		</tbody>
	</table>
</body>
</html>