package com.pet.web.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.pet.web.service.FreeBoardService;
import com.pet.web.service.MemberService;
import com.pet.web.vo.FreeBoardVO;
import com.pet.web.vo.FreeHashtagVO;
import com.pet.web.vo.FreeReplyVO;
import com.pet.web.vo.MemberVO;

@Controller
@RequestMapping(value = "/freeBoard")
public class FreeBoardController {
	// 자유게시판 컨트롤러
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private FreeBoardService service;	
	
	@Autowired
	private MemberService memberService;
	
	// 자유게시판 홈
	@RequestMapping(value = "/freeBoardMain", method = RequestMethod.GET)
	public String freeBoardMain(Model model, HttpSession session) {
		logger.info("freeBoardMain 메소드 실행(GET)");
		
		// 자유게시판 전체 정보를 담을 리스트
		ArrayList<FreeBoardVO> freeBoardList = service.selectAllFreeBoard();
		logger.info("freeBoardList: {}", freeBoardList);
		model.addAttribute("freeBoardList", freeBoardList);
		
		// 해시태그 전체 조회
		ArrayList<FreeHashtagVO> allHash = service.searchAllHashtag();
		logger.info("allHash: {}", allHash);
		model.addAttribute("allHash", allHash);
				
		return "freeBoard/freeBoardMain";
	}
			
	// 자유게시판 글 쓰기로 이동
	@RequestMapping(value = "/freeBoardWrite", method = RequestMethod.GET)
	public String freeBoardWrite() {
		logger.info("freeBoardWrite 메소드 실행(GET)");
		
		return "freeBoard/freeBoardWrite";
	}
	
	// 자유게시판 글 쓰기
	@RequestMapping(value = "/freeBoardWrite", method = RequestMethod.POST)
	public String freeBoardWrite(String freeBoardTitle, String freeBoardContent, String memberId
			,String hashTag) {
		logger.info("freeBoardWrite 메소드 실행(POST)");
				
		// 글 쓰기
		boolean result = service.freeBoardWrite(freeBoardTitle,freeBoardContent,memberId);
		
		String returnUrl = null;
		
		if(result) {
			logger.info("게시글 작성 성공");
			returnUrl = "redirect:/freeBoard/freeBoardMain";
		} else {
			logger.info("게시글 작성 실패");
			returnUrl = "freeBoard/freeBoardWrite";
		}
		
		// 가장 최신글을 가져온다
		int freeBoardNum = service.selectBoardNum();
		
		// 해시태그 쪼개서 넣기
		String[] hashtags = hashTag.split(",");
		for(int i=0;i<hashtags.length;i++) {
			if(hashtags[i] != null) {
				boolean hash = service.insertHashTag(freeBoardNum, hashtags[i]);	
				logger.info("게시판 번호 : {}", freeBoardNum);
				logger.info("해시태그 : {}", hashtags[i]);
				if(hash) 
					logger.info("해시태그 입력 성공"); 
				else 
					logger.info("해시태그 입력 실패");
			}			
		}
		
		return returnUrl;
	}
	
	// 해시 태그 쓰기(이건 안씀) ---
	@RequestMapping(value = "/freeBoardHashTag", method = RequestMethod.POST)
	public String freeBoardHashTag(int freeBoardNum, String hashTag, Model model) {
		logger.info("freeBoardHashTag 메소드 실행(POST)");
				
		// 글 가져오기
		FreeBoardVO freeBoard = service.freeBoardRead(freeBoardNum);
		logger.info("freeBoard: {}", freeBoard); 
		model.addAttribute("freeBoard",freeBoard);
		
		// 현재 글의, 해시 태그 여부 확인
		HashMap<String, Object> hashresult = service.checkHashTag(freeBoardNum, hashTag);			
		logger.info("hashresult(map): {}", hashresult);
		if (hashresult != null)
			model.addAttribute("hashTagMap", hashresult);
		  
		// 해시태그 넣기 
		boolean hash = service.insertHashTag(freeBoardNum, hashTag);		  
		if(hash) 
			logger.info("해시태그 입력 성공"); 
		else 
			logger.info("해시태그 입력 실패");
				 
		return "freeBoard/freeBoardRead";
	}
	
