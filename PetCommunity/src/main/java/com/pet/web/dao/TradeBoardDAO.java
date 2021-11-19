package com.pet.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.pet.web.vo.TradeBoardSaveVO;
import com.pet.web.vo.TradeBoardVO;
import com.pet.web.vo.TradeReplyVO;

@Repository
public class TradeBoardDAO {

	@Autowired
	private SqlSession session;
	
	// 전체 글 조회
	public ArrayList<TradeBoardVO> selectAllTradeBoard() {
		ArrayList<TradeBoardVO> result = null;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.selectAllTradeBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 글 쓰기
	public int tradeBoardWrite(TradeBoardVO trade) {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.tradeBoardWrite(trade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public TradeBoardVO tradeBoardRead(int tradeBoardNum) {
		TradeBoardVO result = null;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.tradeBoardRead(tradeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 조회수 증가
	public void updateHitsCount(int tradeBoardNum) {
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			mapper.updateHitsCount(tradeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int tradeBoardDelete(int tradeBoardNum) {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.tradeBoardDelete(tradeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int selectBoardNum() {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.selectBoardNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int savedTradePhoto(TradeBoardSaveVO photo) {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.savedTradePhoto(photo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<TradeBoardSaveVO> getFileInfo(int tradeBoardNum) {
		ArrayList<TradeBoardSaveVO> result = null;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.getFileInfo(tradeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 검색
	public ArrayList<TradeBoardVO> searchTradeBoard(HashMap<String, String> map) {
		ArrayList<TradeBoardVO> result = null;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.searchTradeBoard(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int tradeBoardUpdate(TradeBoardVO newTradeBoard) {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.tradeBoardUpdate(newTradeBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 기존 파일 db삭제
	public int deleteTradeBoardb(int tradeBoardNum) {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.deleteTradeBoardb(tradeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int getTotalCount() {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

//	public ArrayList<HashMap<String, Object>> getBoardList(HashMap<String, Integer> map) {
//		ArrayList<HashMap<String, Object>> result = null;
//		TradeBoardMapper mapper = null;
//		
//		try {
//			mapper = session.getMapper(TradeBoardMapper.class);
//			result = mapper.getBoardList(map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}

	public ArrayList<TradeBoardVO> getBoardList(TradeBoardVO tb) {
		ArrayList<TradeBoardVO> result = null;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.getBoardList(tb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 거래게시판 댓글
	public int tradeReplyWrite(TradeReplyVO newReply) {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.tradeReplyWrite(newReply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 댓글 보기
	public ArrayList<TradeReplyVO> readAllTradeReply(int tradeBoardNum) {
		ArrayList<TradeReplyVO> result = null;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.readAllTradeReply(tradeBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 댓글 삭제
	public int tradeReplyDelete(int tradeReplyNum) {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.tradeReplyDelete(tradeReplyNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 댓글 수정
	public int tradeReplyUpdate(TradeReplyVO reply) {
		int result = 0;
		TradeBoardMapper mapper = null;
		
		try {
			mapper = session.getMapper(TradeBoardMapper.class);
			result = mapper.tradeReplyUpdate(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
