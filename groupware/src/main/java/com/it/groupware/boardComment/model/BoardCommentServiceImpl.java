package com.it.groupware.boardComment.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService{
	private final BoardCommentDAO boardCommentDao;

	@Override
	public int insertComment(BoardCommentDTO commVo) {
		return boardCommentDao.insertComment(commVo);
	}

	@Override
	public List<BoardCommentDTO> selectByNo(int boardNo) {
		return boardCommentDao.selectByNo(boardNo);
	}

	@Override
	public int insertReply(BoardCommentDTO commVo) {
		return boardCommentDao.insertReply(commVo);
	}

	@Override
	public int deleteComm(int boardCommentNo) {
		return boardCommentDao.deleteComm(boardCommentNo);
	}

}
