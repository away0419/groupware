package com.it.groupware.boardFol.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardFolDAO {
	int insertBoardFol(BoardFolDTO vo);
	List<BoardFolDTO> selectBoardFol();
	BoardFolDTO selectByNo(int boardFolderNo);
	List<BoardFolDTO> otherFolder();
	int updateBoardFol(BoardFolDTO vo);
	int deleteBoardFol(int boardFolderNo);
}
