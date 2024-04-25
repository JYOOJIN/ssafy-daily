<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
	<div class="navbar">
	
		<div>
		<a href="${root}/product/">홈</a>
		<c:if test="${userinfo.id eq 'admin'}">
			<a href="${root}/product/manage">상품 관리</a>
		</c:if>
		</div>
		<div>
			<c:if test="${empty userinfo}">
				<a href="${root}/product/login">로그인</a>
			</c:if>
			<c:if test="${not empty userinfo}">
        	${userinfo.name }님, 로그인 하였습니다.
        	<a href="${root}/product/logout">로그아웃</a>
        	</c:if>
			<a href="${root }/product/list">상품 목록</a> 
			<c:if test="${not empty userinfo}">
			<a href="${root }/product/regist">상품 정보 등록</a>
			</c:if>
		</div>
	</div>