package com.it.groupware.addBook.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.groupware.addBook.model.AddBookDTO;
import com.it.groupware.addBook.model.AddBookService;
import com.it.groupware.addBookFol.model.AddBookFolDTO;
import com.it.groupware.addBookFol.model.AddBookFolService;
import com.it.groupware.attendDay.model.ConditionViewDTO;
import com.it.groupware.common.ConstUtil;
import com.it.groupware.common.PaginationInfo;
import com.it.groupware.common.SearchVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/addressBook")
@RequiredArgsConstructor
@Api(tags ="주소록")
public class AddBookController {
	private static final Logger logger = LoggerFactory.getLogger(AddBookController.class);

	private final AddBookService addBookService;
	private final AddBookFolService addBookFolService;

	/* 주소록 메인 */
	@GetMapping
	@ApiOperation(value = "주소록 전체 목록")
	public ResponseEntity<Map<String, Object>> bookMain(SearchVO searchVo, @ApiIgnore String dong, int empNo) {
		logger.info("주소록 메인 페이지, 파라미터 empNo={}", empNo);

		Map<String, Object> map = new HashMap<>();

		/* 페이징 처리 */
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);

		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setEmpNo(Integer.toString(empNo));
		logger.info("페이지 번호 관련 셋팅 후 serachVo={}", searchVo);

		/* 주소록 목록 조회 */
		List<Map<String, Object>> bookList = addBookService.selectAllAddBook(searchVo);
		logger.info("bookList.size()={}", bookList.size());

		int totalRecord = addBookService.selectAllTotalRecord(searchVo);
		logger.info("totalRecord=" + totalRecord);
		pagingInfo.setTotalRecord(totalRecord);

		/* top, 등록, 수정 카테고리 list처리 */
		List<AddBookFolDTO> bookFolList = addBookFolService.selectFol(empNo);
		logger.info("bookFolList.size={}", bookFolList.size());

