<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import ="com.ssafy.member.model.dto.MemberDto" %>

<!-- jstl 사용 위한 선언 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- 로그인 전 메뉴 구성 -->
<%
	MemberDto memberInfo=(MemberDto) session.getAttribute("memberInfo");
	if(memberInfo==null){
	
%>


<a href="<%=application.getAttribute("root")%>/member?action=mvlogin">로그인</a>

<%
	} else{
%>

<!-- 로그인 후 메뉴 구성 -->
<div>
	<span> <%=memberInfo.getUserName() %>님 로그인 중</span> 
	<a href="<%=application.getAttribute("root")%>/member?action=logout">로그아웃</a>
	<a href="<%=application.getAttribute("root")%>/product?action=list">상품목록</a>
	<a href="<%=application.getAttribute("root")%>/product?action=mvregist">상품등록</a>
</div>

<%
	}
%>
