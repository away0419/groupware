package com.it.groupware.electronicDocSty.model;

import java.util.List;

public interface ElectronicDocStyService {
	List<ElectronicDocStyDTO> selectAll(); //전체 양식 목록 
	ElectronicDocStyDTO selectByStyleNo(int styleNo); //해당 양식 번호를 가진 양식
	List<ElectronicDocStyDTO> selectByFolderNo(int styleNo); //해당 폴더 번호를 가진 양식 목록
	int selectByFolderStyleNo(int folderNo,int folderStyleNo); // 폴더 번호, 폴더 번호안의 순서와 동일한 양식
}
