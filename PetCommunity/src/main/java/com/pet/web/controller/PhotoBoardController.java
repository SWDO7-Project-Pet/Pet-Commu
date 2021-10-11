package com.pet.web.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pet.web.service.PhotoBoardService;
import com.pet.web.util.FileService;

import com.pet.web.vo.PhotoBoardSaveVO;
import com.pet.web.vo.PhotoBoardVO;
import com.pet.web.vo.PhotoHashtagVO;
import com.pet.web.vo.PhotoLikesVO;
import com.pet.web.vo.PhotoReplyVO;


@Controller
@RequestMapping(value = "/photoBoard")
public class PhotoBoardController {
	// 사진게시판 컨트롤러
	private static final Logger logger = LoggerFactory.getLogger(PhotoBoardController.class);

	@Autowired
	PhotoBoardService service;
	
	/* 사진게시판 메인 */
	@RequestMapping(value = "/photoBoardMain", method = RequestMethod.GET)
	public String photoBoardMain(Model model,HttpSession session) {
		logger.info("photoBoardMain 메소드 실행(GET)");
		
		ArrayList<PhotoBoardVO> photoBoardList = service.selectAllPhotoBoard();
		model.addAttribute("photoBoardList",photoBoardList);
		String memberId = (String)session.getAttribute("memberId");
		session.setAttribute("memberId", memberId);
		//logger.info("photoBoardList: {}", photoBoardList);
			
		ArrayList<HashMap<String, Object>> photoLikesList =service.allFavorite(memberId);
	//	logger.info("photoLikesList: {}", photoLikesList);
		model.addAttribute("photoLikesList",photoLikesList);
		
		ArrayList<PhotoHashtagVO> photoHashtagList = service.allPBHashTag();
	//	logger.info("photoHashtagList: {}", photoHashtagList);
		model.addAttribute("photoHashtagList",photoHashtagList);
		
		ArrayList<PhotoBoardSaveVO> photoBoardSaveList = service.allPhotoBoardsave();
	//	logger.info("photoBoardSaveList: {}", photoBoardSaveList);
		model.addAttribute("photoBoardSaveList",photoBoardSaveList);
		
		ArrayList<PhotoReplyVO> photoReplyList = service.allPhotoReply();
	//	logger.info("photoReplyList: {}", photoReplyList);
		model.addAttribute("photoReplyList",photoReplyList);

		
		
		
		return "photoBoard/photoBoardMain";
	}

	/* 사진게시판 작성 이동 */
	@RequestMapping(value = "/photoBoardWrite", method = RequestMethod.GET)
	public String photoBoardWrite() {
		logger.info("photoBoardWrite 메소드 실행(GET)");

		return "photoBoard/photoBoardWrite";
	}
	
	/* 댓글전체 읽어와서 ajax로 구현할려고 만든거 */
	@ResponseBody
	@RequestMapping(value = "/replyList", method = RequestMethod.POST)
	public ArrayList<PhotoReplyVO> replyList() {
		logger.info("replyList 메소드 실행(POST)");
		ArrayList<PhotoReplyVO> photoReplyList = service.allPhotoReply();
		logger.info("photoReplyList: {}", photoReplyList);
		
		return photoReplyList;
	}
	

