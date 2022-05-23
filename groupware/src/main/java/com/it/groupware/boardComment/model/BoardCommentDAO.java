package com.it.groupware.boardComment.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardCommentDAO {
	int insertComment(BoardCommentDTO commVo);
	int insertReply(BoardCommentDTO commVo);
	List<BoardCommentDTO> selectByNo(int boardNo);
	int deleteComm(int boardCommentNo);
}
