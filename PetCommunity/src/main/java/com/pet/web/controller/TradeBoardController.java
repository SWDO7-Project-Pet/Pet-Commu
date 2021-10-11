package com.pet.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.pet.web.service.TradeBoardService;
import com.pet.web.util.FileService;
import com.pet.web.util.PageNavigator;
import com.pet.web.vo.TradeBoardSaveVO;
import com.pet.web.vo.TradeBoardVO;

//거래게시판 컨트롤러
@Controller
@RequestMapping(value = "/tradeBoard")
public class TradeBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private TradeBoardService service;
	
	// 거래게시판 메인
	@RequestMapping(value = "/tradeBoardMain", method = RequestMethod.GET)
	public String tradeBoardMain(Model model, HttpServletRequest request) {
		logger.info("tradeBoardMain 메소드 실행(GET)");
		
		ArrayList<TradeBoardVO> tradeBoardList = service.selectAllTradeBoard();
		logger.info("tradeBoardList: {}", tradeBoardList);
		model.addAttribute("tradeBoardList", tradeBoardList);
				
		// 페이지당 글 수, 그룹 당 페이지 수, 현재 페이지 (최근 글이 1부터 시작), 전체 글 수 (DB에서 가져옴)
//		PageNavigator navi = new PageNavigator(COUNT_PER_PAGE, PAGE_PER_GROUP, currentPage, totalRecordsCount);
//		model.addAttribute("navi", navi); // jsp로 네비게이터 전달
		
//		ArrayList<HashMap<String, Object>> boardList = service.getBoardList(navi.getStartRecord(), COUNT_PER_PAGE);
//		logger.info("boardList: {}", boardList);
//		model.addAttribute("boardList", boardList);	// jsp로 게시글 전달		
						
		// 한 페이지당 보여줄 글 개수
		final int PAGE_ROW_COUNT = 12;
		
		// 보여줄 페이지 번호 초기값 1
		int pageNum = 1;
		// 페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum = request.getParameter("pageNum");
		// 만일 페이지 번호가 파라미터로 넘어 온다면
		if( strPageNum != null) {
			// 숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			pageNum = Integer.parseInt(strPageNum);
		}
		
		// 보여줄 페이지의 시작 ROWNUM - 0 부터 시작
		int startRowNum = 0 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;
		
		int rowCount = PAGE_ROW_COUNT;
		
		String keyword = request.getParameter("keyword");
	    String condition = request.getParameter("condition");
	    
	    if (keyword == null) {
	        // 키워드와 검색 조건에 빈 문자열을 넣어준다.
	        keyword = "";
	        condition = "";
	    }
	    
		// startRowNum 과 rowCount 를  TradeBoardVO 객체에 담는다.
		TradeBoardVO tb = new TradeBoardVO();
		tb.setStartRowNum(startRowNum);
		tb.setEndRowNum(endRowNum);
		tb.setRowCount(rowCount);
		
		// 만일 검색 키워드가 넘어온다면
	    if (!keyword.equals("")) { // 검색 조건이 무엇이냐에 따라 분기하기
	        if (condition.equals("tradeBoardTitle")) { // 제목 검색인 경우
	            // 검색 키워드를 PictureTO에 담아서 전달한다.
	        	tb.setTradeBoardTitle(keyword);
	        	System.out.print("제목:" + tb.getTradeBoardTitle());
	        } else if (condition.equals("tradeBoardContent")) { // 내용 검색인 경우
	            tb.setTradeBoardContent(keyword);
	            System.out.print("내용:" + tb.getTradeBoardContent());
	        } else if (condition.equals("memberId")) { // 작성자 검색인 경우
	            tb.setMemberId(keyword);
	            System.out.print("작성자:" + tb.getMemberId());
	        } 
	    }
		
		// ArrayList 객체의 참조값을 담을 지역변수를 미리 만든다.
		ArrayList<TradeBoardVO> list = service.getBoardList(tb);
		// 전체 글의 갯수	
		int totalRecordsCount = service.getTotalCount();
		// 전체 페이지 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRecordsCount / (double) PAGE_ROW_COUNT);
		
		request.setAttribute("list", list);
		request.setAttribute("totalPageCount", totalPageCount);
	    request.setAttribute("condition", condition);
	    request.setAttribute("keyword", keyword);
		request.setAttribute("totalRow", totalRecordsCount);
		// pageNum 도 추가로 담아주기
		request.setAttribute("pageNum", pageNum);
		    
		return "tradeBoard/tradeBoardMain";
	}
	
	@RequestMapping(value = "/ajax_page.do")
	public String picture_ajax_page(HttpServletRequest request, HttpSession session) {
				// 한 페이지당 보여줄 글 개수
				final int PAGE_ROW_COUNT = 12;
				
				// 보여줄 페이지 번호 초기값 1
				int pageNum = 1;
				// 페이지 번호가 파라미터로 전달되는지 읽어와 본다.
				String strPageNum = request.getParameter("pageNum");
				// 만일 페이지 번호가 파라미터로 넘어 온다면
				if( strPageNum != null) {
					// 숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
					pageNum = Integer.parseInt(strPageNum);
				}
											
				int startRowNum = 0 + (pageNum - 1) * PAGE_ROW_COUNT + 1;
				// 보여줄 페이지의 끝 ROWNUM
				int endRowNum = pageNum * PAGE_ROW_COUNT;				
				int rowCount = pageNum * PAGE_ROW_COUNT;
				
				String keyword = request.getParameter("keyword");
			    String condition = request.getParameter("condition");
			    
			    if (keyword == null) {
			        // 키워드와 검색 조건에 빈 문자열을 넣어준다.
			        keyword = "";
			        condition = "";
			    }
				
				// startRowNum 과 rowCount 를  TradeBoardVO 객체에 담는다.
				TradeBoardVO tb = new TradeBoardVO();
				tb.setStartRowNum(startRowNum);
				tb.setEndRowNum(endRowNum);
				tb.setRowCount(rowCount);
				
				// ArrayList 객체의 참조값을 담을 지역변수를 미리 만든다.
				//ArrayList<TradeBoardVO> list = null;
				
							
				// 만일 검색 키워드가 넘어온다면
			    if (!keyword.equals("")) { // 검색 조건이 무엇이냐에 따라 분기하기
			        if (condition.equals("tradeBoardTitle")) { // 제목 검색인 경우
			            // 검색 키워드를 PictureTO에 담아서 전달한다.
			        	tb.setTradeBoardContent(keyword);
			        } else if (condition.equals("tradeBoardContent")) { // 내용 검색인 경우
			            tb.setTradeBoardContent(keyword);
			        } else if (condition.equals("memberId")) { // 작성자 검색인 경우
			            tb.setMemberId(keyword);
			        } 
			    }

				ArrayList<TradeBoardVO> list = service.getBoardList(tb);
				
				// 전체 글의 갯수	
				int totalRecordsCount = service.getTotalCount();
				// 전체 페이지 갯수 구하기
				int totalPageCount = (int) Math.ceil(totalRecordsCount / (double) PAGE_ROW_COUNT);
								
				request.setAttribute("list", list);
				logger.info("list : {}", list);
				request.setAttribute("totalPageCount", totalPageCount);
				logger.info("totalPageCount : {}", totalPageCount);
				request.setAttribute("totalRow", totalRecordsCount);
				logger.info("totalRow : {}", totalRecordsCount);
			    request.setAttribute("condition", condition);
			    
			    request.setAttribute("keyword", keyword);
				// pageNum 도 추가로 담아주기
				request.setAttribute("pageNum", pageNum);
				    
				return "tradeBoard/ajax_page";
	}
	
	// 거래게시판 작성페이지 이동
	@RequestMapping(value = "/tradeBoardWrite", method = RequestMethod.GET)
	public String tradeBoardWrite() {
		logger.info("tradeBoardWrite 메소드 실행(GET)");
		
		return "tradeBoard/tradeBoardWrite";
	}
	
	// 거래게시판 작성
	@RequestMapping(value = "/tradeBoardWrite", method = RequestMethod.POST)
	public String tradeBoardWrite(String tradeBoardTitle, String tradeBoardContent, 
			String memberId, MultipartFile uploadFile, MultipartHttpServletRequest mtfRequest) {
		logger.info("tradeBoardWrite 메소드 실행(POST)");
		
		// 썸네일 저장
		String tradeBoardThumbnailSt = FileService.saveFile(uploadFile, "C:/workspace/petcommunity");
		logger.info("저장된 파일명 : {}", tradeBoardThumbnailSt);
						
		// 게시글의 메인 이미지 저장(여러개 가능)
		// fileList : 저장된 파일들을 저장한 리스트
		List<MultipartFile> fileList = mtfRequest.getFiles("multiUploadFile");
        String src = mtfRequest.getParameter("src");
        System.out.println("src value : " + src);

        String path = "C:/workspace/petcommunity/";
        
        String returnUrl = null;
    	
		// 글 작성
		boolean tradeResult = service.tradeBoardWrite(tradeBoardTitle,tradeBoardContent,memberId,tradeBoardThumbnailSt, uploadFile.getOriginalFilename());
			
		if(tradeResult) {
			logger.info("게시글 작성 성공");
			returnUrl = "redirect:/tradeBoard/tradeBoardMain";
		} else {
			logger.info("게시글 작성 실패");
			returnUrl = "tradeBoard/tradeBoardWrite";
		}			
        
        // 가장 최신글을 가져온다.
        int tradeBoardNum = service.selectBoardNum();
        
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
            boolean tradeDbsave = service.savedTradePhoto(tradeBoardNum,safeFile,originFileName);
            
            if(tradeDbsave)
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
		return returnUrl;
	}
	
	// 글 상세 보기
	@RequestMapping(value = "/tradeBoardRead", method = RequestMethod.GET)
	public String tradeBoardRead(int tradeBoardNum, Model model) {
		logger.info("tradeBoardWrite 메소드 실행(GET)");
		
		// 조회수 증가
		service.updateHitsCount(tradeBoardNum);
		
		// 글 상세보기
		TradeBoardVO tradeBoard = service.tradeBoardRead(tradeBoardNum);
		logger.info("tradeBoard: {}", tradeBoard);
		model.addAttribute("tradeBoard", tradeBoard);	
		
		// tradePhotoList = 이 글의 본문 사진정보가 들어있는 List
		ArrayList<TradeBoardSaveVO> tradePhotoList = service.getFileInfo(tradeBoardNum);
		model.addAttribute("tradePhotoList", tradePhotoList);
		
		return "tradeBoard/tradeBoardRead";
		
	}
	
	// 글 삭제
	@RequestMapping(value = "/tradeBoardDelete", method = RequestMethod.POST)
	public String tradeBoardDelete(int tradeBoardNum) {
		logger.info("tradeBoardDelete 메소드 실행(POST)");
		logger.info("tradeBoardNum: {}", tradeBoardNum);
			
		// 글 삭제 시 썸네일 파일 삭제
		TradeBoardVO file = service.tradeBoardRead(tradeBoardNum);
		if(file.getTradeBoardThumbnailOr() != null) {
			String filePath = "C:/workspace/petcommunity/" + file.getTradeBoardThumbnailSt();
			FileService.deleteFile(filePath);		
		}
		
		// 글 삭제 시 본문 사진 삭제
		ArrayList<TradeBoardSaveVO> fileList = service.getFileInfo(tradeBoardNum);
		for(int i=0; i<fileList.size();i++) {
			if(fileList != null) {
				String filePath2 = "C:/workspace/petcommunity/" + fileList.get(i).getTradeBoardPhotoSt();
				FileService.deleteFile(filePath2);
			}
		}
	
		// 게시글 제거
		boolean result = service.tradeBoardDelete(tradeBoardNum);
		if(result) {
			logger.info("게시글 삭제 성공");
		} else {
			logger.info("게시글 삭제 실패");
		}
					
		return "redirect:/tradeBoard/tradeBoardMain";
	}
	
	// 글 수정으로 이동
	@RequestMapping(value = "/tradeBoardUpdate", method = RequestMethod.GET)
	public String tradeBoardUpdate(int tradeBoardNum, Model model) {
		logger.info("tradeBoardUpdate 메소드 실행(GET)");
		
		TradeBoardVO tradeBoard = service.tradeBoardRead(tradeBoardNum);
		logger.info("tradeBoard: {}", tradeBoard);
		model.addAttribute("tradeBoard", tradeBoard);	
		
		// tradePhotoList = 이 글의 본문 사진정보가 들어있는 List
		ArrayList<TradeBoardSaveVO> tradePhotoList = service.getFileInfo(tradeBoardNum);
		model.addAttribute("tradePhotoList", tradePhotoList);
				
		return "tradeBoard/tradeBoardUpdate";
	}
	
	// 글 수정
	@RequestMapping(value = "/tradeBoardUpdate", method = RequestMethod.POST)
	public String tradeBoardUpdate(int tradeBoardNum, String tradeBoardTitle, String tradeBoardContent, 
			MultipartFile uploadFile, MultipartHttpServletRequest mtfRequest) {
		logger.info("tradeBoardUpdate 메소드 실행(POST)");
		
		// 글 수정 시 기존 썸네일 파일 삭제
		TradeBoardVO file = service.tradeBoardRead(tradeBoardNum);
		if(file.getTradeBoardThumbnailOr() != null) {
			String filePath = "C:/workspace/petcommunity/" + file.getTradeBoardThumbnailSt();
			FileService.deleteFile(filePath);		
		}
		
		// 기존 본문 사진 파일 삭제
		ArrayList<TradeBoardSaveVO> fileList = service.getFileInfo(tradeBoardNum);
		for(int i=0; i<fileList.size();i++) {
			if(fileList != null) {
				String filePath2 = "C:/workspace/petcommunity/" + fileList.get(i).getTradeBoardPhotoSt();
				FileService.deleteFile(filePath2);
			}
		}	
		
		// 기존 본문 사진 db도 삭제가 필요
		boolean deletePhotoDB = service.deleteTradeBoardb(tradeBoardNum);
		if(deletePhotoDB)
			logger.info("db삭제 성공");
		else
			logger.info("db삭제 실패");
		
		// 새로운 썸네일 저장
		String newTradeBoardThumbnailSt = FileService.saveFile(uploadFile, "C:/workspace/petcommunity");
		logger.info("저장된 파일명 : {}", newTradeBoardThumbnailSt);
		
	
		// 새로운 본문 사진 파일 저장
		List<MultipartFile> newFileList = mtfRequest.getFiles("multiUploadFile2");
        String src = mtfRequest.getParameter("src");
        System.out.println("src value : " + src);

        String path2 = "C:/workspace/petcommunity/";
        
        // 파일을 저장
        for (MultipartFile mf : newFileList) {
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            // 저장 파일명
            String safeFile = System.currentTimeMillis() + originFileName;
            // 파일 저장 경로 + 파일명
            String safeFile2 = path2 + safeFile;
            
            // db에 저장
            boolean tradeDbsave = service.savedTradePhoto(tradeBoardNum,safeFile,originFileName);
            
            if(tradeDbsave)
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
			
		// 글 번호를 가지고, 제목, 내용, 썸네일 사진 변경
		boolean result = service.tradeBoardUpdate(tradeBoardNum, tradeBoardTitle, tradeBoardContent, 
				newTradeBoardThumbnailSt, uploadFile.getOriginalFilename());
		if(result) {
			logger.info("게시글 수정 성공");
		} else {
			logger.info("게시글 수정 실패");
		}
				
		return "redirect:/tradeBoard/tradeBoardRead?tradeBoardNum=" + tradeBoardNum;
	}
	
	// 검색(안씀)
	@RequestMapping(value = "/searchTradeBoard", method = RequestMethod.GET)
	public String searchTradeBoard(String condition, String keyword, Model model) {
		logger.info("searchTradeBoard 메소드 실행(GET)");
		logger.info("condition : {}", condition);
		logger.info("searchWord : {}", keyword);
			
		ArrayList<TradeBoardVO> tradeBoardList =  service.searchTradeBoard(condition, keyword);
		logger.info("검색 결과: {}", tradeBoardList.size());
		model.addAttribute("tradeBoardList", tradeBoardList);	
									
		return "tradeBoard/tradeBoardMain";
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
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		// 경로 + 저장이름
		File targetFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/petcommunity/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
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