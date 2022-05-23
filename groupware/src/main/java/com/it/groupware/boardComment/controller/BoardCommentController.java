package com.it.groupware.boardComment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.groupware.board.model.BoardDAO;
import com.it.groupware.boardComment.model.BoardCommentDTO;
import com.it.groupware.boardComment.model.BoardCommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardCommentController {
	private static final Logger logger=LoggerFactory.getLogger(BoardCommentController.class);
	
	private final BoardCommentService boardCommentService;
	private final BoardDAO boardDao;
	
	@PostMapping("/comment/commentWrite")
	public String commentWrite(@ModelAttribute BoardCommentDTO commVo, @RequestParam(defaultValue = "0")int boardNo) {
		logger.info("댓글 등록 처리, 파라미터 commVo={}", commVo);
		
		boardCommentService.insertComment(commVo);
		boardDao.updateCommentCnt(boardNo);
		return "redirect:/board/boardDetail?boardNo="+boardNo;
	}
	
	@PostMapping("/comment/replyWrite")
	public String replyWrite(@ModelAttribute BoardCommentDTO commVo, @RequestParam(defaultValue = "0")int boardNo) {
		logger.info("답변 등록 처리, 파라미터 commVo={}", commVo);
		
		boardCommentService.insertReply(commVo);
		boardDao.updateCommentCnt(boardNo);
		
		return "redirect:/board/boardDetail?boardNo="+boardNo;
	}
	
	@RequestMapping("/comment/delete")
	public String delete(@RequestParam(defaultValue = "0")int boardCommentNo,
			@RequestParam(defaultValue = "0")int boardNo, Model model) {
		logger.info("댓글 삭제 처리, 파라미터 boardCommentNo={}, boardNo={}", boardCommentNo, boardNo);
		
		int cnt = boardCommentService.deleteComm(boardCommentNo);
		logger.info("cnt={}", cnt);
		String msg="댓글 삭제를 실패하였습니다.", url="/board/boardDetail?boardNo="+boardNo;
		if(cnt>0) {
			msg="댓글을 삭제하였습니다.";
			url="/board/boardDetail?boardNo="+boardNo;
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
}
