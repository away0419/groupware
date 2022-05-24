package com.it.groupware.boardFile.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BoardFileService {
	int insertFile(BoardFileDTO fileVo);
	List<BoardFileDTO> selectByNo(int boardNo);
	BoardFileDTO selectByFileNo(int boardFileNo);
	int deleteFile(int boardFileNo);
	void insertFileAll(List<MultipartFile> list, BoardFileDTO fileDto, int boardNo);
}
