package com.it.groupware.boardLike.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.groupware.boardLike.model.BoardLikeDTO;
import com.it.groupware.boardLike.model.BoardLikeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class boardLikeController {
	private final Logger logger=LoggerFactory.getLogger(boardLikeController.class);
	
	private final BoardLikeService boardLikeService;
  
	@ResponseBody
	@RequestMapping(value = "/like/insert")
	public int insert(@RequestParam(defaultValue = "0") int boardNo, HttpSession session) {
		logger.info("좋아요 등록 처리, 파라미터 boardNo={}", boardNo);
		
		String empNo=(String)session.getAttribute("empNo");
		
		BoardLikeDTO likeVo = new BoardLikeDTO();
		likeVo.setBoardNo(boardNo);
		likeVo.setEmpNo(Integer.parseInt(empNo));
		
		boardLikeService.insertLike(likeVo);
		
		int cnt = boardLikeService.selectLike(boardNo);
		
		logger.info("좋아요 테이블에 사원추가");
		
		return cnt;
	}
	
	@ResponseBody
	@RequestMapping(value = "/like/delete")
	public int delete(@RequestParam(defaultValue = "0")int boardNo, HttpSession session) {
		logger.info("좋아요 삭제 처리, 파라미터 boardNo={}", boardNo);

		String empNo=(String)session.getAttribute("empNo");
		
		BoardLikeDTO likeVo = new BoardLikeDTO();
		likeVo.setBoardNo(boardNo);
		likeVo.setEmpNo(Integer.parseInt(empNo));

		boardLikeService.deleteLike(likeVo);
		
		int cnt = boardLikeService.selectLike(boardNo);
		logger.info("좋아요 테이블에서 삭제");
		
		return cnt;
	}

}
