<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>글 수정</title>
	
	    <!-- Custom fonts for this template -->
	    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	    <link
	        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	        rel="stylesheet">
	
	    <!-- Custom styles for this template -->
	    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
	    <link href="/resources/css/sb-admin-2.css" rel="stylesheet">
	    
	     <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.0-beta/css/bootstrap.min.css"> -->
		 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> 
		 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script> 
		 <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.0-beta/js/bootstrap.min.js"></script>  -->
		 
		 <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet"> 
		 <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>

		<!-- 썸머노트 -->
		<link rel="stylesheet" href="/resources/summernote/summernote-lite.min.css">
		<!-- 태그s 인풋 -->
		<link href="/resources/tagsinput/tagsinput.css" rel="stylesheet" type="text/css">
	
	</head>

	<body id="page-top">
	<%@include file="/WEB-INF/views/menubar.jsp"%>
    <!-- Page Wrapper -->
    <div id="wrapper">

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- 글 쓰기 타이틀 -->
                    <h1 class="h3 mb-2 text-gray-800">글 수정</h1>
                    
                    <form action="/freeBoard/freeBoardUpdate" method="post" id="frm">
	          			<!-- 제목 입력 -->
		                <div class="input-group mb-3">
		                	<div class="input-group-prepend">
							    <span class="input-group-text" id="basic-addon1">제목</span>
							</div>
							<input type="text" name="freeBoardTitle" class="form-control" aria-describedby="basic-addon1" value="${freeBoard.freeBoardTitle}">
						</div>
						
						<!-- 해시태그 -->						
						 <div class="input-group mb-3">
								<input type="text" data-role="tagsinput" placeholder="태그" id="hashTag" name="hashTag"
								value='<c:forEach items="${hashtags }" var="hash">${hash.hashTag },</c:forEach>'>						
  						 </div>
						
						<!-- 썸머노트(본문) -->
					    <textarea id="summernote" name="freeBoardContent" class="summernote">${freeBoard.freeBoardContent}</textarea>
						
							    		                 
		                 <div align="right">
						    <input type="submit" class="btn btn-primary" value="수정" id="update">
						    <input type="button" class="btn btn-secondary" value="취소" onclick="location.href='/freeBoard/freeBoardRead?freeBoardNum=${freeBoard.freeBoardNum };">
						</div>

	                    <!-- 게시판번호(PK)를 보내주는 보이지 않는 input -->
						<input type="hidden" name="freeBoardNum" value="${freeBoard.freeBoardNum }">          
					</form>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

        </div>
        <!-- End of Content Wrapper -->
         <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>요기도 머쓸까</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>
    
    <script type="text/javascript">	 
	    $(document).ready(function() {
			$('#summernote').summernote({
				 height: 300,                 // 에디터 높이
				  minHeight: null,             // 최소 높이
				  maxHeight: null,             // 최대 높이
				  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
				  lang: "ko-KR",					// 한글 설정
			      callbacks : { 
			    	  onImageUpload : function(files) {
			          // 파일 업로드(다중업로드를 위해 반복문 사용)
				          for (var i = files.length - 1; i >= 0; i--) {
				          	uploadSummernoteImageFile(files[i], this);
				          }
			          }
			       } 	
			}); 
		});
	
	  function uploadSummernoteImageFile(file, el) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "uploadSummernoteImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(el).summernote('editor.insertImage', data.url);
				}
			});
		}

	</script>
	
	<!-- 서머노트를 위해 추가해야할 부분 -->
  	<script src="/resources/summernote/summernote-lite.js"></script>
  	<script src="/resources/summernote/lang/summernote-ko-KR.js"></script> 
  	<!-- 태그s 인풋 -->
	<script src="/resources/tagsinput/tagsinput.js"></script>

</body>

</html>