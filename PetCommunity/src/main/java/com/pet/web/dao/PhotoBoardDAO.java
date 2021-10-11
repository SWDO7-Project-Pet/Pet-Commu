package com.pet.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.web.vo.PhotoBoardSaveVO;
import com.pet.web.vo.PhotoBoardVO;
import com.pet.web.vo.PhotoHashtagVO;
import com.pet.web.vo.PhotoLikesVO;
import com.pet.web.vo.PhotoReplyVO;

@Repository
public class PhotoBoardDAO {
	
	
	
	  @Autowired 
	  private SqlSession session;
	  
	  public int writeBoard(PhotoBoardVO photoBoard) { 
		  int result = 0;
		  PhotoBoardMapper mapper = null; 
	  try { 
		  mapper = session.getMapper(PhotoBoardMapper.class); 
		  result = mapper.writeBoard(photoBoard); 
	  } catch (Exception e) { 
		  e.printStackTrace(); 
		 }
	  
	  return result; }
	  
	
	  public int uploadPhotoBoard(PhotoBoardSaveVO photoBoardSave) { 
			 int result = 0; 
			 PhotoBoardMapper mapper = null; 
			 try { 
				 mapper = session.getMapper(PhotoBoardMapper.class); 
				 result = mapper.uploadPhotoBoard(photoBoardSave); 
		  } catch (Exception e) {
			  e.printStackTrace(); 
		  }
	  
	  
			 return result; 
	  }
	 
	  public int photoBoardUpdate(PhotoBoardVO photoBoard) {
		  int result = 0; 
		  PhotoBoardMapper mapper = null; 
		  try { 
			  mapper = session.getMapper(PhotoBoardMapper.class); 
			  result = mapper.photoBoardUpdate(photoBoard); 
			  }catch (Exception e) {
				  e.printStackTrace(); 
				  } 
		  return result; 
		  }
	  
	  
	  
	
	  public int selectOnePhotoBoard(String photoBoardTitle) { 
		  int result = 0; 
		  PhotoBoardMapper mapper = null; 
		  try { 
			  mapper = session.getMapper(PhotoBoardMapper.class); 
			  result = mapper.selectOnePhotoBoard(photoBoardTitle); 
			  }catch (Exception e) {
				  e.printStackTrace(); 
				  } 
		  return result; 
		  }
	 
	 public ArrayList<PhotoBoardVO> selectAllPhotoBoard(){
		 ArrayList<PhotoBoardVO> result =null;
		 PhotoBoardMapper mapper = null;
		 try {
			mapper = session.getMapper(PhotoBoardMapper.class);
			result = mapper.selectAllPhotoBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return result;
	 }
	 
	 public PhotoBoardVO photoBoardRead(int photoBoardNum) {
		 PhotoBoardVO result = null;
		 PhotoBoardMapper mapper = null;
		 try {
			 mapper = session.getMapper(PhotoBoardMapper.class);
			 result = mapper.photoBoardRead(photoBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return result;
	 }
	 public ArrayList<PhotoReplyVO> photoBoardReadReply(int photoBoardNum) {
		 ArrayList<PhotoReplyVO> result = null;
		 PhotoBoardMapper mapper = null;
		 try {
			 mapper = session.getMapper(PhotoBoardMapper.class);
			 result = mapper.photoBoardReadReply(photoBoardNum);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return result;
	 }
	 
	 public int photoBoardReplyWrite(PhotoReplyVO photoReply) {
		 int result = 0;
		 PhotoBoardMapper mapper = null;
		 try {
			 mapper = session.getMapper(PhotoBoardMapper.class);
			 result = mapper.photoBoardReplyWrite(photoReply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return result;
	 }

	public int photoBoardReplyUpdate(PhotoReplyVO photoReply) {
		int result = 0;
		PhotoBoardMapper mapper = null;
		try {
			 mapper = session.getMapper(PhotoBoardMapper.class);
			 result = mapper.photoBoardReplyUpdate(photoReply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int photoBoardReplyDelete(int photoReplyNum) {
		int result = 0;
		PhotoBoardMapper mapper = null;
		try {
			 mapper = session.getMapper(PhotoBoardMapper.class);
			 result = mapper.photoBoardReplyDelete(photoReplyNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	  public int photoBoardInsertFavorite(PhotoLikesVO photoLikes) {
		  int result = 0;
		  PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 result = mapper.photoBoardInsertFavorite(photoLikes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  return result;
	  }
	  
	  public int photoBoardUpdateFavorite(int photoBoardNum) {
		  int result = 0;
		  PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 result = mapper.photoBoardUpdateFavorite(photoBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return result;
	  }


	  public ArrayList<HashMap<String, Object>> allFavorite(String memberId) {
		  ArrayList<HashMap<String, Object>> photoLikesList  = null;
		  PhotoBoardMapper mapper = null;
		  try {
			  mapper = session.getMapper(PhotoBoardMapper.class);
			  photoLikesList = mapper.allFavorite(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  return photoLikesList;
	  }
	  
	  
	  public int photoBoardDeleteFavorite(PhotoLikesVO photoLikes) {
		  int result = 0;
		  PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 result = mapper.photoBoardDeleteFavorite(photoLikes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  return result;
	  }
	  
	  public int photoBoardUpdateFavoriteCancel(int photoBoardNum) {
		  int result = 0;
		  PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 result = mapper.photoBoardUpdateFavoriteCancel(photoBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return result;
	  }


	public int insertPBHashTag(PhotoHashtagVO photoHashtag) {
		  int result = 0;
		  PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 result = mapper.insertPBHashTag(photoHashtag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return result;
	}
	public ArrayList<PhotoHashtagVO> allPBHashTag() {
		ArrayList<PhotoHashtagVO> photoHashtagList = null;
		  PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 photoHashtagList = mapper.allPBHashTag();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return photoHashtagList;
	}

	public int deletePBHashTag(int photoBoardNum) {
		  int result = 0;
		  PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 result = mapper.deletePBHashTag(photoBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return result;
	}

	public int photoBoardDelete(int photoBoardNum) {
		  int result = 0;
		  PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 result = mapper.photoBoardDelete(photoBoardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return result;
	}


	public ArrayList<PhotoBoardSaveVO> allPhotoBoardsave() {
		ArrayList<PhotoBoardSaveVO> photoBoardSaveList = null;
		PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 photoBoardSaveList = mapper.allPhotoBoardsave();
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return photoBoardSaveList;
	}


	public ArrayList<PhotoReplyVO> allPhotoReply() {
		ArrayList<PhotoReplyVO> photoReplyList =null;
		PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 photoReplyList = mapper.allPhotoReply();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoReplyList;
	}
	
	public int getListCnt() {
		int result = 0;
		PhotoBoardMapper mapper = null;
		  try {
				 mapper = session.getMapper(PhotoBoardMapper.class);
				 result = mapper.getListCnt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
