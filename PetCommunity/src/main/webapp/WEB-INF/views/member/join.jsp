<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
			<!-- 부트스트랩 css -->
			<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
			<!-- 페이지 자체 css -->
			<link href="/resources/css/join.css" rel="stylesheet">
			<!-- jquery -->
			<script type="text/javascript" src="/resources/js/jquery-3.6.0.min.js"></script>
			<!-- 부트스트랩 js -->
			<script type="text/javascript"
				src="/resources/js/bootstrap.bundle.min.js"></script>
			<style type="text/css">
				.profileSyasin {
					border-radius : 70%;
					object-fit : cover;
					width : 202px;
					height : 202px;
					overflow : hidden;
					padding : .25rem;
					background-color : #fff;
					border : 1px solid #dddfeb;
				}
			</style>
		<title>회원가입</title>		
	</head>
	<body class="bg-gradient-primary">
		<div class="container-fluid ps-md-0">
			<div class="row g-0">
				<div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
				<div class="col-md-8 col-lg-6">
					<div class="login d-flex align-items-center py-5">
						<div class="container">
							<div class="row">
								<div class="col-md-9 col-lg-8 mx-auto">
									<h3 class="login-heading mb-4" style="text-align: center;">GBG</h3>
									<!-- Sign In Form -->
									<form method="POST" action="/member/join" class="needs-validation" novalidate enctype="multipart/form-data">
										<div class="text-center">
											<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" id="profileSyasin"
												class="profileSyasin" alt="avatar">
											
										</div>
										<input type="file" name="uploadFile" id="inputImage" class="text-center center-block file-upload"><br><br>
										<div class="form-floating mb-3">
											<input type="text" class="form-control" id="memberId"
												name="memberId" placeholder="ex) name@example.com" required>
											<label for="floatingInput">ID</label>
											<span class="id_input_re_1">사용 가능한 아이디 입니다.</span>
											<span class="id_input_re_2">사용 불가능한 아이디 입니다.</span>
											<div class="invalid-feedback">ID를 정확하게 입력하세요.</div>
										</div>
										<div class="form-floating mb-3">
											<input type="password" class="form-control"
												placeholder="PASSWORD" name="memberPw" id="floatingPassword"
												minlength="5" required> <label
												for="floatingPassword">PASSWORD</label>
											<div class="invalid-feedback">비밀번호를 정확하게 입력하세요.</div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control" name="memberNames"
												id="floatingPassword" placeholder="ex) 홍길동" required>
											<label for="floatingPassword">NAME</label>
											<div class="invalid-feedback">이름을 정확하게 입력하세요.</div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control" maxlength="10"
												name="memberBirth"
												pattern="^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$"
												onkeyup="this.value=this.value.replace(/(\d{4})(\d{2})(\d)/,'$1-$2-$3')"
												id="floatingPassword" placeholder="ex) 19900101" required><label
												for="floatingPassword">BIRTH</label>
											<div class="invalid-feedback">생년월일을 정확하게 입력하세요</div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control" name="memberPhone"
												id="floatingPassword" placeholder="ex) 01012341234" required>
											<label for="floatingPassword">PHONE NUMBER</label>
											<div class="invalid-feedback">핸드폰 번호를 정확하게 입력하세요.</div>
										</div>
										<div class="d-grid">
											<button class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2" type="submit">JOIN!!</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
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
		
		$('#memberId').on("propertychange change keyup paste input", function(){
			var memberId = $('#memberId').val();			// .id_input에 입력되는 값
			var data = {memberId : memberId}				// '컨트롤에 넘길 데이터 이름' : '데이터(.id_input에 입력되는 값)'
			
			$.ajax({
				type : "post",
				url : "/member/memberIdChk",
				data : data,
				success : function(result){
					if(result != 'fail'){
						$('.id_input_re_1').css("display","inline-block");
						$('.id_input_re_2').css("display", "none");				
					} else {
						$('.id_input_re_2').css("display","inline-block");
						$('.id_input_re_1').css("display", "none");				
					}
				}// success 종료
			}); // ajax 종료	
		});// function 종료
		
		$('#inputImage').change(function(){
		    setImageFromFile(this, '#profileSyasin');
		});

		function setImageFromFile(input, expression) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        reader.onload = function (e) {
		            $(expression).attr('src', e.target.result);
		        }
		        reader.readAsDataURL(input.files[0]);
		    }
		}
		</script>
	</body>
