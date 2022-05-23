package com.it.groupware.boardComment.model;

import java.util.List;

public interface BoardCommentService {
	int insertComment(BoardCommentDTO commVo);
	int insertReply(BoardCommentDTO commVo);
	List<BoardCommentDTO> selectByNo(int boardNo);
	int deleteComm(int boardCommentNo);
}
