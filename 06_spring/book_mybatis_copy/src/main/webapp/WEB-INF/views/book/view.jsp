<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<c:if test="${book eq null}">
	<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/book/list";
	</script>
</c:if>
<%@ include file="/WEB-INF/views/common/confirm.jsp"%>
<div class="row justify-content-center">
	<div class="col-lg-8 col-md-10 col-sm-12">
		<h2 class="my-3 py-3 shadow-sm bg-light text-center">
			<mark class="sky">도서 관리</mark>
		</h2>
	</div>
	<div class="col-lg-8 col-md-10 col-sm-12">
		<div class="row my-2">
			<h2 class="text-secondary px-5">도서 등록 결과</h2>
		</div>
		<div class="row">
			<table>
				<tr>
					<th>항목</th>
					<th>내용</th>
				</tr>
				<tr>
					<td>도서번호</td>
					<td>${book.isbn}</td>
				</tr>
				<tr>
					<td>도서명</td>
					<td>${book.title}</td>
				</tr>
				<tr>
					<td>저자</td>
					<td>${book.author}</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>${book.price}</td>
				</tr>
				<tr>
					<td>이미지</td>
					<td>${book.img}</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>${book.description}</td>
				</tr>
			</table>
		</div>
		<div class="divider mt-3 mb-3"></div>
		<div class="d-flex justify-content-end">
			<button type="button" id="btn-list"
				class="btn btn-outline-primary mb-3">글목록</button>
			<button type="button" id="btn-mv-register"
				class="btn btn-outline-success mb-3 ms-1">추가 등록</button>
			<button type="button" id="btn-mv-modify"
				class="btn btn-outline-success mb-3 ms-1">수정</button>
			<button type="button" id="btn-delete"
				class="btn btn-outline-danger mb-3 ms-1">삭제</button>
		</div>
	</div>
</div>
<form id="form-param" method="get" action="">
	<input type="hidden" id="isbn" name="isbn" value="${book.isbn}">
</form>
<form id="form-no-param" method="get" action=""></form>
<script>
	document.querySelector("#btn-list").addEventListener("click", function() {
		let form = document.querySelector("#form-param");
		form.setAttribute("action", "${root}/book/list");
		form.submit();
	});
	document.querySelector("#btn-mv-register").addEventListener("click",
			function() {
				let form = document.querySelector("#form-param");
				form.setAttribute("action", "${root}/book/register");
				form.submit();
			});
	document.querySelector("#btn-delete").addEventListener("click", function() {
		if (confirm("정말 삭제하시겠습니까?")) {
			let form = document.querySelector("#form-param");
			form.setAttribute("action", "${root}/book/delete");
			form.submit();
		}
	});
	document.querySelector("#btn-mv-modify").addEventListener("click", function() {
		let form = document.querySelector("#form-param");
		form.setAttribute("action", "${root}/book/modify");
		form.submit();
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>