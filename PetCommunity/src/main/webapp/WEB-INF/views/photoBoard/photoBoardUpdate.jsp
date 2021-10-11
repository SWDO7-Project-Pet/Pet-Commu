<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom fonts for this template -->
       <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
       <link
           href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
           rel="stylesheet">
   
       <!-- Custom styles for this template -->
       <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
       <link href="/resources/css/sb-admin-2.css" rel="stylesheet">
	   <!-- 해시태그s 인풋 -->
       <link href="/resources/tagsinput/tagsinput.css" rel="stylesheet" type="text/css">
		<!-- 부트스트랩 5.1.0-dist -->
		<link href="/resources/bootstrap-5.1.0-dist/css/bootstrap.min.css" rel="stylesheet">


<title>사진게시판 수정하기</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.0-beta/css/bootstrap.min.css">
       <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> 
       <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script> 
       <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.0-beta/js/bootstrap.min.js"></script> 
       
       <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet"> 
       <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>

      <!-- 썸머노트 -->
      <link rel="stylesheet" href="/resources/summernote/summernote-lite.min.css">
</head>
<body>

<div class="container-fluid">

                    <!-- 글 쓰기 타이틀 -->
                    <h1 class="h3 mb-2 text-gray-800">사진게시판 수정</h1>
                    
                    <form action="/photoBoard/photoBoardUpdate" method="post" id="frm" enctype="multipart/form-data">
                      <!-- 제목 입력 -->
                      <div class="input-group mb-3">
                         <div class="input-group-prepend">
                         <span class="input-group-text" id="basic-addon1">제목</span>
                     </div>
                     <input type="text" name="photoBoardTitle" class="form-control" value="${photoBoard.photoBoardTitle }">
                  </div>
                  
                  <!-- 해시태그 -->
                  <div class="input-group mb-3">
                         <input type="text" data-role="tagsinput" placeholder="태그" id="hashTag" name="hashTag">
                   </div>
                  
					<!-- 썸네일 사진 업로드 -->
                        <div class="mb-3">
                          <label for="formFile" class="form-label">썸네일사진</label>
                          <input class="form-control" type="file" id="uploadFile" name="uploadFile">
                        </div>

                        <!-- 본문 사진 업로드 -->
                        <div class="mb-3">
                          <label for="formFileMultiple" class="form-label">본문사진(5개까지)</label>
                          <input class="form-control" type="file" id="multiUploadFile" name="multiUploadFile" multiple>
                        </div>                  
                  
                  
                  
                  <!-- 썸머노트(본문) -->
                   <textarea id="summernote" name="photoBoardContent" class="summernote">${photoBoard.photoBoardContent }</textarea>
            
                                          
                       <div align="right">
                      <input type="submit" class="btn btn-primary" value="수정">
                      <input type="button" class="btn btn-secondary" value="취소" onclick="location.href='/photoBoard/photoBoardMain';">
                  </div>

                  
                       <input type="hidden" name="photoBoardNum" value="${photoBoard.photoBoardNum }">
               </form>
                </div>

<script type="text/javascript">
$(document).ready(function() {
    $('#summernote').summernote({
        height: 300,                 // 에디터 높이
         minHeight: null,             // 최소 높이
         maxHeight: null,             // 최대 높이
         focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
         lang: "ko-KR",               // 한글 설정
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
   

   <!-- Bootstrap core JavaScript-->
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../resources/js/sb-admin-2.min.js"></script>



   <!-- 서머노트를 위해 추가해야할 부분 -->
        <script src="/resources/summernote/summernote-lite.js"></script>
        <script src="/resources/summernote/summernote-ko-KR.js"></script>  

	<!-- 해시태그s 인풋 -->
    <script src="/resources/tagsinput/tagsinput.js"></script>
    
    <!-- 부트스트랩 5.1.0-dist -->
    <script type="text/javascript" src="/resources/bootstrap-5.1.0-dist/js/bootstrap.min.js"></script>

</body>
</html>