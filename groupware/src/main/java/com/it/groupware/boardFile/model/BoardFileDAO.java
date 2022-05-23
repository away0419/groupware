package com.it.groupware.boardFile.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardFileDAO {
	int insertFile(BoardFileDTO fileVo);
	List<BoardFileDTO> selectByNo(int boardNo);
	BoardFileDTO selectByFileNo(int boardFileNo);
	int deleteFile(BoardFileDTO fileVo);
}
