package com.pet.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.web.vo.FreeBoardVO;
import com.pet.web.vo.FreeHashtagVO;
import com.pet.web.vo.FreeReplyVO;

@Repository
public class FreeBoardDAO {

	@Autowired
	private SqlSession session;
	
	public ArrayList<FreeBoardVO> selectAllFreeBoard() {
		ArrayList<FreeBoardVO> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.selectAllFreeBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int freeBoardWrite(FreeBoardVO newFreeBoard) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.freeBoardWrite(newFreeBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public FreeBoardVO freeBoardRead(int freeBoardNum) {
		FreeBoardVO result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.freeBoardRead(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 조회수
	public void updateHitsCount(int freeBoardNum) {
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			mapper.updateHitsCount(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	public int freeBoardDelete(int freeBoardNum) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.freeBoardDelete(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}

	public int freeBoardUpdate(FreeBoardVO uFreeBoard) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.freeBoardUpdate(uFreeBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}

	public int freeReplyWrite(FreeReplyVO newReply) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.freeReplyWrite(newReply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<FreeReplyVO> readAllFreeReply(int freeBoardNum) {
		ArrayList<FreeReplyVO> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.readAllFreeReply(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 댓글 삭제
	public int freeReplyDelete(int freeReplyNum) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.freeReplyDelete(freeReplyNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 댓글 수정
	public int freeReplyUpdate(FreeReplyVO reply) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.freeReplyUpdate(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int recordLikes(HashMap<String, Object> map) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.recordLikes(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void increaseLikesNum(int freeBoardNum) {
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			mapper.increaseLikesNum(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public HashMap<String, Object> checklikes(HashMap<String, Object> map) {
		HashMap<String, Object> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.checklikes(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteLikes(HashMap<String, Object> map) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.deleteLikes(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 좋아요 수 감소
	public void reductionLikesNum(int freeBoardNum) {
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			mapper.reductionLikesNum(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	// 좋아요 개수
	public int countLikes(int freeBoardNum) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.countLikes(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<FreeBoardVO> searchFreeBoard(HashMap<String, String> map) {
		ArrayList<FreeBoardVO> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.searchFreeBoard(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 댓글 개수
	public int replyCount(int freeBoardNum) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.replyCount(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 댓글 수 증가
	public void increaseReplyNum(int freeBoardNum) {
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			mapper.increaseReplyNum(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void reductionReplyNum(int freeBoardNum) {
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			mapper.reductionReplyNum(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public int insertHashTag(FreeHashtagVO hash) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.insertHashTag(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public HashMap<String, Object> checkHashTag(HashMap<String, Object> map) {
		HashMap<String, Object> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.checkHashTag(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<FreeHashtagVO> searchHashtag(int freeBoardNum) {
		ArrayList<FreeHashtagVO> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.searchHashtag(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int selectBoardNum() {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.selectBoardNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<FreeHashtagVO> searchAllHashtag() {
		ArrayList<FreeHashtagVO> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.searchAllHashtag();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 해시태그 삭제
	public int deleteHashtag(int freeBoardNum) {
		int result = 0;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.deleteHashtag(freeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 해시태그 검색
	public ArrayList<FreeBoardVO> freeBoardfindTag(String hashTag) {
		ArrayList<FreeBoardVO> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.freeBoardfindTag(hashTag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 조회수 10
	public ArrayList<FreeBoardVO> selectBestFreeBoard() {
		ArrayList<FreeBoardVO> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.selectBestFreeBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 메뉴바 통합검색
	public ArrayList<FreeBoardVO> searchAllFreeBoard(String searchWord) {
		ArrayList<FreeBoardVO> result = null;
		FreeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(FreeBoardMapper.class);
			result = mapper.searchAllFreeBoard(searchWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	





}
