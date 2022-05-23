package com.it.groupware.boardFile.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardFileServiceImpl implements BoardFileService{
	private final BoardFileDAO boardFileDao;
	
	@Override
	public int insertFile(BoardFileDTO fileVo) {
		return boardFileDao.insertFile(fileVo);
	}

	@Override
	public List<BoardFileDTO> selectByNo(int boardNo) {
		return boardFileDao.selectByNo(boardNo);
	}

	@Override
	public BoardFileDTO selectByFileNo(int boardFileNo) {
		return boardFileDao.selectByFileNo(boardFileNo);
	}

	@Override
	public int deleteFile(BoardFileDTO fileVo) {
		return boardFileDao.deleteFile(fileVo);
	}

}