		map.put("bookList", bookList);
		map.put("bookFolList", bookFolList);
		map.put("pagingInfo", pagingInfo);
//		logger.debug("bookList, {}", bookList);
//		logger.debug("bookFolList, {}", bookFolList);
//		logger.debug("paginInfo, {}", pagingInfo);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	/* 주소록 등록 */
	@PostMapping
	@ApiOperation(value = "주소록 등록")
	public ResponseEntity<String> insert(@RequestBody AddBookDTO dto, int empNo) {
		logger.info("주소록 등록 처리, 파라미터 vo={}", dto);

		dto.setEmpNo(empNo);
		int cnt = addBookService.insertAddBook(dto);

		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);
	}

	/* 주소록 수정(처리) */
	@PutMapping
	@ApiOperation(value = "주소록 수정")
	public ResponseEntity<String> update_post(@RequestBody AddBookDTO vo) {
		logger.info("주소록 수정 처리, 파라미터 vo={}", vo);
		int cnt = addBookService.updateAddBook(vo);

		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);
	}

	/* 주소록 삭제 */
	@DeleteMapping(produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "주소록 삭제")
	public ResponseEntity<String> delete(@RequestParam(value = "checkBoxArr[]") List<String> checkBoxArr) {

		for (String str : checkBoxArr) {
			addBookService.deleteAddBook(Integer.parseInt(str));
		}

		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);
	}

	/* 주소록 카테고리별 목록 */
	@GetMapping("/category")
	@ApiOperation(value = "카테고리별 주소록 목록")
	public ResponseEntity<Map<String, Object>> selectFolNoList(@RequestParam(defaultValue = "0") int addressFolderNo, SearchVO searchVo, int empNo) {
		logger.info("카테고리별 목록 조회, 파라미터 addressFolderNo={}", addressFolderNo);
		
		Map<String, Object> map = new HashMap<>();
		
		/* 페이징 처리 */
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);

		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setAddressFolderNo(addressFolderNo);
		logger.info("페이지 번호 관련 셋팅 후 serachVo={}", searchVo);

		int totalRecord = addBookService.selectByFolNoTotalRecord(searchVo);
		logger.info("totalRecord=" + totalRecord);
		pagingInfo.setTotalRecord(totalRecord);

		/* 목록조회 */
		List<Map<String, Object>> list = addBookService.selectByFolNo(searchVo);
		logger.info("list.size()={}", list.size());

		/* 카테고리명 뿌려주기 위한 VO */
		AddBookFolDTO folDto = addBookFolService.selectByFolNo(addressFolderNo);
		logger.info("folDto={}", folDto);

		/* top, 등록, 수정 카테고리 list처리 */
		List<AddBookFolDTO> bookFolList = addBookFolService.selectFol(empNo);
		logger.info("bookFolList.size={}", bookFolList.size());

		map.put("list", list);
		map.put("folDto", folDto);
		map.put("pagingInfo", pagingInfo);
		map.put("bookFolList", bookFolList);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	/* 주소록 양식 엑셀 */
	@GetMapping(path="/addressBookExcel", produces = "application/vnd.ms-excel")
	@ApiOperation(value = "주소록 양식 다운")
	public void AddressBookExcel(HttpServletResponse response) throws Exception { 
		HSSFWorkbook objWorkbook = new HSSFWorkbook();
		HSSFSheet objSheet =null;
		HSSFRow objRow=null;
		HSSFCell objCell=null;
		
		HSSFFont font = objWorkbook.createFont();
		font.setFontHeightInPoints((short)(9));
		font.setFontName("맑은고딕");
		
		
		HSSFCellStyle styleHd = objWorkbook.createCellStyle();
		HSSFCellStyle styleHd2 = objWorkbook.createCellStyle();
		styleHd.setFont(font);
		styleHd.setBorderBottom(BorderStyle.THIN);
		styleHd.setBorderTop(BorderStyle.THIN);
		styleHd.setBorderLeft(BorderStyle.THIN);
		styleHd.setBorderRight(BorderStyle.THIN);
		styleHd.setAlignment(HorizontalAlignment.CENTER);
		styleHd2.setFont(font);
		styleHd2.setBorderBottom(BorderStyle.THIN);
		styleHd2.setBorderTop(BorderStyle.THIN);
		styleHd2.setBorderLeft(BorderStyle.THIN);
		styleHd2.setBorderRight(BorderStyle.THIN);
		styleHd2.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		styleHd2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHd2.setAlignment(HorizontalAlignment.CENTER);
		objSheet = objWorkbook.createSheet("첫번째 시트");
		
		objRow = objSheet.createRow(0);
		objRow.setHeight((short)0x150);
		
		objCell = objRow.createCell(0);
		objCell.setCellValue("이름");
		objCell.setCellStyle(styleHd2);
		
		objCell = objRow.createCell(1);
		objCell.setCellValue("전화번호");
		objCell.setCellStyle(styleHd2);
		
		objCell = objRow.createCell(2);
		objCell.setCellValue("이메일");
		objCell.setCellStyle(styleHd2);
		
		objCell = objRow.createCell(3);
		objCell.setCellValue("직위");
		objCell.setCellStyle(styleHd2);
		
		objCell = objRow.createCell(4);
		objCell.setCellValue("회사");
		objCell.setCellStyle(styleHd2);
		
		objCell = objRow.createCell(5);
		objCell.setCellValue("회사번호");
		objCell.setCellStyle(styleHd2);
		
		objCell = objRow.createCell(6);
		objCell.setCellValue("카테고리");
		objCell.setCellStyle(styleHd2);
		
		objCell = objRow.createCell(7);
		objCell.setCellValue("성별");
		objCell.setCellStyle(styleHd2);
		
		
		objSheet.setColumnWidth(0, objSheet.getColumnWidth(0)+256);
		objSheet.setColumnWidth(1, objSheet.getColumnWidth(1)+2048);
		objSheet.setColumnWidth(2, objSheet.getColumnWidth(2)+4096);
		objSheet.setColumnWidth(3, objSheet.getColumnWidth(3)+1024);
		objSheet.setColumnWidth(4, objSheet.getColumnWidth(4)+1024);
		objSheet.setColumnWidth(5, objSheet.getColumnWidth(5)+2048);
		objSheet.setColumnWidth(6, objSheet.getColumnWidth(6)+4096);
		objSheet.setColumnWidth(7, objSheet.getColumnWidth(7)+256);
		
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment;FileName="+URLEncoder.encode("주소록 양식","UTF-8")+".xls");
		
		OutputStream fileOut = response.getOutputStream();
		objWorkbook.write(fileOut);
		fileOut.close();
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		
	}

}
