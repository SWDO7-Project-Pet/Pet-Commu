package com.pet.web.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.web.vo.AnimalDataPhotoSaveVO;
import com.pet.web.vo.AnimalDataVO;
import com.pet.web.vo.TipVO;

@Repository
public class TipDAO {

	@Autowired
	private SqlSession session;

	public ArrayList<TipVO> selectTip() {
		ArrayList<TipVO> result = null;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.selectTip();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertAnimal(AnimalDataVO newAnimal) {
		int result = 0;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.insertAnimal(newAnimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateAnimal(AnimalDataVO newAnimal) {
		int result = 0;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.updateAnimal(newAnimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int selectAnimalNum() {
		int result = 0;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.selectAnimalNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int uploadFile(AnimalDataPhotoSaveVO file) {
		int result = 0;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.uploadFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<AnimalDataVO> selectAnimalData(String animalKind) {
		ArrayList<AnimalDataVO> result = null;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.selectAnimalData(animalKind);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public AnimalDataVO getFileInfo(int animalNum) {
		AnimalDataVO result = null;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.getFileInfo(animalNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<AnimalDataPhotoSaveVO> selectPhoto(int animalNum) {
		ArrayList<AnimalDataPhotoSaveVO> result = null;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.selectPhoto(animalNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<AnimalDataVO> selectOnly(String animalKind) {
		ArrayList<AnimalDataVO> result = null;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.selectOnly(animalKind);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertTip(TipVO tip) {
		int result = 0;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.insertTip(tip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteTip(int tipNum) {
		int result = 0;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.deleteTip(tipNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateTip(TipVO tip) {
		int result = 0;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.updateTip(tip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteAnimal(int animalNum) {
		int result = 0;
		TipMapper mapper = null;
		try {
			mapper = session.getMapper(TipMapper.class);
			result = mapper.deleteAnimal(animalNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	

	
	
}
