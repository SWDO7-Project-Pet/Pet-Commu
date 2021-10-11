package com.pet.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.web.dao.PhotoBoardDAO;
import com.pet.web.vo.PhotoBoardSaveVO;
import com.pet.web.vo.PhotoBoardVO;
import com.pet.web.vo.PhotoHashtagVO;
import com.pet.web.vo.PhotoLikesVO;
import com.pet.web.vo.PhotoReplyVO;

@Service
public class PhotoBoardService {

	  @Autowired 
	  private PhotoBoardDAO dao;
	  
	  public int writeBoard(PhotoBoardVO photoBoard) { 
		  return dao.writeBoard(photoBoard);
	  }
	  
	  public int uploadPhotoBoard(PhotoBoardSaveVO photoBoardSave) { 
		
		  return dao.uploadPhotoBoard(photoBoardSave); }
	  
	  public int photoBoardUpdate(PhotoBoardVO photoBoard) {
		  return dao.photoBoardUpdate(photoBoard);
	  }
	 
	
	  public int selectOnePhotoBoard(String photoBoardTitle) {
		  return dao.selectOnePhotoBoard(photoBoardTitle);
	  }
	  public ArrayList<PhotoBoardVO> selectAllPhotoBoard(){
		  return dao.selectAllPhotoBoard();
	  }
	  
	  public PhotoBoardVO photoBoardRead(int photoBoardNum) {
		  return dao.photoBoardRead(photoBoardNum);
	  }
	  public ArrayList<PhotoReplyVO> photoBoardReadReply(int photoBoardNum) {
		  return dao.photoBoardReadReply(photoBoardNum);
	  }
	  
	  public int photoBoardReplyWrite(PhotoReplyVO photoReply) {
		  return dao.photoBoardReplyWrite(photoReply);
	  }

	public int photoBoardReplyUpdate(PhotoReplyVO photoReply) {
		
		return dao.photoBoardReplyUpdate(photoReply);
	}

	public int photoBoardReplyDelete(int photoReplyNum) {
		
		return dao.photoBoardReplyDelete(photoReplyNum);
	}
	
	public int photoBoardInsertFavorite(PhotoLikesVO photoLikes) {
		return dao.photoBoardInsertFavorite(photoLikes);
	}
	  
	public int photoBoardUpdateFavorite(int photoBoardNum) {
		return dao.photoBoardUpdateFavorite(photoBoardNum);
	}
	  
	 public ArrayList<HashMap<String, Object>> allFavorite(String memberId){
		
		 return dao.allFavorite(memberId);
	 }
	 public int photoBoardDeleteFavorite(PhotoLikesVO photoLikes) {
		 return dao.photoBoardDeleteFavorite(photoLikes);
	 }
	 
	 public int photoBoardUpdateFavoriteCancel(int photoBoardNum) {
		 return dao.photoBoardUpdateFavoriteCancel(photoBoardNum);
	 }

	public int insertPBHashTag(PhotoHashtagVO photoHashtag) {
		
		return dao.insertPBHashTag(photoHashtag);
	}
	public ArrayList<PhotoHashtagVO> allPBHashTag() {
		return dao.allPBHashTag();
	}

	public int deletePBHashTag(int photoBoardNum) {
		
		return dao.deletePBHashTag(photoBoardNum);
	}

	public int photoBoardDelete(int photoBoardNum) {
		
		return dao.photoBoardDelete(photoBoardNum);
	}

	public ArrayList<PhotoBoardSaveVO> allPhotoBoardsave() {
		
		return dao.allPhotoBoardsave();
	}

	public ArrayList<PhotoReplyVO> allPhotoReply() {
		
		return dao.allPhotoReply();
	}
	
	public int getListCnt() {
		return dao.getListCnt();
	}


}