	/* 사진게시판 작성하고나서 메인돌아가기 */
	@RequestMapping(value = "/photoBoardWrite", method = RequestMethod.POST)
	public String photoBoardWrite(String photoBoardTitle,String photoBoardContent
			,String hashTag,MultipartFile uploadFile,MultipartHttpServletRequest mtfRequest,HttpSession session) {
		logger.info("photoBoardWrite 메소드 실행(POST)");
		logger.info("photoBoardTitle: {}", photoBoardTitle);
		logger.info("photoBoardContent: {}", photoBoardContent);
		logger.info("uploadFile: {}", uploadFile);
		logger.info("originalFileName: {}",uploadFile.getOriginalFilename());
		logger.info("hashTag: {}",hashTag);
		logger.info("mtfRequest: {}",mtfRequest);
		
		String savedFileName = FileService.saveFile(uploadFile, "C:/workspace/petcommunity");
		String getOriginalFilename =uploadFile.getOriginalFilename();
		
		String memberId = (String)session.getAttribute("memberId");
		
		PhotoBoardVO photoBoard = new PhotoBoardVO();
		photoBoard.setPhotoBoardTitle(photoBoardTitle); 
		photoBoard.setPhotoBoardContent(photoBoardContent);
		photoBoard.setPhotoBoardThumbnailOr(getOriginalFilename);
		photoBoard.setPhotoBoardThumbnailSt(savedFileName);
		photoBoard.setMemberId(memberId);
		
		String returnUrl = null;

		int photoBoardResult = service.writeBoard(photoBoard);
		if(photoBoardResult == 1) {
			logger.info("사진게시판 저장 성공");
			returnUrl = "redirect:/photoBoard/photoBoardMain";
		}else {
			logger.info("사진게시판 저장 실패");
			returnUrl = "photoBoard/photoBoardWrite";
		}
		
		  // 게시글의 메인 이미지 저장(여러개 가능)
	      // fileList : 저장된 파일들을 저장한 리스트
	      List<MultipartFile> fileList = mtfRequest.getFiles("multiUploadFile");
	        String src = mtfRequest.getParameter("src");
	        System.out.println("src value : " + src);
	        
	        String path = "C:/workspace/petcommunity/";
	        //logger.info("fileList: {}",fileList);

		
	    int photoBoardNum = service.selectOnePhotoBoard(photoBoardTitle); 
			
		logger.info("photoBoardNum: {}", photoBoardNum);
		
		// 파일을 저장
		 for (MultipartFile mf : fileList) {
	            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
	            long fileSize = mf.getSize(); // 파일 사이즈

	            System.out.println("originFileName : " + originFileName);
	            System.out.println("fileSize : " + fileSize);

	            // 저장 파일명
	            String safeFile = System.currentTimeMillis() + originFileName;
	            // 파일 저장 경로 + 파일명
	            String safeFile2 = path + safeFile;
	            
	            // db에 저장
	            PhotoBoardSaveVO photoBoardSave = new PhotoBoardSaveVO();
	            photoBoardSave.setPhotoboardNum(photoBoardNum);
	            photoBoardSave.setPhotoBoardPhotoOr(originFileName);
	            photoBoardSave.setPhotoBoardPhotoSt(safeFile);
	            
	            int photoDbsave = service.uploadPhotoBoard(photoBoardSave);
	            
	            if(photoDbsave == 1)
	               logger.info("본문 사진 저장 완료");
	            else
	               logger.info("본문 사진 저장 실패");
	            
	            try {
	                mf.transferTo(new File(safeFile2));
	            } catch (IllegalStateException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        // ------------- 게시글의 메인 이미지 저장 끝 ------------          

		// 해시태그 쪼개서 넣기
		String[] hashtags = hashTag.split(",");
		PhotoHashtagVO photoHashtag = new PhotoHashtagVO();
		for(int i=1;i<hashtags.length;i++) {
			if(hashtags[i] != null) {
				photoHashtag.setPhotoBoardNum(photoBoardNum);
				photoHashtag.setHashtag(hashtags[i]);
				int hash = service.insertPBHashTag(photoHashtag);   
				logger.info("게시판 번호 : {}", photoBoardNum);
				logger.info("해시태그 : {}", hashtags[i]);
				if(hash == 1) 
					logger.info("해시태그 입력 성공"); 
				else 
					logger.info("해시태그 입력 실패");
			}
		}

		return returnUrl;
	}
	
	/* 사진게시판 업데이트 페이지로 이동 */
	@RequestMapping(value = "/photoBoardUpdate", method = RequestMethod.GET)
	public String photoBoardUpdate(int photoBoardNum,Model model) {
		
		  logger.info("photoBoardUpdate 메소드 실행(GET)");
		  logger.info("photoBoardNum: {}", photoBoardNum);
		  PhotoBoardVO photoBoard = new PhotoBoardVO();
		  photoBoard = service.photoBoardRead(photoBoardNum);
		  model.addAttribute("photoBoard",photoBoard);
		  logger.info("photoBoard: {}", photoBoard);

		return "photoBoard/photoBoardUpdate";
	}

	/* 게시글 수정하고 메인가기 */
	@RequestMapping(value = "/photoBoardUpdate", method = RequestMethod.POST)
	public String photoBoardUpdate(String photoBoardTitle,String photoBoardContent,String hashTag,int photoBoardNum,MultipartFile multiUploadFile,MultipartFile uploadFile,HttpSession session) {
		logger.info("photoBoardUpdate 메소드 실행(POST)");
		logger.info("photoBoardTitle: {}", photoBoardTitle);
		logger.info("photoBoardContent: {}", photoBoardContent);
		logger.info("uploadFile: {}", uploadFile);
		logger.info("originalFileName: {}",uploadFile.getOriginalFilename());
		logger.info("hashTag: {}",hashTag);
		logger.info("multiUploadFile: {}",multiUploadFile);
		logger.info("photoBoardNum: {}",photoBoardNum);
		
		/* 저장경로설정 */
		String savedFileName = FileService.saveFile(uploadFile, "C:\\workspace\\petcommunity");
		String getOriginalFilename =uploadFile.getOriginalFilename();
		String memberId = (String)session.getAttribute("memberId");
		/* 밑에 아이디 memberId 로 수정해야함 */
		
		PhotoBoardVO photoBoard = new PhotoBoardVO();
		photoBoard.setPhotoBoardTitle(photoBoardTitle); 
		photoBoard.setPhotoBoardContent(photoBoardContent);
		photoBoard.setPhotoBoardThumbnailOr(getOriginalFilename);
		photoBoard.setPhotoBoardThumbnailSt(savedFileName);
		photoBoard.setPhotoBoardNum(photoBoardNum);
		photoBoard.setMemberId(memberId);
		
		  
		  
		int photoBoardUpdateResult = service.photoBoardUpdate(photoBoard);
		if(photoBoardUpdateResult == 1) {
			logger.info("사진게시판 저장 성공");
		}else {
			logger.info("사진게시판 저장 실패");
		}
		
		int delHashTag = service.deletePBHashTag(photoBoardNum);
		if(delHashTag == 1) {
			logger.info("사진게시판 저장 성공");
		}else {
			logger.info("사진게시판 저장 실패");
		}
		
		
		// 해시태그 쪼개서 넣기
		String[] hashtags = hashTag.split(",");
		PhotoHashtagVO photoHashtag = new PhotoHashtagVO();
		for(int i=1;i<hashtags.length;i++) {
			if(hashtags[i] != null) {
				photoHashtag.setPhotoBoardNum(photoBoardNum);
				photoHashtag.setHashtag(hashtags[i]);
				int hash = service.insertPBHashTag(photoHashtag);   
				logger.info("게시판 번호 : {}", photoBoardNum);
				logger.info("해시태그 : {}", hashtags[i]);
				if(hash == 1) 
					logger.info("해시태그 입력 성공"); 
				else 
					logger.info("해시태그 입력 실패");
			}
		}
		
		
		 
		return "redirect:/photoBoard/photoBoardMain";
	}

	/* 사진게시판 삭제 */
	@RequestMapping(value="/photoBoardDelete", method = RequestMethod.GET)
	public String photoBoardDelete(int photoBoardNum) {
			logger.info("photoBoardDelete 메소드 실행(GET)");
			
		
			logger.info("photoBoardNum: {}", photoBoardNum);
			
			int result = service.photoBoardDelete(photoBoardNum);
			if(result == 1) {
				logger.info("게시글 삭제 성공");
			}else {
				logger.info("게시글 삭제 실패");
			}
			
		return "redirect:/photoBoard/photoBoardMain";
	}
	
	
	/* 사진게시판 상세보기 모달로하면서 없어도되는기능 */
	@RequestMapping(value = "/photoBoardRead", method = RequestMethod.GET)
	public String photoBoardRead(int photoBoardNum,Model model) {
		logger.info("photoBoardRead 메소드 실행(GET)");
		
		logger.info("photoBoardNum: {}", photoBoardNum);
		PhotoBoardVO photoBoard = new PhotoBoardVO();
		ArrayList<PhotoReplyVO> photoReplyList = new ArrayList<PhotoReplyVO>();
		
		
		photoBoard = service.photoBoardRead(photoBoardNum);
		photoReplyList = service.photoBoardReadReply(photoBoardNum);
		logger.info("photoBoard: {}", photoBoard);
		logger.info("photoReplyList: {}", photoReplyList);
		
		model.addAttribute("photoBoard",photoBoard);
		model.addAttribute("photoReplyList",photoReplyList);
		
		
		return "photoBoard/photoBoardRead";
	}
	
	/* @ResponseBody ajax한다면 필요 아직 구현못함*/
	@RequestMapping(value = "/photoBoardReplyWrite", method = RequestMethod.POST)
	public String photoBoardReplyWrite(int photoBoardNum,String photoReplyContent, HttpSession session) {
		logger.info("photoBoardReplyWrite 메소드 실행(POST)");
		
		logger.info("photoBoardNum: {}", photoBoardNum);
		logger.info("photoReplyContent: {}", photoReplyContent);
		
		String memberId = (String) session.getAttribute("memberId");
		
		PhotoReplyVO photoReply = new PhotoReplyVO();
		photoReply.setPhotoBoardNum(photoBoardNum);
		photoReply.setPhotoReplyContent(photoReplyContent);
		photoReply.setMemberId(memberId);
		
		int result = service.photoBoardReplyWrite(photoReply);
		if(result == 1) {
			logger.info("댓글 작성 성공.");
		}else {
			logger.info("댓글 작성 실패.");
		}
		return "redirect:/photoBoard/photoBoardMain";
	}

	/* 댓글 업데이트 ajax 로 근데 지금 실행하면 return 이상한데감 */
	@ResponseBody
	@RequestMapping(value="/photoBoardReplyUpdate", method = RequestMethod.POST)
	public void photoBoardReplyUpdate(String photoReplyContent, int photoReplyNum, int photoBoardNum) {
		logger.info("photoBoardReplyUpdate 메소드 실행(POST)");
		
		logger.info("photoReplyContent: {}",photoReplyContent);
		logger.info("photoReplyNum: {}",photoReplyNum);
		logger.info("photoBoardNum: {}",photoBoardNum);
		PhotoReplyVO photoReply = new PhotoReplyVO();
		photoReply.setPhotoReplyContent(photoReplyContent);
		photoReply.setPhotoReplyNum(photoReplyNum);
		
		int result = service.photoBoardReplyUpdate(photoReply);
		if(result == 1) {
			logger.info("댓글 수정 성공");
		}else {
			logger.info("댓글 수정 실패");
		}
	//	return "redirect:/photoBoard/photoBoardMain";
	}

	/* 댓글 삭제 ajax */
	@ResponseBody
	@RequestMapping(value="/photoBoardReplyDelete", method = RequestMethod.GET)
	public void photoBoardReplyDelete(int photoReplyNum, int photoBoardNum) {
			logger.info("photoBoardReplyDelete 메소드 실행(GET)");
			
			logger.info("photoReplyNum: {}", photoReplyNum);
			logger.info("photoBoardNum: {}", photoBoardNum);
			
			int result = service.photoBoardReplyDelete(photoReplyNum);
			if(result == 1) {
				logger.info("댓글 삭제 성공");
			}else {
				logger.info("댓글 삭제 실패");
			}
			
		//	return "";
	}

	/* 게시물 좋아요기능 */
	@ResponseBody
	@RequestMapping(value="/photoBoardInsertFavorite", method = RequestMethod.POST)
	public void photoBoardInsertFavorite(int photoBoardNum,HttpSession session) {
		logger.info("photoBoardInsertFavorite 메소드 실행(POST)");
		
		String memberId = (String) session.getAttribute("memberId");
		PhotoLikesVO photoLikes = new PhotoLikesVO();
		photoLikes.setMemberId(memberId);
		photoLikes.setPhotoBoardNum(photoBoardNum);
		logger.info("memberId:{}",memberId);
		logger.info("photoBoardNum:{}",photoBoardNum);
		int result = service.photoBoardInsertFavorite(photoLikes);
		if(result ==1) {
			logger.info("좋아요 성공");
		}else {
			logger.info("좋아요 실패");
		}
		
		service.photoBoardUpdateFavorite(photoBoardNum);
	}
	
	/* 좋아요 기능 취소 */
	@ResponseBody
	@RequestMapping(value="/photoBoardDeleteFavorite", method = RequestMethod.POST)
	public void photoBoardDeleteFavorite(int photoBoardNum,HttpSession session) {
		logger.info("photoBoardDeleteFavorite 메소드 실행(POST)");
		
		String memberId = (String) session.getAttribute("memberId");
		PhotoLikesVO photoLikes = new PhotoLikesVO();
		photoLikes.setMemberId(memberId);
		photoLikes.setPhotoBoardNum(photoBoardNum);
		logger.info("memberId:{}",memberId);
		logger.info("photoBoardNum:{}",photoBoardNum);
		int result = service.photoBoardDeleteFavorite(photoLikes);
		if(result ==1) {
			logger.info("좋아요 취소 성공");
		}else {
			logger.info("좋아요 취소 실패");
		}
		
		service.photoBoardUpdateFavoriteCancel(photoBoardNum);
	}
	
   // 썸머노트에서 그림을 넣으면 petcommutity 폴더로 이미지 파일 저장을 위한 경로 리턴
   @ResponseBody
   @RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")   
   public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, 
         HttpServletRequest request )  {
	  JsonObject jsonObject = new JsonObject();
           
      String fileRoot = "C:/workspace/petcommunity/"; // 외부경로로 저장을 희망할때.
          
      // 내부경로로 저장
      //String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
      //String fileRoot = contextRoot+"resources/fileupload/";
      
      String originalFileName = multipartFile.getOriginalFilename();   //오리지날 파일명
      String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   //파일 확장자
      String savedFileName = UUID.randomUUID() + extension;   //저장될 파일 명
      
      // 경로 + 저장이름
      File targetFile = new File(fileRoot + savedFileName);
      try {
         InputStream fileStream = multipartFile.getInputStream();
         FileUtils.copyInputStreamToFile(fileStream, targetFile);   //파일 저장
         jsonObject.addProperty("url", "/petcommunity/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
         jsonObject.addProperty("responseCode", "success");
            
      } catch (IOException e) {
         FileUtils.deleteQuietly(targetFile);   //저장된 파일 삭제
         jsonObject.addProperty("responseCode", "error");
         e.printStackTrace();
      }
      String a = jsonObject.toString();
      
      return a;
   }
	   

	
}
