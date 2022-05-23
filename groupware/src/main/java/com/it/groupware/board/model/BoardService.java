package com.it.groupware.board.model;

import java.util.List;

import com.it.groupware.common.SearchVO;

public interface BoardService {
	int insertBoard(BoardDTO vo);
	List<BoardDTO> selectBoard(SearchVO searchVo);
	int selectTotalRecord(SearchVO searchVo);
	//int selectByFol(int boardFolderNo);
	List<BoardDTO> selectBoardMain(int boardFolderNo);
	int updateReadCount(int boardNo);
	BoardDTO selectByNo(int boardNo);
	int updateBoard(BoardDTO vo);
	int deleteBoard(int boardNo);
	int updateCommentCnt(int boardNo);
	int updateLikeCntUp(int boardNo);
	int updateLikeCntDown(int boardNo);
}