</html>
<!-- <!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
			부트스트랩 css
			<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
			페이지 자체 css
			<link href="/resources/css/join.css" rel="stylesheet">
			jquery
			<script type="text/javascript" src="/resources/js/jquery-3.6.0.min.js"></script>
			부트스트랩 js
			<script type="text/javascript"
				src="/resources/js/bootstrap.bundle.min.js"></script>
		<title>회원가입</title>
	</head>
	<body class="bg-gradient-primary">
		<div class="container-fluid ps-md-0">
			<div class="row g-0">
				<div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
				<div class="col-md-8 col-lg-6">
					<div class="login d-flex align-items-center py-5">
						<div class="container">
							<div class="row">
								<div class="col-md-9 col-lg-8 mx-auto">
									<h3 class="login-heading mb-4">Welcome our COMMUNITY!!</h3>
									Sign In Form
									<form method="POST" action="/member/join" class="needs-validation" novalidate enctype="multipart/form-data">
										<div class="text-center">
											<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
												class="avatar img-circle img-thumbnail" alt="avatar">
											<h6>Upload a different photo...</h6>
										</div>
										<input type="file" name="uploadFile"
											class="text-center center-block file-upload">
										<div class="form-floating mb-3">
											<input type="text" class="form-control" id="memberId"
												name="memberId" placeholder="ex) name@example.com" required>
											<label for="floatingInput">ID</label>
											<span class="id_input_re_1">사용 가능한 아이디 입니다.</span>
											<span class="id_input_re_2">사용 불가능한 아이디 입니다.</span>
											<div class="invalid-feedback">ID를 정확하게 입력하세요.</div>
										</div>
										<div class="form-floating mb-3">
											<input type="password" class="form-control"
												placeholder="PASSWORD" name="memberPw" id="floatingPassword"
												minlength="5" required> <label
												for="floatingPassword">PASSWORD</label>
											<div class="invalid-feedback">비밀번호를 정확하게 입력하세요.</div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control" name="memberNames"
												id="floatingPassword" placeholder="ex) 홍길동" required>
											<label for="floatingPassword">NAME</label>
											<div class="invalid-feedback">이름을 정확하게 입력하세요.</div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control" maxlength="10"
												name="memberBirth"
												pattern="^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$"
												onkeyup="this.value=this.value.replace(/(\d{4})(\d{2})(\d)/,'$1-$2-$3')"
												id="floatingPassword" placeholder="ex) 19900101" required><label
												for="floatingPassword">BIRTH</label>
											<div class="invalid-feedback">생년월일을 정확하게 입력하세요</div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control" name="memberPhone"
												id="floatingPassword" placeholder="ex) 01012341234" required>
											<label for="floatingPassword">PHONE NUMBER</label>
											<div class="invalid-feedback">핸드폰 번호를 정확하게 입력하세요.</div>
										</div>
										<div class="d-grid">
											<button
												class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2"
												type="submit">JOIN!!</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
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
		
		$('#memberId').on("propertychange change keyup paste input", function(){
			var memberId = $('#memberId').val();			// .id_input에 입력되는 값
			var data = {memberId : memberId}				// '컨트롤에 넘길 데이터 이름' : '데이터(.id_input에 입력되는 값)'
			
			$.ajax({
				type : "post",
				url : "/member/memberIdChk",
				data : data,
				success : function(result){
					if(result != 'fail'){
						$('.id_input_re_1').css("display","inline-block");
						$('.id_input_re_2').css("display", "none");				
					} else {
						$('.id_input_re_2').css("display","inline-block");
						$('.id_input_re_1').css("display", "none");				
					}
				}// success 종료
			}); // ajax 종료	
		});// function 종료
		</script>
	</body>
</html> -->