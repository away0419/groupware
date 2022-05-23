package com.it.groupware.boardFol.model;

import java.util.List;

public interface BoardFolService {
	int insertBoardFol(BoardFolDTO vo);
	List<BoardFolDTO> selectBoardFol();
	BoardFolDTO selectByNo(int boardFolderNo);
	List<BoardFolDTO> otherFolder();
	int updateBoardFol(BoardFolDTO vo);
	int deleteBoardFol(int boardFolderNo);
}
