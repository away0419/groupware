package com.it.groupware.board.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it.groupware.common.SearchVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardDAO boardDao;
	
	@Override
	public int insertBoard(BoardDTO vo) {
		return boardDao.insertBoard(vo);
	}

	@Override
	public List<BoardDTO> selectBoard(SearchVO searchVo) {
		return boardDao.selectBoard(searchVo);
	}

	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return boardDao.selectTotalRecord(searchVo);
	}
	
	@Override
	public List<BoardDTO> selectBoardMain(int boardFolderNo) {
		return boardDao.selectBoardMain(boardFolderNo);
	}

	@Override
	public int updateReadCount(int boardNo) {
		return boardDao.updateReadCount(boardNo);
	}

	@Override
	public BoardDTO selectByNo(int boardNo) {
		return boardDao.selectByNo(boardNo);
	}

	@Override
	public int updateBoard(BoardDTO vo) {
		return boardDao.updateBoard(vo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}

	@Override
	public int updateCommentCnt(int boardNo) {
		return boardDao.updateCommentCnt(boardNo);
	}

	@Override
	public int updateLikeCntUp(int boardNo) {
		return boardDao.updateLikeCntUp(boardNo);
	}

	@Override
	public int updateLikeCntDown(int boardNo) {
		return boardDao.updateLikeCntDown(boardNo);
	}

}
