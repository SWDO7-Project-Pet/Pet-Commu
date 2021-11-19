package com.pet.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.pet.web.vo.TradeBoardSaveVO;
import com.pet.web.vo.TradeBoardVO;
import com.pet.web.vo.TradeReplyVO;

public interface TradeBoardMapper {

	// 전체 글 조회
	ArrayList<TradeBoardVO> selectAllTradeBoard();
	// 글 작성
	int tradeBoardWrite(TradeBoardVO trade);
	// 글 조회
	TradeBoardVO tradeBoardRead(int tradeBoardNum);
	// 조회수 증가
	void updateHitsCount(int tradeBoardNum);
	// 글 삭제
	int tradeBoardDelete(int tradeBoardNum);
	
	// 가장 최신글 찾기
	int selectBoardNum();
	// 사진 업로드(파일정보저장)
	int savedTradePhoto(TradeBoardSaveVO photo);
	// 사진 업로드(파일정보저장) 정보 가져오기
	ArrayList<TradeBoardSaveVO> getFileInfo(int tradeBoardNum);	
	// 검색
	ArrayList<TradeBoardVO> searchTradeBoard(HashMap<String, String> map);
	// 글 수정
	int tradeBoardUpdate(TradeBoardVO newTradeBoard);
	// 저장된 db 삭제
	int deleteTradeBoardb(int tradeBoardNum);
	// 전체 글 수
	int getTotalCount();
	// 페이징 글 출력
	//ArrayList<HashMap<String, Object>> getBoardList(HashMap<String, Integer> map);
	ArrayList<TradeBoardVO> getBoardList(TradeBoardVO tb);
	
	// 댓글 작성
	int tradeReplyWrite(TradeReplyVO newReply);
	// 댓글 보기
	ArrayList<TradeReplyVO> readAllTradeReply(int tradeBoardNum);
	// 댓글 삭제
	int tradeReplyDelete(int tradeReplyNum);
	// 댓글 수정
	int tradeReplyUpdate(TradeReplyVO reply);


}