	// 글 상세보기
	@RequestMapping(value = "/freeBoardRead", method = RequestMethod.GET)
	public String freeBoardRead(int freeBoardNum, Model model, HttpSession session) {
		logger.info("freeBoardRead 메소드 실행(GET)");
		
		// 조회수 증가
		service.updateHitsCount(freeBoardNum);
		
		// 글 조회하기
		FreeBoardVO freeBoard = service.freeBoardRead(freeBoardNum);
		logger.info("freeBoard: {}", freeBoard);
		model.addAttribute("freeBoard", freeBoard);	
		
		// 댓글
		ArrayList<FreeReplyVO> freeReplyList = service.readAllFreeReply(freeBoardNum);
		logger.info("freeReplyList : {}", freeReplyList);
	    model.addAttribute("freeReplyList", freeReplyList);
	    
	    // 댓글 개수
	    int replyCount = service.replyCount(freeBoardNum);
	    model.addAttribute("replyCount", replyCount);
	    
	    // 현재 접속자의, 해당 게시글에 대한 좋아요 표시 여부 확인하기   
	    String memberId = (String) session.getAttribute("memberId");
	 	logger.info("memberId: {}", memberId); 	 	
	 	if(memberId != null) {
	 		HashMap<String, Object> result = service.checklikes(memberId, freeBoardNum);
		 	logger.info("likeresult(map): {}", result); 
		 	if (result != null)
		 		model.addAttribute("likesMap", result);
	 	}
	 					
		// 글에 달린 해시태그 조회
		ArrayList<FreeHashtagVO> hashtags = service.searchHashtag(freeBoardNum);
		model.addAttribute("hashtags", hashtags);
		logger.info("hashtags :{}", hashtags);
		
		return "freeBoard/freeBoardRead";
	}
		
	// 글 삭제
	@RequestMapping(value = "/freeBoardDelete", method = RequestMethod.POST)
	public String freeBoardDelete(int freeBoardNum) {
		logger.info("freeBoardDelete 메소드 실행(POST)");
		logger.info("freeBoardNum: {}", freeBoardNum);
		
		// 게시글 제거
		boolean result = service.freeBoardDelete(freeBoardNum);
		if(result) {
			logger.info("게시글 삭제 성공");
		} else {
			logger.info("게시글 삭제 실패");
		}
				
		// 글 삭제시 저장된 사진 삭제
		
		return "redirect:/freeBoard/freeBoardMain";
	}
	
	// 글 수정으로 이동
	@RequestMapping(value = "/freeBoardUpdate", method = RequestMethod.GET)
	public String freeBoardUpdate(int freeBoardNum, Model model) {
		logger.info("freeBoardUpdate 메소드 실행(GET)");
		
		FreeBoardVO freeBoard = service.freeBoardRead(freeBoardNum);
		ArrayList<FreeHashtagVO> hashtags = service.searchHashtag(freeBoardNum);
		
		model.addAttribute("freeBoard", freeBoard);
		model.addAttribute("hashtags", hashtags);
		
		return "freeBoard/freeBoardUpdate";
	}
	
	// 글 수정
	@RequestMapping(value = "/freeBoardUpdate", method = RequestMethod.POST)
	public String freeBoardUpdate(int freeBoardNum, String freeBoardTitle, String freeBoardContent,
			String hashTag) {
		logger.info("freeBoardUpdate 메소드 실행(POST)");
		
		// 기존 해시태그 삭제 후
				boolean dHash = service.deleteHashtag(freeBoardNum);
				if(dHash) {
					logger.info("게시글 삭제 성공");
				} else {
					logger.info("게시글 삭제 실패");
				}
				
				// 새로운 수정한 해시 태그 추가
				String[] hashtags = hashTag.split(",");
				for(int i=0;i<hashtags.length;i++) {
					if(hashtags[i] != null) {
						boolean hash = service.insertHashTag(freeBoardNum, hashtags[i]);	
						logger.info("게시판 번호 : {}", freeBoardNum);
						logger.info("해시태그 : {}", hashtags[i]);
						if(hash) 
							logger.info("해시태그 입력 성공"); 
						else 
							logger.info("해시태그 입력 실패");
					}			
				}
		
		
		// 글 수정
		boolean result = service.freeBoardUpdate(freeBoardNum, freeBoardTitle, freeBoardContent);		
		if(result) {
			logger.info("게시글 수정 성공");
		} else {
			logger.info("게시글 수정 실패");
		}
		
		return "redirect:/freeBoard/freeBoardRead?freeBoardNum=" + freeBoardNum;
	}	
	
