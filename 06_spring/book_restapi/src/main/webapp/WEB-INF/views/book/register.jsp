<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/confirm.jsp"%>
<div class="row justify-content-center">
	<div class="col-lg-8 col-md-10 col-sm-12">
		<h2 class="my-3 py-3 shadow-sm bg-light text-center">
			<mark class="sky">도서 등록</mark>
		</h2>
	</div>
	<div class="col-lg-8 col-md-10 col-sm-12">
		<form id="form-register" method="POST" action="">
			<div class="mb-3">
				<label for="isbn" class="form-label">도서번호 : </label> <input
					type="text" class="form-control" id="isbn" name="isbn" />
			</div>
			<div class="mb-3">
				<label for="isbn" class="form-label">도서명 : </label> <input
					type="text" class="form-control" id="title" name="title" />
			</div>
			<div class="mb-3">
				<label for="isbn" class="form-label">저자 : </label> <input
					type="text" class="form-control" id="author" name="author" />
			</div>
			<div class="mb-3">
				<label for="isbn" class="form-label">가격 : </label> <input
					type="number" class="form-control" id="price" name="price" />
			</div>
			<div class="mb-3">
				<label for="isbn" class="form-label">이미지 : </label> 
				<input type="file" id="img" name="img"/>
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">설명 : </label>
				<textarea class="form-control" id="description" name="description"
					rows="3"></textarea>
			</div>
			<div class="col-auto text-center">
				<button type="button" id="btn-register"
					class="btn btn-outline-primary mb-3">등록</button>
				<button type="button" id="btn-list"
					class="btn btn-outline-danger mb-3">취소</button>
			</div>
		</form>
	</div>
</div>
<form id="form-param" method="get" action="">
	<input type="hidden" id="pgno" name="pgno" value="${pgno}"> <input
		type="hidden" id="key" name="key" value="${key}"> <input
		type="hidden" id="word" name="word" value="${word}">
</form>
<script>
	document.querySelector("#btn-register").addEventListener("click",
			function() {
				if (!document.querySelector("#isbn").value) {
					alert("도서번호 입력!!");
					return;
				} else if (!document.querySelector("#title").value) {
					alert("제목 입력!!");
					return;
				} else if (!document.querySelector("#author").value) {
					alert("저자 입력!!");
					return;
				} else if (!document.querySelector("#price").value) {
					alert("가격 입력!!");
					return;
				} else if (!document.querySelector("#description").value) {
					alert("설명 입력!!");
					return;
				} else {
					let form = document.querySelector("#form-register");
					form.setAttribute("action", "${root}/book/register");
					form.submit();
				}
			});

	document.querySelector("#btn-list").addEventListener("click", function() {
		if (confirm("취소를 하시면 등록중인 도서는 삭제됩니다.\n취소하시겠습니까?")) {
			let form = document.querySelector("#form-param");
			form.setAttribute("action", "${root}/book/list");
			form.submit();
		}
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
