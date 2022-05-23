package com.it.groupware.electronicFile.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElectronicFileDAO {
	
	int insertFile(ElectronicFileDTO vo);
	List<ElectronicFileDTO> selectFileByEleNo(int electronicNo);
	ElectronicFileDTO selectFileByFileNo(int fileNo);
	int deleteFile(int electronicNo);
}
