package com.pet.web.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pet.web.service.MemberService;
import com.pet.web.util.FileService;
import com.pet.web.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// 회원 컨트롤러
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		logger.info("join 메소드 실행(GET)");
		
		return "member/join";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.info("login 메소드 실행(GET)");
		
		return "member/login";
	}
	
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(HttpSession session, String memberId, MultipartFile uploadFile) {
		logger.info("setting 메소드 실행(GET)");
		
		String memberId1 = (String)session.getAttribute("memberId");
		
		MemberVO file = service.selectId(memberId1);
		
		String fileName = file.getMemberPhotoSt();
		String pw = file.getMemberPw();
		String name = file.getMemberNames();
		String phone = file.getMemberPhone();
		String birth = file.getMemberBirth();
		
		session.setAttribute("memberPhoto", fileName);
		session.setAttribute("name", name);
		session.setAttribute("pw", pw);
		session.setAttribute("birth", birth);
		session.setAttribute("phone", phone);
		
		return "member/setting";
	}
	

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(String memberId, String memberPw, String memberNames, String memberBirth, String memberPhone, MultipartFile uploadFile) {
		logger.info("join 메소드 실행(POST)");
		
		String saveFileName = FileService.saveFile(uploadFile, "C:/workspace/user");
		String originalFilename = uploadFile.getOriginalFilename();
		boolean result = service.join(memberId, memberPw, memberNames, memberBirth, memberPhone, saveFileName, originalFilename);
		
		String returnUrl;
		if(result) {
			returnUrl = "redirect:/";
			logger.info("회원가입 성공");
		} else {
			returnUrl = "member/join";
			logger.info("회원가입 실패");
		}
		return returnUrl;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String memberId, String memberPw, HttpSession session, Model rttr) {
		logger.info("login 메소드 실행(POST)");
		
		logger.info("memberId: {}", memberId);
		logger.info("memberPw: {}", memberPw);
		
		MemberVO login = service.login(memberId, memberPw);
		logger.info("login: {}", login);
		
		MemberVO file = service.selectId(memberId);
		
		String returnUrl;
		if(login == null) {
			logger.info("로그인 실패");
			returnUrl = "member/login";
			rttr.addAttribute("msg", false);
		} else {
			logger.info("로그인 성공");
			session.setAttribute("memberId", memberId);
			session.setAttribute("memberPhoto", file.getMemberPhotoSt());
			
			if(login.getMemberCode() == 9) {
				session.setAttribute("code", login.getMemberCode());			
			}
			
			returnUrl = "redirect:/";
		}
		


		
		return returnUrl;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("logout 메소드 실행(GET)");
		
		session.removeAttribute("memberId");
		session.removeAttribute("code");
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
	public String memberIdChkPOST(String memberId) throws Exception{
		logger.info("memberIdChk() 진입");
		int result = service.idCheck(memberId);
		
		logger.info("결과값 = " + result);
		
		if(result != 0) {
			return "fail";	// 중복 아이디가 존재
		} else {
			return "success";	// 중복 아이디 x
		}
	}
	
	
	@RequestMapping(value = "/setting", method = RequestMethod.POST)
	public String setting(HttpSession session, String memberPw, String memberNames, String memberBirth, String memberPhone, MultipartFile uploadFile) {
		String memberId = (String)session.getAttribute("memberId");
		
		String saveFileName = FileService.saveFile(uploadFile, "C:/workspace/user");
		
		MemberVO file = service.selectId(memberId);
		
		if (file.getMemberPhotoOr() != null) {
			String filepath = "C:/workspace/user/" + file.getMemberPhotoSt();
			FileService.deleteFile(filepath);
		}

		boolean result = service.updateMember(memberId, memberPw, memberNames, memberBirth, memberPhone, saveFileName, uploadFile.getOriginalFilename());
		
	    String returnUrl = null;
	    
	    if(result) {
			returnUrl = "redirect:/";
			logger.info("update 성공");
		} else {
			returnUrl = "member/setting";
			logger.info("update 실패");
		}
		return returnUrl;
	}
}