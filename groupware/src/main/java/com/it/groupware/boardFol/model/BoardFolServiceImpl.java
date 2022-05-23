package com.it.groupware.boardFol.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardFolServiceImpl implements BoardFolService{
	private final BoardFolDAO boardFolDao;
	
	@Override
	public int insertBoardFol(BoardFolDTO vo) {
		return boardFolDao.insertBoardFol(vo);
	}

	@Override
	public List<BoardFolDTO> selectBoardFol() {
		return boardFolDao.selectBoardFol();
	}

	@Override
	public BoardFolDTO selectByNo(int boardFolderNo) {
		return boardFolDao.selectByNo(boardFolderNo);
	}

	@Override
	public List<BoardFolDTO> otherFolder() {
		return boardFolDao.otherFolder();
	}

	@Override
	public int updateBoardFol(BoardFolDTO vo) {
		return boardFolDao.updateBoardFol(vo);
	}

	@Override
	public int deleteBoardFol(int boardFolderNo) {
		return boardFolDao.deleteBoardFol(boardFolderNo);
	}


}
