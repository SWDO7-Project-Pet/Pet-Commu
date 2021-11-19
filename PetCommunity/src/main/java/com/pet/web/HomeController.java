package com.pet.web;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pet.web.service.FreeBoardService;
import com.pet.web.vo.FreeBoardVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private FreeBoardService freeService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		// 자유게시판 전체 정보를 담을 리스트
		ArrayList<FreeBoardVO> bestFreeBoardList = freeService.selectBestFreeBoard();
		logger.info("bestFreeBoardList: {}", bestFreeBoardList);
		model.addAttribute("bestFreeBoardList", bestFreeBoardList);
		
		// 자유게시판 전체 정보를 담을 리스트
		ArrayList<FreeBoardVO> newFreeBoardList = freeService.selectAllFreeBoard();
		logger.info("newFreeBoardList: {}", newFreeBoardList);
		model.addAttribute("newFreeBoardList", newFreeBoardList);
		
		return "home";
	}
	 
//	@RequestMapping("/chat")
//	public ModelAndView chat() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("chat");
//		return mv;
//	}
	
	
	
}
