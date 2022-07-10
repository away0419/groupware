package com.it.groupware.electronicDocSty.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElectronicDocStyDAO {
	List<ElectronicDocStyDTO> selectAll(); // 모든 양식 목록
	ElectronicDocStyDTO selectByStyleNo(int styleNo); // 해당 번호의 양식
	List<ElectronicDocStyDTO> selectByFolderNo(int folderNo); // 해당 폴더 번호인 양식 목록 
	int selectByFolderStyleNo(int folderNo,int folderStyleNo); // 폴더 번호와 폴더에서의 순서 가 일치하는 양식의 양식 번호
	
}
