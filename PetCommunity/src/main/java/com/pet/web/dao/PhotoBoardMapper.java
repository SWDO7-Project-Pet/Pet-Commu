package com.pet.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.pet.web.vo.PhotoBoardSaveVO;
import com.pet.web.vo.PhotoBoardVO;
import com.pet.web.vo.PhotoHashtagVO;
import com.pet.web.vo.PhotoLikesVO;
import com.pet.web.vo.PhotoReplyVO;

public interface PhotoBoardMapper {

	
	  int writeBoard(PhotoBoardVO photoBoard); //사진게시판 등록
	  
	  int uploadPhotoBoard(PhotoBoardSaveVO photoBoardSave); //사진 등록
	 
	  int photoBoardUpdate(PhotoBoardVO photoBoard);

	  int selectOnePhotoBoard(String photoBoardTitle);
	  
	  ArrayList<PhotoBoardVO> selectAllPhotoBoard();
	  
	  PhotoBoardVO photoBoardRead(int photoBoardNum);

	  ArrayList<PhotoReplyVO> photoBoardReadReply(int photoBoardNum);
	  
	  int photoBoardReplyWrite(PhotoReplyVO photoReply);

	  int photoBoardReplyUpdate(PhotoReplyVO photoReply);
	  
	  int photoBoardReplyDelete(int photoReplyNum);
	  
	  int photoBoardInsertFavorite(PhotoLikesVO photoLikes);

	  int photoBoardUpdateFavorite(int photoBoardNum);

	  ArrayList<HashMap<String, Object>> allFavorite(String memberId);

	  int photoBoardDeleteFavorite(PhotoLikesVO photoLikes);
	  
	  int photoBoardUpdateFavoriteCancel(int photoBoardNum);

	  int insertPBHashTag(PhotoHashtagVO photoHashtag);
	  
	  ArrayList<PhotoHashtagVO> allPBHashTag();

	  int deletePBHashTag(int photoBoardNum);

  	  int photoBoardDelete(int photoBoardNum);

	  ArrayList<PhotoBoardSaveVO> allPhotoBoardsave();

	  ArrayList<PhotoReplyVO> allPhotoReply();
	  
	  int getListCnt();
}
