<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.84.0">
<title>Login</title>
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">
<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.5.0.min.css" rel="stylesheet">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="/resources/css/login.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin">
		<form method="POST" action="/member/login" class="needs-validation" novalidate>
			<img class="mb-4" src="/resources/img/logo.png"
				alt="" width="72" height="72">
			<h1 class="h3 mb-3 fw-normal">WELCOME BACK!!</h1>

			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInput" name="memberId" required
					placeholder="namenamename"> <label for="floatingInput">ID</label>
				<div class="invalid-feedback">
					ID가 올바르지 않습니다.
				</div>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" name="memberPw" required
					placeholder="Password"> <label for="floatingPassword">PASSWORD</label>
				<div class="invalid-feedback">
					PW가 올바르지 않습니다.
				</div>
			</div>
			<c:if test="${msg == false}">
					<p style="color:#f00;">로그인에 실패했습니다. 아이디 또는 비밀번호를 다시 입력하세요.</p>
				</c:if>
			<button class="w-100 btn btn-lg btn-primary" type="submit">Login</button>
			
			<p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
		</form>
		
		<script type="text/javascript">
		'use strict';

		let forms = document.querySelectorAll('.needs-validation');

		[].slice.call(forms).forEach( form => {
		  form.addEventListener('submit', event => {
		    if (!form.checkValidity()) {
		      event.preventDefault();
		      event.stopPropagation();
		    }
		    form.classList.add('was-validated');
		  }, false)
		});
		</script>
	</main>
</body>
</html>
