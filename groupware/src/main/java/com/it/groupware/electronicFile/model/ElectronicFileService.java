package com.it.groupware.electronicFile.model;

import java.util.List;

public interface ElectronicFileService {

	ElectronicFileDTO selectFileByFileNo(int fileNo);
	int deleteFile(int electronicNo); 
	int insertFile(ElectronicFileDTO dto); // 파일 등록
	List<ElectronicFileDTO> selectFileByEleNo(int electronicNo); //해당 문서 파일 목록
}
