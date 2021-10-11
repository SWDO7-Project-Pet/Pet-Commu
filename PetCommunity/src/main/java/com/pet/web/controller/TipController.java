package com.pet.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pet.web.service.TipService;
import com.pet.web.vo.AnimalDataPhotoSaveVO;
import com.pet.web.vo.AnimalDataVO;
import com.pet.web.vo.TipVO;
import com.pet.web.util.FileService;

@Controller
@RequestMapping(value = "/tip")
public class TipController {
	// 팁 컨트롤러
	private static final Logger logger = LoggerFactory.getLogger(TipController.class);
	
	@Autowired
	private TipService service;
	
	// tipMain 접속
	@RequestMapping(value = "/tipMain", method = RequestMethod.GET)
	public String tipMain(Model model) {
		logger.info("tipMain 메소드 실행(GET)");
		
		//tipMain select
		ArrayList<TipVO> tipList = service.selectTip();
		logger.info("model:{}",model);
		model.addAttribute("tipList",tipList);
		
		
		return"tip/tipMain";
	}	
	
	// tipMap 접속
	@RequestMapping(value="/tipMap",method = RequestMethod.GET)
	public String tipMap() {
		logger.info("tipMap 메소드 실행(GET)");
		return"tip/tipMap";
	}
	
	
	// tipAnimal 접속
	@RequestMapping(value="/tipAnimal",method = RequestMethod.GET)
	public String tipAnimal(Model model,String animalKind) {
		logger.info("tipAnimal 메소드 실행(GET)");
		ArrayList<AnimalDataVO> animalList = service.selectAnimalData(animalKind);
		model.addAttribute("animalList",animalList);
		
		return"tip/tipAnimal";
	}
	
	// tipAnimalSpecific 접속
		@RequestMapping(value="/tipAnimalSpecific",method = RequestMethod.GET)
		public String tipAnimalSpecific(Model model,int animalNum) {
			logger.info("tipAnimalSpecific 메소드 실행(GET)");
			logger.info("animalNum:{}",animalNum);
			AnimalDataVO animal = service.getFileInfo(animalNum);
			ArrayList<AnimalDataPhotoSaveVO> photo = service.selectPhoto(animalNum);
			logger.info("animal:{}",animal);
			model.addAttribute("animal",animal);
			logger.info("photo:{}",photo);
			model.addAttribute("photo",photo);
			
			return"tip/tipAnimalSpecific";
		}
	
	
	//tipTest 접속
	@RequestMapping(value="/tipTest",method = RequestMethod.GET)
	public String tipTest() {
		logger.info("tipTest 메소드 실행(GET)");
		return"tip/tipTest";
	}
	//writeTipAnimal 접속
		@RequestMapping(value="/writeTipAnimal",method = RequestMethod.GET)
		public String writeTipAnimal() {
			logger.info("writeTipAnimal 메소드 실행(GET)");
			return"tip/writeTipAnimal";
		}
		
	//writeTipAnimal 작성 기능	
		@RequestMapping(value="/writeTipAnimal",method = RequestMethod.POST)
		public String writeTipAnimal(HttpSession session,String animalKind,String animalVariety, String animalOutline,String animalOrigin, String animalSize,String animalHeight,String animalWeight,String animalAppearance,String animalColor,String animalPersonality,String animalPurpose,String animalDisease,String animalRecommend,MultipartFile uploadThumbNail ,MultipartHttpServletRequest uploadFile) {
			logger.info("writeTipAnimal 메소드 실행(POST)");
			logger.info("animalKind: {}",animalKind);
			logger.info("animalVariety: {}",animalVariety);
			logger.info("animalOutline: {}",animalOutline);
			logger.info("uploadThumbNail: {}",uploadThumbNail);
			logger.info("originalFileName: {}",uploadThumbNail.getOriginalFilename());
			logger.info("uploadFile: {}",uploadFile);
			
			String animalThumbnailSt = FileService.saveFile(uploadThumbNail, "C:/workspace/tip/");
			logger.info("저장된 파일명: {}", animalThumbnailSt);		
			
			String memberId = (String) session.getAttribute("memberId");
			
			boolean result = service.insertAnimal(animalKind,uploadThumbNail.getOriginalFilename(),animalThumbnailSt,animalVariety,animalOutline,animalOrigin,animalSize,animalHeight,animalWeight,animalAppearance,animalColor,animalPersonality,animalPurpose,animalDisease,animalRecommend,memberId);
			String returnUrl = null;
			if(result) {
				logger.info("게시글 작성 성공");
				returnUrl = "redirect:/tip/tipAnimal";
			}else {
				logger.info("게시글 작성 실패");
				returnUrl = "tip/writeTipAnimal";
			}
			//파일 저장 시작
			List<MultipartFile> fileList = uploadFile.getFiles("uploadFile");
			String path = "C:/workspace/tip";
			for(MultipartFile mf : fileList) {
				String savedFileName = FileService.saveFile(mf, path);
				logger.info("저장된 파일명:{}",savedFileName);
				if(savedFileName !=null) {
					int animalNum = service.selectAnimalNum();
					logger.info("animalNum:{}",animalNum);
					boolean result2 = service.uploadFile(animalNum, savedFileName, mf.getOriginalFilename());
					if(result2) {
						logger.info("파일 정보 저장 성공.");
					}else {
						logger.info("파일 정보 저장 삭제.");
					}
				}
			}						
			return returnUrl;									
		}		
			
