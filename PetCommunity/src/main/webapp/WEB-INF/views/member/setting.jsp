<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Setting</title>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="/resources/js/setting.js"></script>

<link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="/resources/css/sb-admin-2.css" rel="stylesheet">
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<%@include file="/WEB-INF/views/menubar.jsp"%>
	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-sm-10">
				<h1>${sessionScope.memberId}</h1>
			</div>
		</div>
		<div class="row">
		
				<!--left col-->
				<form class="form" action="/member/setting" method="post" id="registrationForm" enctype="multipart/form-data">
					<div class="text-center">
						<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
							class="avatar img-circle img-thumbnail" alt="avatar">
						<h6>Upload a different photo...</h6>
						<input type="file" name="uploadFile" class="text-center center-block file-upload">
					</div>
					<div class="tab-content">
						<div>
							<hr>
							<input type="hidden" name="memberId" id="memberId" value="${sessionScope.memberId }">
							<div class="form-group">
								<div class="col-xs-6">
									<label for="first_name"><h4>PASSWORD</h4></label> <input
										type="password" class="form-control" name="memberPw"
										id="memberPw" placeholder="PASSWORD"
										title="enter your first name if any.">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-6">
									<label for="last_name"><h4>NAME</h4></label> <input
										type="text" class="form-control" name="memberNames"
										id="memberNames" placeholder="NAME"
										title="enter your last name if any.">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-6">
									<label for="phone"><h4>PHONE</h4></label> <input type="text"
										class="form-control" name="memberPhone" id="memberPhone"
										placeholder="PHONE"
										title="enter your phone number if any.">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-6">
									<label for="mobile"><h4>BIRTH</h4></label> <input type="text"
										class="form-control" name="memberBirth" id="memberBirth"
										placeholder="BIRTH" pattern="^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$"
										onkeyup="this.value=this.value.replace(/(\d{4})(\d{2})(\d)/,'$1-$2-$3')"
										title="enter your mobile number if any.">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-12">
									<br>
									<button class="btn btn-lg btn-success" type="submit" onclick="fn_submit();">
										<i class="glyphicon glyphicon-ok-sign"></i> Save
									</button>
									<button class="btn btn-lg" type="reset">
										<i class="glyphicon glyphicon-repeat"></i> Reset
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	<script type="text/javascript">
	function fn_submit() {
	      
	      var memberPw = $("#memberPw").val();
	      if(memberPw.length < 5 || memnberPw.length > 20) {
	    	  alert("비밀번호를 다시 입력하세요. (5~30자리)");
	    	  return false;
	      } else if(memberPw == "") {
	    	  alert("비밀번호를 입력하세요.");
	    	  return false;
	      }
	      
	      var memberNames = $("#memberNames").val();
	      if(memberNames.length < 3 || memberNames > 30) {
	    	  alert("이름을 다시 입력ㅎ하세요(3~30자리)");
	    	  return false;
	      } else if(memberNames == "") {
	    	  alert("이름을 입력하세요");
	    	  return false;
	      }
	      
	      var phoneNum = document.getElementById('memberPhone').value;

	      var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	      if (regPhone.test(phoneNum) !== true) {
	          alert('휴대폰 번호를 다시 입력하세요');
	          return false;
	      }
	      
	      var memberBirth = $("#memberBirth").val();
	      if(memberBirth == "") {
	    	  alert("생년월일을 입력해주세요.");
	    	  return false;
	      }
	      
	      return true;
	  }
	</script>
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="/resources/vendor/chart.js/Chart.min.js"></script>	
</body>
</html>