package com.it.groupware.boardLike.model;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService {
	private final BoardLikeDAO boardLikeDao;

	@Override
	public int firstInsert(int boardNo) {
		return boardLikeDao.firstInsert(boardNo);
	}

	@Override
	public int insertLike(BoardLikeDTO likeVo) {
		return boardLikeDao.insertLike(likeVo);
	}

	@Override
	public int deleteLike(BoardLikeDTO likeVo) {
		return boardLikeDao.deleteLike(likeVo);
	}

	@Override
	public int selectLike(int boardNo) {
		return boardLikeDao.selectLike(boardNo);
	}

	@Override
	public int selectByEmpNo(BoardLikeDTO likeVo) {
		// TODO Auto-generated method stub
		return boardLikeDao.selectByEmpNo(likeVo);
	}
}
