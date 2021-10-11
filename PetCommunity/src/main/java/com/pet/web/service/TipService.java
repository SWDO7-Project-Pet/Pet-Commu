package com.pet.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.web.dao.TipDAO;
import com.pet.web.vo.AnimalDataPhotoSaveVO;
import com.pet.web.vo.AnimalDataVO;
import com.pet.web.vo.TipVO;

@Service
public class TipService {
	@Autowired
	private TipDAO dao;

	public ArrayList<TipVO> selectTip() {
		
		return dao.selectTip();
	}

	public boolean insertAnimal(String animalKind, String animalThumbnailOr, String animalThumbnailSt, String animalVariety, String animalOutline, String animalOrigin, String animalSize, String animalHeight, String animalWeight, String animalAppearance, String animalColor, String animalPersonality, String animalPurpose, String animalDisease, String animalRecommend, String memberId) {
		AnimalDataVO newAnimal = new AnimalDataVO();
		newAnimal.setAnimalKind(animalKind);
		newAnimal.setAnimalThumbnailOr(animalThumbnailOr);
		newAnimal.setAnimalThumbnailSt(animalThumbnailSt);
		newAnimal.setAnimalVariety(animalVariety);
		newAnimal.setAnimalOutline(animalOutline);
		newAnimal.setAnimalOrigin(animalOrigin);
		newAnimal.setAnimalSize(animalSize);
		newAnimal.setAnimalHeight(animalHeight);
		newAnimal.setAnimalWeight(animalWeight);
		newAnimal.setAnimalAppearance(animalAppearance);
		newAnimal.setAnimalColor(animalColor);
		newAnimal.setAnimalPersonality(animalPersonality);
		newAnimal.setAnimalPurpose(animalPurpose);
		newAnimal.setAnimalDisease(animalDisease);
		newAnimal.setAnimalRecommend(animalRecommend);
		newAnimal.setMemberId(memberId);
		if(dao.insertAnimal(newAnimal)>0) return true;
		return false;
	}

	public boolean updateAnimal(int animalNum,String animalKind, String animalVariety, String animalOutline, String animalOrigin, String animalSize, String animalHeight,String animalWeight, String animalAppearance, String animalColor, String animalPersonality,String animalPurpose, String animalDisease, String animalRecommend) {
		AnimalDataVO newAnimal = new AnimalDataVO();
		newAnimal.setAnimalNum(animalNum);
		newAnimal.setAnimalKind(animalKind);
		//newAnimal.setAnimalThumbnailOr(animalThumbnailOr);
		//newAnimal.setAnimalThumbnailSt(animalThumbnailSt);
		newAnimal.setAnimalVariety(animalVariety);
		newAnimal.setAnimalOutline(animalOutline);
		newAnimal.setAnimalOrigin(animalOrigin);
		newAnimal.setAnimalSize(animalSize);
		newAnimal.setAnimalHeight(animalHeight);
		newAnimal.setAnimalWeight(animalWeight);
		newAnimal.setAnimalAppearance(animalAppearance);
		newAnimal.setAnimalColor(animalColor);
		newAnimal.setAnimalPersonality(animalPersonality);
		newAnimal.setAnimalPurpose(animalPurpose);
		newAnimal.setAnimalDisease(animalDisease);
		newAnimal.setAnimalRecommend(animalRecommend);
		if(dao.updateAnimal(newAnimal)>0) return true;
		return false;
	}
	
	public int selectAnimalNum() {
		return dao.selectAnimalNum();
	}

	public boolean uploadFile(int animalNum, String savedFileName, String adBoardPhotoOr) {
		AnimalDataPhotoSaveVO file = new AnimalDataPhotoSaveVO();
		file.setAnimalNum(animalNum);
		file.setAdBoardPhotoSt(savedFileName);
		file.setAdBoardPhotoOr(adBoardPhotoOr);
		if(dao.uploadFile(file) > 0) {
			return true;
		}else {
			return false;
		}
	}

	public ArrayList<AnimalDataVO> selectAnimalData(String animalKind) {
		
		return dao.selectAnimalData(animalKind);
	}

	public AnimalDataVO getFileInfo(int animalNum) {
		return dao.getFileInfo(animalNum);
	}

	public ArrayList<AnimalDataPhotoSaveVO> selectPhoto(int animalNum) {
		
		return dao.selectPhoto(animalNum);
	}

	public ArrayList<AnimalDataVO> selectOnly(String animalKind) {
		
		return dao.selectOnly(animalKind);
	}

	public boolean insertTip(String tipTitle, String tipContent, String memberId) {
		TipVO tip = new TipVO();
		tip.setTipTitle(tipTitle);
		tip.setTipContent(tipContent);
		tip.setMemberId(memberId);
		if(dao.insertTip(tip)>0) return true;
		return false;
	}

	public boolean deleteTip(int tipNum) {
		 
		if(dao.deleteTip(tipNum)>0) return true;
		return false;		
	}

	public boolean updateTip(int tipNum, String tipTitle, String tipContent) {
		TipVO tip = new TipVO();
		tip.setTipNum(tipNum);
		tip.setTipTitle(tipTitle);
		tip.setTipContent(tipContent);
		if(dao.updateTip(tip)>0) return true;
		return false;	
	}

	public boolean deleteAnimal(int animalNum) {

		if(dao.deleteAnimal(animalNum)>0) return true;
		return false;	
	}
	
}
