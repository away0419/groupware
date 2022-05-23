package com.it.groupware.electronicFile.model;

import java.util.List;

public interface ElectronicFileService {

	int insertFile(ElectronicFileDTO vo);
	List<ElectronicFileDTO> selectFileByEleNo(int electronicNo);
	ElectronicFileDTO selectFileByFileNo(int fileNo);
	int deleteFile(int electronicNo);
}
