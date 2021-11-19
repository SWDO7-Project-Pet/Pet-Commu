package com.pet.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.web.dao.FreeBoardDAO;
import com.pet.web.vo.FreeBoardVO;
import com.pet.web.vo.FreeHashtagVO;
import com.pet.web.vo.FreeReplyVO;

@Service
public class FreeBoardService {

	@Autowired
	private FreeBoardDAO dao;
	
	// 전체 글 보기(메인화면)
	public ArrayList<FreeBoardVO> selectAllFreeBoard() {	
		return dao.selectAllFreeBoard();
	}
	
	// 글 쓰기
	public boolean freeBoardWrite(String freeBoardTitle, String freeBoardContent, String memberId) {
		FreeBoardVO newFreeBoard = new FreeBoardVO();
		
		newFreeBoard.setFreeBoardTitle(freeBoardTitle);
		newFreeBoard.setFreeBoardContent(freeBoardContent);
		newFreeBoard.setMemberId(memberId);
		
		if(dao.freeBoardWrite(newFreeBoard) > 0)
			return true;
		else		
			return false;
	}

	// 글 상세보기
	public FreeBoardVO freeBoardRead(int freeBoardNum) {
		return dao.freeBoardRead(freeBoardNum);
	}

	// 조회수 증가
	public void updateHitsCount(int freeBoardNum) {
		dao.updateHitsCount(freeBoardNum);
		
	}

	// 글 삭제
	public boolean freeBoardDelete(int freeBoardNum) {
		if(dao.freeBoardDelete(freeBoardNum) > 0) 
			return true;
		else
			return false;
	}

	// 글 수정
	public boolean freeBoardUpdate(int freeBoardNum, String freeBoardTitle, String freeBoardContent) {
		FreeBoardVO uFreeBoard = new FreeBoardVO();
		
		uFreeBoard.setFreeBoardNum(freeBoardNum);
		uFreeBoard.setFreeBoardTitle(freeBoardTitle);
		uFreeBoard.setFreeBoardContent(freeBoardContent);
		
		if(dao.freeBoardUpdate(uFreeBoard) > 0)
			return true;
		else
			return false;

	}

	// 댓글 작성
	public boolean freeReplyWrite(int freeBoardNum, String memberId, String freeReplyContent) {
		FreeReplyVO newReply = new FreeReplyVO();
		
		newReply.setFreeBoardNum(freeBoardNum);
		newReply.setMemberId(memberId);
		newReply.setFreeReplyContent(freeReplyContent);
		
		int result = dao.freeReplyWrite(newReply);
		if(result > 0)
			return true;
		else
			return false;
	}

	// 댓글 조회
	public ArrayList<FreeReplyVO> readAllFreeReply(int freeBoardNum) {	
		return dao.readAllFreeReply(freeBoardNum);
	}


	// 댓글 삭제
	public boolean freeReplyDelete(int freeReplyNum) {
		if(dao.freeReplyDelete(freeReplyNum) > 0) 
			return true;
		else
			return false;
	}

	// 댓글 수정
	public boolean freeReplyUpdate(String freeReplyContent, int freeReplyNum) {
		FreeReplyVO reply = new FreeReplyVO();
		
		reply.setFreeReplyContent(freeReplyContent);
		reply.setFreeReplyNum(freeReplyNum);
			
		if(dao.freeReplyUpdate(reply) > 0)
			return true;
		else
			return false;
	}

	// 좋아요 누른 글, 아이디 저장
	public boolean recordLikes(String memberId, int freeBoardNum) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("freeBoardNum", freeBoardNum);
		
		return dao.recordLikes(map) > 0 ? true : false;
	}

	// 좋아요 숫자 올리기
	public void increaseLikesNum(int freeBoardNum) {
		dao.increaseLikesNum(freeBoardNum);		
	}


	public HashMap<String, Object> checklikes(String memberId, int freeBoardNum) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("freeBoardNum", freeBoardNum);
		
		return dao.checklikes(map);
	}

	// 좋아요 삭제
	public boolean deleteLikes(String memberId, int freeBoardNum) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("freeBoardNum", freeBoardNum);
		
		return dao.deleteLikes(map) > 0 ? true : false;
	}

	// 좋아요 수 감소
	public void reductionLikesNum(int freeBoardNum) {
		dao.reductionLikesNum(freeBoardNum);
	}

	// 좋아요 수
	public int countLikes(int freeBoardNum) {
		return dao.countLikes(freeBoardNum);
	}

	// 검색
	public ArrayList<FreeBoardVO> searchFreeBoard(String condition, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		
		map.put("condition", condition);
		map.put("searchWord", searchWord);
		
		return dao.searchFreeBoard(map);
	}

	// 댓글 개수
	public int replyCount(int freeBoardNum) {
		return dao.replyCount(freeBoardNum);
	}

	// 댓글 개수 증가
	public void increaseReplyNum(int freeBoardNum) {
		dao.increaseReplyNum(freeBoardNum);	
	}

	// 댓글 개수 감소
	public void reductionReplyNum(int freeBoardNum) {
		dao.reductionReplyNum(freeBoardNum);
	}

	// 해시태그 추가
	public boolean insertHashTag(int freeBoardNum,String hashTag) {
		FreeHashtagVO hash = new FreeHashtagVO();
		
		hash.setFreeBoardNum(freeBoardNum);
		hash.setHashTag(hashTag);
				
		if(dao.insertHashTag(hash) > 0)
			return true;
		else		
			return false;
	}

	// 해시태그 여부 확인
	public HashMap<String, Object> checkHashTag(int freeBoardNum, String hashTag) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("freeBoardNum", freeBoardNum);
		map.put("hashTag", hashTag);
		
		//ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		//list.add(map);
			
		return dao.checkHashTag(map);
	}

	// 글에 달린 해시태그 목록
	public ArrayList<FreeHashtagVO> searchHashtag(int freeBoardNum) {
		return dao.searchHashtag(freeBoardNum);
	}

	// 최신글의 글번호 가져오기
	public int selectBoardNum() {
		return dao.selectBoardNum();
	}

	// 전체 해시태그 조회
	public ArrayList<FreeHashtagVO> searchAllHashtag() {
		return dao.searchAllHashtag();
	}

	// 해시태그 삭제
	public boolean deleteHashtag(int freeBoardNum) {
		if(dao.deleteHashtag(freeBoardNum) > 0) 
			return true;
		else
			return false;
	}
	
	// 해시태그로 검색
	public ArrayList<FreeBoardVO> freeBoardfindTag(String hashTag) {
		return dao.freeBoardfindTag(hashTag);
	}

	// 인기많은 글 top 10
	public ArrayList<FreeBoardVO> selectBestFreeBoard() {
		return dao.selectBestFreeBoard();
	}

	public ArrayList<FreeBoardVO> searchAllFreeBoard(String searchWord) {
		return dao.searchAllFreeBoard(searchWord);
	}

	
}
