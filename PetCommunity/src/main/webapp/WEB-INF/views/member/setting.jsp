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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/setting.js"></script>
</head>
<body>
	<hr>
	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-sm-10">
				<h1>${sessionScope.memberId}</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<!--left col-->
				<form class="form" action="/member/setting" method="post" id="registrationForm" enctype="multipart/form-data">
					<div class="text-center">
						<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
							class="avatar img-circle img-thumbnail" alt="avatar">
						<h6>Upload a different photo...</h6>
						<input type="file" name="uploadFile" class="text-center center-block file-upload">
					</div>
					<div class="tab-content">
						<div class="tab-pane active" id="home">
							<hr>
							<input type="hidden" name="memberId" id="memberId" value="${sessionScope.memberId }">
							<div class="form-group">
								<div class="col-xs-6">
									<label for="first_name"><h4>PASSWORD</h4></label> <input
										type="password" class="form-control" name="memberPw"
										id="first_name" placeholder="PASSWORD"
										title="enter your first name if any.">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-6">
									<label for="last_name"><h4>NAME</h4></label> <input
										type="text" class="form-control" name="memberNames"
										id="last_name" placeholder="NAME"
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
										class="form-control" name="memberBirth" id="mobile"
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
	</div>
	<script type="text/javascript">
	function fn_submit() {
	      var phoneNum = document.getElementById('memberPhone').value;

	      var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	      if (regPhone.test(phoneNum) !== true) {
	          alert('휴대폰 번호를 다시 입력하세요');
	      }
	  }
	</script>
</body>
</html>