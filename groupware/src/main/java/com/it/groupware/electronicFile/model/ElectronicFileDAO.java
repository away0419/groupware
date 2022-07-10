package com.it.groupware.electronicFile.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElectronicFileDAO {
	
	ElectronicFileDTO selectFileByFileNo(int fileNo);
	int deleteFile(int electronicNo);
	
	List<ElectronicFileDTO> selectFileByEleNo(int electronicNo);//해당 문서 번호의 첨부 파일 목록
	int insertFile(ElectronicFileDTO dto); // 해당 문서에 파일 생성
}
