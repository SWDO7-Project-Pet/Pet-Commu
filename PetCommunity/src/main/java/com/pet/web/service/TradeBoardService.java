package com.pet.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pet.web.dao.TradeBoardDAO;
import com.pet.web.vo.FreeReplyVO;
import com.pet.web.vo.TradeBoardSaveVO;
import com.pet.web.vo.TradeBoardVO;
import com.pet.web.vo.TradeReplyVO;

@Service
public class TradeBoardService {

	@Autowired
	private TradeBoardDAO dao;
	
	public ArrayList<TradeBoardVO> selectAllTradeBoard() {
		return dao.selectAllTradeBoard();
	}
	
	// 거래 글 작성
	public boolean tradeBoardWrite(String tradeBoardTitle, String tradeBoardContent, String memberId, 
			String tradeBoardThumbnailSt, String originalFilename) {
		TradeBoardVO trade = new TradeBoardVO();
		
		trade.setTradeBoardTitle(tradeBoardTitle);
		trade.setTradeBoardContent(tradeBoardContent);
		trade.setMemberId(memberId);
		trade.setTradeBoardThumbnailSt(tradeBoardThumbnailSt);
		trade.setTradeBoardThumbnailOr(originalFilename);
		
		if(dao.tradeBoardWrite(trade) > 0)
			return true;
		else		
			return false;
	}

	public TradeBoardVO tradeBoardRead(int tradeBoardNum) {
		
		return dao.tradeBoardRead(tradeBoardNum);
	}

	public void updateHitsCount(int tradeBoardNum) {
		dao.updateHitsCount(tradeBoardNum);	
	}

	public boolean tradeBoardDelete(int tradeBoardNum) {
		if(dao.tradeBoardDelete(tradeBoardNum) > 0) 
			return true;
		else
			return false;
	}

	public int selectBoardNum() {
		return dao.selectBoardNum();
	}

	// 본문 파일 저장
	public boolean savedTradePhoto(int tradeBoardNum, String safeFile, String originFileName) {
		TradeBoardSaveVO photo = new TradeBoardSaveVO();
		photo.setTradeBoardNum(tradeBoardNum);
		photo.setTradeBoardPhotoSt(safeFile);
		photo.setTradeBoardPhotoOr(originFileName);
		
		if(dao.savedTradePhoto(photo) > 0) 
			return true;
		else 
			return false;
	}
	
	// 글 삭제 시 본문 파일 삭제를 위한 파일 정보 가져오기
	public ArrayList<TradeBoardSaveVO> getFileInfo(int tradeBoardNum) {
		return dao.getFileInfo(tradeBoardNum);
	}

	public ArrayList<TradeBoardVO> searchTradeBoard(String condition, String keyword) {
		HashMap<String, String> map = new HashMap<>();
		
		map.put("condition", condition);
		map.put("searchWord", keyword);
		
		return dao.searchTradeBoard(map);
	}

	// 수정
	public boolean tradeBoardUpdate(int tradeBoardNum, String tradeBoardTitle, String tradeBoardContent,
			String newTradeBoardThumbnailSt, String originalFilename) {
		TradeBoardVO newTradeBoard = new TradeBoardVO();
		
		newTradeBoard.setTradeBoardNum(tradeBoardNum);
		newTradeBoard.setTradeBoardTitle(tradeBoardTitle);
		newTradeBoard.setTradeBoardContent(tradeBoardContent);
		newTradeBoard.setTradeBoardThumbnailSt(newTradeBoardThumbnailSt);
		newTradeBoard.setTradeBoardThumbnailOr(originalFilename);
		
		if(dao.tradeBoardUpdate(newTradeBoard) > 0)
			return true;
		else
			return false;
	}

	// 기존 파일 db 삭제
	public boolean deleteTradeBoardb(int tradeBoardNum) {
		if(dao.deleteTradeBoardb(tradeBoardNum) > 0) 
			return true;
		else
			return false;
	}

	// 전체 글수
	public int getTotalCount() {
		return dao.getTotalCount();
	}

	public ArrayList<TradeBoardVO> getBoardList(TradeBoardVO tb) {
		return dao.getBoardList(tb);
	}

	// 거래게시판 댓글 작성
	public boolean tradeReplyWrite(int tradeBoardNum, String memberId, String tradeReplyContent, String csOpen) {
		TradeReplyVO newReply = new TradeReplyVO();
		newReply.setTradeBoardNum(tradeBoardNum);
		newReply.setMemberId(memberId);
		newReply.setTradeReplyContent(tradeReplyContent);
		newReply.setCsOpen(csOpen);
		
		int result = dao.tradeReplyWrite(newReply);
		if(result > 0)
			return true;
		else
			return false;
	}

	// 전체 댓글 보기
	public ArrayList<TradeReplyVO> readAllTradeReply(int tradeBoardNum) {
		return dao.readAllTradeReply(tradeBoardNum);
	}

	// 댓글 삭제
	public boolean tradeReplyDelete(int tradeReplyNum) {
		if(dao.tradeReplyDelete(tradeReplyNum) > 0) 
			return true;
		else
			return false;
	}

	// 댓글 수정
	public boolean tradeReplyUpdate(String tradeReplyContent, int tradeReplyNum) {
		TradeReplyVO reply = new TradeReplyVO();
		
		reply.setTradeReplyContent(tradeReplyContent);
		reply.setTradeReplyNum(tradeReplyNum);
	
		if(dao.tradeReplyUpdate(reply) > 0)
			return true;
		else
			return false;
	}

	// 페이징 글 출력
//	public ArrayList<HashMap<String, Object>> getBoardList(int startRecord, int countPerPage) {
//		HashMap<String, Integer> map = new HashMap<>();
//		
//		map.put("startRecord", startRecord);
//		map.put("countPerPage", countPerPage);
//		
//		return dao.getBoardList(map);
//	}
	
}
