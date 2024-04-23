<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/confirm.jsp"%>
<div class="row justify-content-center">
	<div class="col-lg-8 col-md-10 col-sm-12">
		<h2 class="my-3 py-3 shadow-sm bg-light text-center">
			<mark class="sky">글목록</mark>
		</h2>
	</div>
	<div class="col-lg-8 col-md-10 col-sm-12">
		<div class="row align-self-center mb-2">
			<div class="col-md-2 text-start">
				<button type="button" id="btn-mv-register"
					class="btn btn-outline-primary btn-sm">도서등록</button>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr class="text-center">
					<th scope="col">번호</th>
					<th scope="col">ISBN</th>
					<th scope="col">제목</th>
					<th scope="col">저자</th>
					<th scope="col">가격</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}" varStatus="loop">
					<tr class="text-center">
						<th scope="row">${loop.count}</th>
						<td>${book.isbn}</td>
						<td class="text-start"><a href="#"
							class="book-title link-dark" data-no="${book.isbn}"
							style="text-decoration: none"> ${book.title} </a></td>
						<td>${book.author}</td>
						<td>${book.price}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<form id="form-no-param" method="get" action="${root}/book/view">
	<input type="hidden" id="bookIsbn" name="bookIsbn" value="">
</form>
<form id="form-param" method="get" action=""></form>
<script>
	let titles = document.querySelectorAll(".book-title");
	titles.forEach(function(title) {
		title.addEventListener("click", function() {
			document.querySelector("#bookIsbn").value = this.getAttribute("data-no");
			document.querySelector("#form-no-param").submit();
		});
	});

	document.querySelector("#btn-mv-register").addEventListener("click",
			function() {
				let form = document.querySelector("#form-param");
				form.setAttribute("action", "${root}/book/register");
				form.submit();
			});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

