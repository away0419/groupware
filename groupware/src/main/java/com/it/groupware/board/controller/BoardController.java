package com.it.groupware.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.multi.MultiMenuItemUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.it.groupware.board.model.BoardDTO;
import com.it.groupware.board.model.BoardService;
import com.it.groupware.boardComment.model.BoardCommentDTO;
import com.it.groupware.boardComment.model.BoardCommentService;
import com.it.groupware.boardFile.model.BoardFileDTO;
import com.it.groupware.boardFile.model.BoardFileService;
import com.it.groupware.boardFol.model.BoardFolDTO;
import com.it.groupware.boardFol.model.BoardFolService;
import com.it.groupware.boardLike.model.BoardLikeDTO;
import com.it.groupware.boardLike.model.BoardLikeService;
import com.it.groupware.common.ConstUtil;
import com.it.groupware.common.FileUploadUtil;
import com.it.groupware.common.PaginationInfo;
import com.it.groupware.common.SearchVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Api(tags = "?????????")
public class BoardController {
	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
	
	private final BoardService boardService;
	private final BoardFolService boardFolService;
	private final BoardFileService boardFileService;
	private final BoardCommentService boardCommentService;
	private final BoardLikeService boardLikeService;
	
	@GetMapping
	@ApiOperation(value = "????????? ?????? ??????")
	public ResponseEntity<Map<String,Object>> main() {
		Map<String,Object> data = new HashMap<>();
		
		List<BoardDTO> noticeList = boardService.selectBoardMain(1);
		List<BoardDTO> referenceList = boardService.selectBoardMain(2);
		List<BoardDTO> communityList = boardService.selectBoardMain(3);
		
		/* top ????????? ?????? list ?????? */
		List<BoardFolDTO> otherFolder=boardFolService.otherFolder();
		
		data.put("noticeList", noticeList);
		data.put("referenceList", referenceList);
		data.put("communityList", communityList);
		data.put("otherFolder", otherFolder);

		return new ResponseEntity<Map<String,Object>>(data, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "????????? ??????")
	public ResponseEntity<String> write(@RequestBody BoardDTO dto, @RequestBody BoardFileDTO fileDto,
			List<MultipartFile> fileList) {
		logger.info("????????? ?????? ??????, ???????????? vo={}", dto);
		
		/* ????????? ?????? */
		boardService.insertBoard(dto);
		
		/* ?????? ????????? ??????*/
		boardFileService.insertFileAll(fileList, fileDto, dto.getBoardNo());
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);
	}

