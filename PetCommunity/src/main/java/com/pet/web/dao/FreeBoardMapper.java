package com.pet.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.pet.web.vo.FreeBoardVO;
import com.pet.web.vo.FreeHashtagVO;
import com.pet.web.vo.FreeReplyVO;

public interface FreeBoardMapper {

	// 자유게시판 메인홈
	ArrayList<FreeBoardVO> selectAllFreeBoard();
	// 글 쓰기
	int freeBoardWrite(FreeBoardVO newFreeBoard);
	// 글 상세보기
	FreeBoardVO freeBoardRead(int freeBoardNum);
	// 조회수 올리기
	void updateHitsCount(int freeBoardNum);
	// 글 삭제
	int freeBoardDelete(int freeBoardNum);
	// 글 수정
	int freeBoardUpdate(FreeBoardVO uFreeBoard);
	
	
	// 댓글 작성
	int freeReplyWrite(FreeReplyVO newReply);
	// 댓글 읽기
	ArrayList<FreeReplyVO> readAllFreeReply(int freeBoardNum);
	// 댓글 삭제
	int freeReplyDelete(int freeReplyNum);
	// 댓글 수정
	int freeReplyUpdate(FreeReplyVO reply);
	// 댓글 개수(필요없음)
	int replyCount(int freeBoardNum);
	// 댓글 수 증가
	void increaseReplyNum(int freeBoardNum);
	// 댓글 수 감소
	void reductionReplyNum(int freeBoardNum);
	
	
	// 좋아요 저장
	int recordLikes(HashMap<String, Object> map);
	// 좋아요수 욜리기
	void increaseLikesNum(int freeBoardNum);
	// 좋아요 여부 확인
	HashMap<String, Object> checklikes(HashMap<String, Object> map);
	// 좋아요 삭제
	int deleteLikes(HashMap<String, Object> map);
	// 좋아요 수 감소
	void reductionLikesNum(int freeBoardNum);
	// 좋아요 수(필요없음)
	int countLikes(int freeBoardNum);
	
	
	// 검색
	ArrayList<FreeBoardVO> searchFreeBoard(HashMap<String, String> map);
	

	// 해시태그 추가
	int insertHashTag(FreeHashtagVO hash);
	// 해시태그 여부 확인
	HashMap<String, Object> checkHashTag(HashMap<String, Object> map);
	// 해시태그 여부 확인
	//ArrayList<HashMap<String, Object>> checkHashTag(ArrayList<HashMap<String, Object>> list);
	// 해시태그 조회
	ArrayList<FreeHashtagVO> searchHashtag(int freeBoardNum);
	
	// 가장 최신글 조회
	int selectBoardNum();
	// 전체 태그 조회
	ArrayList<FreeHashtagVO> searchAllHashtag();
	// 해시태그 삭제
	int deleteHashtag(int freeBoardNum);
	// 해시태그 검색
	ArrayList<FreeBoardVO> freeBoardfindTag(String hashTag);
	// 인기글 top 10
	ArrayList<FreeBoardVO> selectBestFreeBoard();
	// 메뉴바 통합검색
	ArrayList<FreeBoardVO> searchAllFreeBoard(String searchWord);
	
	
	
	

}
