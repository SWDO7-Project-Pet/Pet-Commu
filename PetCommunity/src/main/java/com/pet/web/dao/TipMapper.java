package com.pet.web.dao;

import java.util.ArrayList;

import com.pet.web.vo.AnimalDataPhotoSaveVO;
import com.pet.web.vo.AnimalDataVO;
import com.pet.web.vo.TipVO;

public interface TipMapper {

	ArrayList<TipVO> selectTip();

	int insertAnimal(AnimalDataVO newAnimal);

	int selectAnimalNum();

	int uploadFile(AnimalDataPhotoSaveVO file);

	ArrayList<AnimalDataVO> selectAnimalData(String animalKind);

	AnimalDataVO getFileInfo(int animalNum);

	AnimalDataVO selectUnAnimalData(int animalNum);

	ArrayList<AnimalDataPhotoSaveVO> selectPhoto(int animalNum);

	ArrayList<AnimalDataVO> selectOnly(String animalKind);

	int insertTip(TipVO tip);

	int deleteTip(int tipNum);

	int updateTip(TipVO tip);

	int deleteAnimal(int animalNum);

	int updateAnimal(AnimalDataVO newAnimal);

}