	// 자유게시판 댓글 작성
	@RequestMapping(value = "/freeReplyWrite", method = RequestMethod.POST)
	public String freeReplyWrite(int freeBoardNum, String freeReplyContent, HttpSession session) {
		logger.info("freeReplyWrite 메소드 실행(POST)");
		String memberId = (String) session.getAttribute("memberId");
		
		// 작성시 댓글 개수 올라감
		service.increaseReplyNum(freeBoardNum);
		
		// 댓글 작성
		boolean result = service.freeReplyWrite(freeBoardNum, memberId, freeReplyContent);
		
		if(result) {
			logger.info("댓글 작성 성공");
		} else {
			logger.info("댓글 작성 실패");
		}
		
		return "redirect:/freeBoard/freeBoardRead?freeBoardNum=" + freeBoardNum;
	}		
	
	// 자유게시판 댓글 삭제
	@RequestMapping(value="/freeReplyDelete", method = RequestMethod.GET)
	public String freeReplyDelete(int freeReplyNum, int freeBoardNum) {
		logger.info("freeReplyDelete 메소드 실행(GET)");
		
		logger.info("freeReplyNum: {}", freeReplyNum);
		logger.info("freeBoardNum: {}", freeBoardNum);
		
		// 댓글 삭제시 댓글 수 감소
		service.reductionReplyNum(freeBoardNum);
		
		boolean result = service.freeReplyDelete(freeReplyNum);
		
		if(result) {
			logger.info("댓글 삭제 성공");
		} else {
			logger.info("댓글 삭제 실패");
		}
		
		return "redirect:/freeBoard/freeBoardRead?freeBoardNum=" + freeBoardNum;
	}

	// 댓글 수정
	@RequestMapping(value="/freeReplyUpdate", method = RequestMethod.POST)
	public String freeReplyUpdate(String freeReplyContent, int freeReplyNum, int freeBoardNum) {
		logger.info("freeReplyUpdate 메소드 실행(POST)");
		
		logger.info("freeReplyContent : {}", freeReplyContent);
		logger.info("freeReplyNum : {}", freeReplyNum);
		logger.info("freeBoardNum : {}", freeBoardNum);
		
		boolean result = service.freeReplyUpdate(freeReplyContent, freeReplyNum);
		
		if(result)
			logger.info("댓글 수정 성공");
		else
			logger.info("댓글 수정 실패");
				
				
		return "redirect:/freeBoard/freeBoardRead?freeBoardNum=" + freeBoardNum;
	}
	
   
	// 댓글 개수
	@ResponseBody
 	@RequestMapping(value="/replyCount", method = RequestMethod.GET)
 	public int replyCount(int freeBoardNum, Model model) {
 		logger.info("replyCount 메소드 실행(POST)");
 		
 		// 댓글 개수
 	    int replyCount2 = service.replyCount(freeBoardNum);
 	    model.addAttribute("replyCount2", replyCount2);
 							
 		return replyCount2;
 	}
	
	
	// 좋아요
	@ResponseBody
	@RequestMapping(value = "/likes", method = RequestMethod.POST)
	public void likes(int freeBoardNum, HttpSession session) {
		logger.info("likes 메소드 실행(POST)");
		
		String memberId = (String) session.getAttribute("memberId");
		
		// 좋아요 누른 아이디,글 저장
		boolean result = service.recordLikes(memberId, freeBoardNum);		
		logger.info("좋아요 기록 {}", result ? "성공" : "실패");
		
		// 추천수 증가
		logger.info("freeBoardNum: {}", freeBoardNum);
		service.increaseLikesNum(freeBoardNum);				
	}
	
