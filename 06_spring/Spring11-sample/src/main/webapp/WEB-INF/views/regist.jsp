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
		<h2>상품 정보 등록</h2>
		<form id="registForm" method="post" action="">
			<label for="productCode">고유번호</label> 
			<input type="text" id="productCode" name="code" placeholder="고유번호 입력" autofocus="true"> 
			<label for="model">모델명</label> 
			<input type="text" id="model" name="model" placeholder="모델명 입력"> 
			<label for="price">가격</label>
			<input type="number" id="price" name="price" placeholder="가격 입력"> 
			<label for="id">등록자 ID</label> 
			<input type="text" id="id" name="id" value=${userinfo.id } readonly=readonly> 
			<label for="description">상세설명</label>
			<textarea id="description" name="detail"></textarea>
			
			<button type="button" id="btn-register" class="btn-primary">등록</button>
			<button type="button" id="btn-list" class="btn-secondary">취소</button>
		</form>
		
	    <script>
	      document.querySelector("#btn-register").addEventListener("click", function () {
	        if (!document.querySelector("#productCode").value) {
	          alert("코드 입력!!");
	          return ;
	        } else if (!document.querySelector("#model").value) {
	          alert("이름 입력!!");
	          return;
	        } else if (!document.querySelector("#price").value) {
	          alert("가격 입력!!");
	          return;
		    }
	        else {
	          let form = document.querySelector("#registForm");
	          form.setAttribute("action", "${root}/product/regist");
	          form.submit();
	        }
	      });
	      
	      document.querySelector("#btn-list").addEventListener("click", function () {
	      	if(confirm("취소를 하시면 작성한 글은 삭제됩니다.\n취소하시겠습니까?")) {
	      		let form = document.querySelector("#form-param");
	         	form.setAttribute("action", "${root}/product/list");
	            form.submit();
	     	}
	      });
	    </script>
	</div>
</body>
</html>
