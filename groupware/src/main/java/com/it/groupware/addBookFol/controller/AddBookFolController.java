package com.it.groupware.addBookFol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.groupware.addBookFol.model.AddBookFolDTO;
import com.it.groupware.addBookFol.model.AddBookFolService;
import com.it.groupware.common.ConstUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookFolInfo")
@RequiredArgsConstructor
@Api(tags = "주소록 카테고리")
public class AddBookFolController {
	private static final Logger logger=LoggerFactory.getLogger(AddBookFolController.class);
	
	private final AddBookFolService addBookFolService;
	
	@GetMapping
	@ApiOperation(value="카테고리 전체 정보")
	public ResponseEntity<List<AddBookFolDTO>> folInfo(int empNo) {
		logger.info("카테고리 정보 페이지, empNo={}", empNo);
		
		List<AddBookFolDTO> bookFolList = addBookFolService.selectFol(empNo);
		
		return new ResponseEntity<List<AddBookFolDTO>>(bookFolList,HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value="카테고리 추가 처리")
	public ResponseEntity<String> insert(@RequestBody AddBookFolDTO dto) {
		logger.info("카테고리 등록 처리, 파라미터 dto={}", dto);
		
		int cnt = addBookFolService.insertFol(dto);
		if(cnt<=0) {
			return new ResponseEntity<String>(ConstUtil.FAIL,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS,HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value="카테고리 수정 처리")
	public ResponseEntity<String> update(@RequestBody AddBookFolDTO dto) {
		logger.info("카테고리 수정 처리, 파라미터 dto={}", dto);
		
		int cnt = addBookFolService.updateFol(dto);
		if(cnt<=0) {
			return new ResponseEntity<String>(ConstUtil.FAIL,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS,HttpStatus.OK);
	}

	@DeleteMapping
	@ApiOperation(value="카테고리 삭제 처리")
	public ResponseEntity<String> delete(int addressFolderNo) {
		logger.info("카테고리 삭제 처리, 파라미터 addressFolderNo={}", addressFolderNo);
		
		int cnt = addBookFolService.deleteFol(addressFolderNo);
		if(cnt<=0) {
			return new ResponseEntity<String>(ConstUtil.FAIL,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS,HttpStatus.OK);
	}
}
