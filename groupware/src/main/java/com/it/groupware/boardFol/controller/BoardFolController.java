package com.it.groupware.boardFol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.groupware.boardFol.model.BoardFolDTO;
import com.it.groupware.boardFol.model.BoardFolService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/boardFol")
@RequiredArgsConstructor
public class BoardFolController {
	private static final Logger logger=LoggerFactory.getLogger(BoardFolController.class);
	
	private final BoardFolService boardFolService;
	
	@GetMapping("/allBoardFol")
	@ApiOperation(value="모든 게시판 폴더 목록 & 주요 게시판 목록 제외한 나머지 게시판 목록")
	public ResponseEntity<Map<String,Object>> allBoardFol() {
		logger.info("게시판 등록 페이지");
		Map<String,Object> data = new HashMap<>();
		
		/* 모든 게시판 폴더 목록 */
		List<BoardFolDTO> boFol = boardFolService.selectBoardFol();
		
		/* 주요 4가지 제외한 게시판 폴더 목록 */
		List<BoardFolDTO> otherFolder=boardFolService.otherFolder();
		
		data.put("boFol", boFol);
		data.put("otherFolder", otherFolder);
		
		return new ResponseEntity<Map<String,Object>>(data, HttpStatus.OK);
	}
	
	@RequestMapping("/main")
	public String adminBoard(Model model) {
		logger.info("관리자 - 게시판 페이지");
		
		/* 게시판 폴더 처리 */
		List<BoardFolDTO> boFol = boardFolService.otherFolder();
		logger.info("게시판 폴더 조회, boFol.size={}", boFol.size());
		
		model.addAttribute("boFol", boFol);
		model.addAttribute("navNo", 8);
		return "board/adminBoardFolMain";
	}
	
	/* 카테고리 추가 */
	@RequestMapping("/insert")
	public String insert(@ModelAttribute BoardFolDTO folVo) {
		logger.info("관리자 - 게시판 카테고리 수정 처리, 파라미터 folVo={}", folVo);

		boardFolService.insertBoardFol(folVo);

		return "redirect:/adminBoard/main";
	}
	
	/* 카테고리 수정 */
	@RequestMapping("/update")
	public String update(@ModelAttribute BoardFolDTO folVo) {
		logger.info("관리자 - 게시판 카테고리 등록 처리, 파라미터 folVo={}", folVo);
		
		boardFolService.updateBoardFol(folVo);
		
		return "redirect:/adminBoard/main";
	}

	/* 카테고리 삭제 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(defaultValue = "0") int boardFolderNo) {
		logger.info("관리자 - 게시판 카테고리 삭제 처리, 파라미터 boardFolderNo={}", boardFolderNo);
		
		boardFolService.deleteBoardFol(boardFolderNo);
		
		return "redirect:/adminBoard/main";
	}
}
