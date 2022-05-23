package com.it.groupware.boardFile.model;

import java.util.List;

public interface BoardFileService {
	int insertFile(BoardFileDTO fileVo);
	List<BoardFileDTO> selectByNo(int boardNo);
	BoardFileDTO selectByFileNo(int boardFileNo);
	int deleteFile(BoardFileDTO fileVo);
}
