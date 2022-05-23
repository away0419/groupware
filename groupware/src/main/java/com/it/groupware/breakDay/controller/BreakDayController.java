package com.it.groupware.breakDay.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.groupware.attend.model.AttendDTO;
import com.it.groupware.attend.model.AttendService;
import com.it.groupware.attendDay.model.AttendDayDTO;
import com.it.groupware.attendDay.model.AttendDayService;
import com.it.groupware.breakDay.model.BreakDayDTO;
import com.it.groupware.breakDay.model.BreakDayService;
import com.it.groupware.common.ConstUtil;
import com.it.groupware.common.PaginationInfo;
import com.it.groupware.emp.model.EmpDTO;
import com.it.groupware.emp.model.EmpService;
import com.it.groupware.schedule.controller.ScheduleController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/breakDay")
@RequiredArgsConstructor
@Api(tags ="휴가")
public class BreakDayController {
	private final AttendDayService attendDayService;
	private final AttendService attendService;
	private final BreakDayService breakDayService;
	private final EmpService empService;
	
	private static final Logger logger
	=LoggerFactory.getLogger(ScheduleController.class);

	@GetMapping("/currentList")
	@ApiOperation(value = "로그인 사원의 휴가 목록")
	public ResponseEntity<Map<String,Object>> currentList(int empNo,int currentPage, int btCheck){
		/* 페이징 블럭 만큼만 휴가 List 조회 AJAX*/
		if(btCheck==1) {
			int block =currentPage/ConstUtil.BLOCK_SIZE_ANN + 1;
			currentPage= block*ConstUtil.BLOCK_SIZE_ANN +1;
		}else if(btCheck == 2) {
			int block =currentPage/ConstUtil.BLOCK_SIZE_ANN - 1;
			currentPage= block*ConstUtil.BLOCK_SIZE_ANN +1;			
		}
		Map<String, Object> map = new HashMap<>();
		map.put("empNo", empNo);
		map.put("currentPage", currentPage);
		
		Map<String, Object> data = new HashMap<>();
		data = breakDayService.currentList(map);
		
		/* 페이징 정보*/
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setCurrentPage(currentPage);
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE_ANN);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT_ANN);
		pagingInfo.setTotalRecord((int)map.get("totalRecord"));
		
		data.put("pagingInfo", pagingInfo);
		
		return new ResponseEntity<Map<String,Object>>(data, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping
	@ApiOperation(value="휴가 종합 정보")
	public ResponseEntity<Map<String,Object>> annual(int empNo) {
		
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> data = new HashMap<>();
		PaginationInfo pagingInfo = new PaginationInfo();
		map.put("empNo", empNo);
		map.put("currentPage", 1);

		AttendDayDTO attendDayDto = attendDayService.toDayAttendDay(empNo);
		AttendDTO attendVo = attendService.selectAttendByEmpNo(empNo);
		List<BreakDayDTO> breakDayList = breakDayService.selectAllBreakDayByEmpNo(map);
		EmpDTO empVo = empService.selectByEmpNo(empNo);
		int TotalRecord = breakDayService.selectCntAllBreakDayByEmpNo(empNo);
		
		pagingInfo.setCurrentPage(1);
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE_ANN);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT_ANN);
		pagingInfo.setTotalRecord(TotalRecord);
		
		data.put("empVo", empVo);
		data.put("attendVo", attendVo);
		data.put("breakDayList", breakDayList);
		data.put("pagingInfo", pagingInfo);
		data.put("attendDayDto", attendDayDto);
		
		return new ResponseEntity<Map<String,Object>>(data,HttpStatus.OK);
		
	}
}
