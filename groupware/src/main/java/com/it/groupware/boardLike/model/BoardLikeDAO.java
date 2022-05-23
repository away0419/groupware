package com.it.groupware.boardLike.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardLikeDAO {

	int firstInsert(int boardNo);
	int insertLike(BoardLikeDTO likeVo);
	int deleteLike(BoardLikeDTO likeVo);
	int selectLike(int boardNo);
	int selectByEmpNo(BoardLikeDTO likeVo);
}