	@GetMapping("/boardList")
	@ApiOperation(value="????????? ??????")
	public ResponseEntity<Map<String, Object>> list(SearchVO searchVo, int boardFolderNo) {
		//1
		logger.info("????????? ?????? ?????????, ???????????? boardFolderNo={}", boardFolderNo);
		
		Map<String, Object> data = new HashMap<>();
		
		/* ????????? ?????? */
		PaginationInfo pagingInfo = new PaginationInfo();
	    pagingInfo.setCurrentPage(searchVo.getCurrentPage());
	    pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
	      
	    searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
	    searchVo.setPerPage(ConstUtil.RECORD_COUNT);
	    searchVo.setBoardFolderNo(boardFolderNo);
	    logger.info("????????? ?????? ?????? ?????? ??? serachVo={}", searchVo);
	      
	    /* ????????? ?????? */
		BoardFolDTO boFol = boardFolService.selectByNo(boardFolderNo);
		logger.info("????????? ?????? ??????, boFol={}", boFol);
		
		/* top ????????? ?????? list ?????? */
		List<BoardFolDTO> otherFolder=boardFolService.otherFolder();
		
		//2
		List<BoardDTO> boardList = boardService.selectBoard(searchVo);
		logger.info("????????? ?????? ??????, list.size={}", boardList.size());
		
		int totalRecord = boardService.selectTotalRecord(searchVo);
	    logger.info("totalRecord="+totalRecord);
	    pagingInfo.setTotalRecord(totalRecord);
	    
	    //3
		data.put("boFol", boFol);
		data.put("list", boardList);
		data.put("otherFolder", otherFolder);
		data.put("pagingInfo", pagingInfo);

		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/{boardNo}")
	@ApiOperation(value ="????????? ??????")
	public ResponseEntity<Map<String, Object>> detail(@PathVariable int boardNo, int empNo) {
		logger.info("????????? ???????????? ?????????, ???????????? boardNo={}", boardNo);
		Map<String,Object> data = new HashMap<>();
		BoardDTO dto = boardService.selectByNo(boardNo);
		List<BoardFileDTO> fileList = boardFileService.selectByNo(boardNo);
		List<BoardCommentDTO> commList = boardCommentService.selectByNo(boardNo);
		
		BoardLikeDTO likeDto = new BoardLikeDTO();
		likeDto.setBoardNo(boardNo);
		likeDto.setEmpNo(empNo);
		
		int selectByEmpNo = boardLikeService.selectByEmpNo(likeDto);
		int likeCnt = boardLikeService.selectLike(boardNo);

		/* top ????????? ?????? list ?????? */
		List<BoardFolDTO> otherFolder=boardFolService.otherFolder();
		
		data.put("boardDto", dto);
		data.put("fileList", fileList);
		data.put("commList", commList);
		data.put("otherFolder", otherFolder);
		data.put("selectByEmpNo", selectByEmpNo);
		data.put("likeCnt", likeCnt);

		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	/* ?????? ???????????? ?????? */
	@GetMapping("/download")
	@ApiOperation(value="?????? ????????????")
	public void download(@RequestParam(defaultValue = "0")int boardFileNo,
			HttpServletResponse response) throws Exception {
		//1
		logger.info("???????????? ??????, ???????????? boardFileNo={}", boardFileNo);
		
		//2
		BoardFileDTO fileVO = boardFileService.selectByFileNo(boardFileNo);
		logger.info("originalFileName={}", fileVO.getOriginalFileName());
		
		//3
		String fileName = fileVO.getOriginalFileName();
		String fileSaveName = fileVO.getFileName();
		String filePath = ConstUtil.BOARD_UPLOAD_PATH_TEST+"\\";

		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName,"UTF-8") + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(filePath + fileSaveName);
		
		int readCount = 0;
		byte[] buffer = new byte[1024];

		while((readCount = fis.read(buffer)) != -1) {
		  	os.write(buffer, 0, readCount);
	    }
		fis.close();
		os.close();
	}


	@Transactional
	@PutMapping("/{boardNo}")
	@ApiOperation(value="????????? ??????")
	public ResponseEntity<String> update(@PathVariable int boardNo, @RequestBody BoardDTO boardDto, @RequestBody BoardFileDTO fileDto, 
			List<MultipartFile> fileList) {
		
		/* ?????? ????????? ??????*/
		boardFileService.insertFileAll(fileList, fileDto, boardNo);
		boardService.updateBoard(boardDto);
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);
	}
	
	@Transactional
	@DeleteMapping("/{boardNo}")
	@ApiOperation(value="????????? ??????")
	public ResponseEntity<String> delete(@PathVariable int boardNo, List<String> fileList) {
		logger.info("????????? ?????? ??????");
		
		for(String str: fileList) {
			int boardFileNo = Integer.parseInt(str);
			boardFileService.deleteFile(boardFileNo);
		}
		
		boardService.deleteBoard(boardNo);
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);
	}
	
	/* ?????? ?????? ?????? */
	@DeleteMapping(value="/boardFileDel")
	@ApiOperation(value="????????? ?????? ?????? ?????? ??????")
	public ResponseEntity<String> deleteFile(int boardFileNo) {
		boardFileService.deleteFile(boardFileNo);
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS,HttpStatus.OK);
	}
	
}


