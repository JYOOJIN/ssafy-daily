<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ssafy.product.model.dto.ProductDto"%>
<%
String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 조회</title>
<style type="text/css">
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}

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
    <%
    ProductDto products = (ProductDto) request.getAttribute("product");
    %>
    <nav>
        <h1>상품 상세 페이지</h1>
        <%@ include file="/nav.jsp"%>
    </nav>

    <main>
        <p>코드: ${product.code}</p>
        <p>모델: ${product.model}</p>
        <p>${product.code}원</p>
        <div>
            <a>삭제</a>
            <a>수정</a>
        </div>
    </main>
</body>
</html>