		//updateAnimal 실행
		@RequestMapping(value="/updateAnimal",method = RequestMethod.POST)
		public String updateAnimal(int animalNum,String animalKind,String animalVariety, String animalOutline,String animalOrigin, String animalSize,String animalHeight,String animalWeight,String animalAppearance,String animalColor,String animalPersonality,String animalPurpose,String animalDisease,String animalRecommend,Model model) {
			logger.info("updateAnimal 메소드 실행(POST)");
			logger.info("animalNum:{}",animalNum);
			
//			ArrayList<AnimalDataPhotoSaveVO> photo = service.selectPhoto(animalNum);
//			logger.info("photo:{}",photo);
//			model.addAttribute("photo",photo);			
			
			AnimalDataVO animal = service.getFileInfo(animalNum);
			logger.info("animal:{}",animal);
			model.addAttribute("animal",animal);
			
			logger.info("animalKind: {}",animalKind);
			logger.info("animalVariety: {}",animalVariety);
			logger.info("animalOutline: {}",animalOutline);
			//logger.info("uploadThumbNail: {}",uploadThumbNail);
			//logger.info("originalFileName: {}",uploadThumbNail.getOriginalFilename());
			//logger.info("uploadFile: {}",uploadFile);
			
			//String animalThumbnailSt = FileService.saveFile(uploadThumbNail, "c:\\Upload Files");
			//logger.info("저장된 파일명: {}", animalThumbnailSt);		
			
			boolean result = service.updateAnimal(animalNum,animalKind,animalVariety,animalOutline,animalOrigin,animalSize,animalHeight,animalWeight,animalAppearance,animalColor,animalPersonality,animalPurpose,animalDisease,animalRecommend);
						
//			//파일 저장 시작
//			List<MultipartFile> fileList = uploadFile.getFiles("uploadFile");
//			String path = "c:\\Upload Files";
//			for(MultipartFile mf : fileList) {
//				String savedFileName = FileService.saveFile(mf, path);
//				logger.info("저장된 파일명:{}",savedFileName);
//				if(savedFileName !=null) {
//					logger.info("animalNum:{}",animalNum);
//					boolean result2 = service.uploadFile(animalNum, savedFileName, mf.getOriginalFilename());
//					if(result2) {
//						logger.info("파일 정보 저장 성공.");
//					}else {
//						logger.info("파일 정보 저장 삭제.");
//					}
//				}
//			}
			String returnUrl = null;
			if(result) {
				logger.info("게시글 수정 성공");
				returnUrl = "redirect:/tip/tipAnimal";
			}else {
				logger.info("게시글 수정 실패");
				returnUrl = "tip/updateTipAnimal?animalNum="+animalNum;
			}
			return returnUrl;	
		}
		
		//updateAnimal 페이지 접속
		@RequestMapping(value="/updateAnimal",method = RequestMethod.GET)
		public String updateAnimal(int animalNum,Model model) {
			logger.info("updateAnimal 메소드 실행(GET)");
			logger.info("animalNum:{}",animalNum);
			AnimalDataVO animal = service.getFileInfo(animalNum);
			logger.info("animal:{}",animal);
			model.addAttribute("animal",animal);
			
			return "tip/updateTipAnimal";
		}
		
		
			//writeTipMain 접속
				@RequestMapping(value="/writeTipMain",method = RequestMethod.GET)
				public String writeTipMain() {
					logger.info("writeTipMain 메소드 실행(GET)");
					return"tip/writeTipMain";
				}
			//writeTipMain 작성 기능
			@RequestMapping(value="/writeTipMain",method = RequestMethod.POST)
			public String writeTipMain(String tipTitle, String tipContent, HttpSession session) {
				logger.info("writeTipMain 메소드 실행(POST)");
				logger.info("tipTitle:{}",tipTitle);
				logger.info("tipContent:{}",tipContent);
				
				String memberId = (String) session.getAttribute("memberId");
 
				
				boolean result = service.insertTip(tipTitle,tipContent,memberId);
				
				String returnUrl = null;
				
				if(result) {
					logger.info("게시글 작성 성공");
					returnUrl = "redirect:/tip/tipMain";
				}else {
					logger.info("게시글 작성 실패");
					returnUrl = "tip/writeTipMain";
				}
				return returnUrl;
			}
			//deleteTipMain 접속
			@RequestMapping(value="/deleteTip",method = RequestMethod.GET)
			public String deleteTip(int tipNum) {
				logger.info("deleteTip 메소드 실행(GET)");
				logger.info("tipNum:{}",tipNum);
				
				boolean result = service.deleteTip(tipNum);
				
				return"redirect:/tip/tipMain";
			}
			//updateTipMain 실행
			@RequestMapping(value="/updateTip",method = RequestMethod.POST)
			public String updateTip(int tipNum,String tipTitle,String tipContent) {
				logger.info("updateTip 메소드 실행(GET)");
				logger.info("tipNum:{}",tipNum);
				logger.info("tipTitle:{}",tipTitle);
				logger.info("tipContent:{}",tipContent);
				
				boolean result = service.updateTip(tipNum,tipTitle,tipContent);
				
				String returnUrl = null;
				if(result) {
					logger.info("게시글 작성 성공");
					returnUrl = "redirect:/tip/tipMain";
				}else {
					logger.info("게시글 작성 실패");
					returnUrl = "tip/tipMain";
				}
				return returnUrl;	
			}
			
			//deleteAnimal 접속
			@RequestMapping(value="/deleteAnimal",method = RequestMethod.GET)
			public String deleteAnimal(int animalNum) {
				logger.info("deleteAnimal 메소드 실행(GET)");
				logger.info("animalNum:{}",animalNum);
				
				boolean result = service.deleteAnimal(animalNum);
				
				return"redirect:/tip/tipAnimal";
			}
		
}