	// 좋아요 취소
	@ResponseBody
	@RequestMapping(value = "/cancelLikes", method = RequestMethod.POST)
	public void cancelLikes(int freeBoardNum, HttpSession session) {
		logger.info("cancelLikes 메소드 실행(POST)");
		
		String memberId = (String) session.getAttribute("memberId");
		
		// 좋아요 취소
		boolean result2 = service.deleteLikes(memberId, freeBoardNum);
		logger.info("좋아요 취소 {}", result2 ? "성공" : "실패");
		
		// 추천수 감소
		logger.info("freeBoardNum: {}", freeBoardNum);
		service.reductionLikesNum(freeBoardNum);
				
	}
	
	// 좋아요 수
	@ResponseBody
	@RequestMapping(value = "/countLikes", method = RequestMethod.POST)
	public void countLikes(int freeBoardNum, HttpSession session) {
		logger.info("countLikes 메소드 실행(POST)");
		
		// count : 글의 좋아요 수
		int count = service.countLikes(freeBoardNum);
		logger.info("count: {}", count);		
	}
	
	// 검색
	@RequestMapping(value = "/searchFreeBoard", method = RequestMethod.GET)
	public String searchFreeBoard(String condition, String searchWord, Model model) {
		logger.info("searchFreeBoard 메소드 실행(GET)");
			
		logger.info("condition : {}", condition);
		logger.info("searchWord : {}", searchWord);
			
		ArrayList<FreeBoardVO> freeBoardList =  service.searchFreeBoard(condition, searchWord);
		logger.info("검색 결과: {}", freeBoardList.size());
		model.addAttribute("freeBoardList", freeBoardList);
		
		// 해시태그 전체 조회
		ArrayList<FreeHashtagVO> allHash = service.searchAllHashtag();
		logger.info("allHash: {}", allHash);
		model.addAttribute("allHash", allHash);
								
		return "freeBoard/freeBoardMain";
	}
	
	// 메뉴바 통합 검색
	@RequestMapping(value = "/searchAllFreeBoard", method = RequestMethod.GET)
	public String searchAllFreeBoard(String searchWord, Model model) {
		logger.info("searchAllFreeBoard 메소드 실행(GET)");
			
		logger.info("searchWord : {}", searchWord);
			
		ArrayList<FreeBoardVO> freeBoardList =  service.searchAllFreeBoard(searchWord);
		logger.info("검색 결과: {}", freeBoardList.size());
		model.addAttribute("freeBoardList", freeBoardList);
		
		// 해시태그 전체 조회
		ArrayList<FreeHashtagVO> allHash = service.searchAllHashtag();
		logger.info("allHash: {}", allHash);
		model.addAttribute("allHash", allHash);
								
		return "freeBoard/freeBoardMain";
	}
	
	
	
	// 해시태그 검색
	@RequestMapping(value = "/freeBoardfindTag", method = RequestMethod.GET)
	public String freeBoardfindTag(String searchTag, Model model) {
		logger.info("freeBoardfindTag 메소드 실행(POST)");
		
		// 해시태그 전체 조회
		ArrayList<FreeHashtagVO> allHash = service.searchAllHashtag();
		logger.info("allHash: {}", allHash);
		model.addAttribute("allHash", allHash);
		
		// 해시태그로 검색된 결과 list
		ArrayList<FreeBoardVO> findTag = service.freeBoardfindTag(searchTag);
		logger.info("findTag: {}", findTag);
		model.addAttribute("findTag", findTag);
		
		return "freeBoard/freeBoardfindTag";
	}
		
	// 썸머노트에서 그림을 넣으면 petcommutity 폴더로 이미지 파일 저장을 위한 경로 리턴
	@ResponseBody
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")	
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, 
			HttpServletRequest request )  {
		JsonObject jsonObject = new JsonObject();
			     
		String fileRoot = "C:/workspace/free/"; // 외부경로로 저장을 희망할때.
			 	
			// 내부경로로 저장
			//String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
			//String fileRoot = contextRoot+"resources/fileupload/";
			
			String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
			String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
			
			// 경로 + 저장이름
			File targetFile = new File(fileRoot + savedFileName);
			try {
				InputStream fileStream = multipartFile.getInputStream();
				FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
				jsonObject.addProperty("url", "/free/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
				jsonObject.addProperty("responseCode", "success");
					
			} catch (IOException e) {
				FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
				jsonObject.addProperty("responseCode", "error");
				e.printStackTrace();
			}
			String a = jsonObject.toString();
			
			return a;
		}
	
}
