package com.it.groupware.boardLike.model;

public interface BoardLikeService {
	int firstInsert(int boardNo);
	int insertLike(BoardLikeDTO likeVo);
	int deleteLike(BoardLikeDTO likeVo);
	int selectLike(int boardNo);
	int selectByEmpNo(BoardLikeDTO likeVo);
}
