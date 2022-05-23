package com.it.groupware.attendDay.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.groupware.attendDay.model.AttendDayDTO;
import com.it.groupware.attendDay.model.AttendDayService;
import com.it.groupware.attendDay.model.ConditionViewDTO;
import com.it.groupware.common.ConstUtil;
import com.it.groupware.common.PaginationInfo;
import com.it.groupware.schedule.controller.ScheduleController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendDay")
@RequiredArgsConstructor
@Api(tags ="출퇴근")
public class AttendDayController {
	private final AttendDayService attendDayService;
	
	private static final Logger logger
	=LoggerFactory.getLogger(ScheduleController.class);
	
	@PostMapping
	@ApiOperation(value="출근")
	public ResponseEntity<String> insertComTime(@RequestBody AttendDayDTO dto){
		attendDayService.insertAttendDay(dto); // AttendDay 등록
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiOperation(value="퇴근")
	public ResponseEntity<String> updateLeaveTime(@RequestBody AttendDayDTO dto){
		attendDayService.updateAttendDay(dto);
		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("/overtime")
	@ApiOperation(value ="초과 근무 승인 업데이트")
	public ResponseEntity<String> updateOverTime(int attendanceDayNo, int flag){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("attendanceDayNo",attendanceDayNo);
		map.put("flag",flag);
		attendDayService.updateAttendDayByFlag(map);
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.CREATED);
	}
	
	@GetMapping("/overTime")
	@ApiOperation(value ="초과 근무 목록")
	public ResponseEntity<Map<String, Object>> getOverTimeList(int btCheck, int currentPage){
		/* 페이징 블럭 만큼만 초과근무 승인 List 조회*/
		Map<String, Object> map = new HashMap<>();
		if(btCheck==1) {
			int block =currentPage/ConstUtil.BLOCK_SIZE_ANN + 1;
			currentPage= block*ConstUtil.BLOCK_SIZE_ANN +1;
		}else if(btCheck == 2) {
			int block =currentPage/ConstUtil.BLOCK_SIZE_ANN - 1;
			currentPage= block*ConstUtil.BLOCK_SIZE_ANN +1;			
		}
		
		List<ConditionViewDTO> conditionList= attendDayService.selectAttendDayByFlag(currentPage);
		map.put("conditionList", conditionList);
		
		/* 페이징 정보 */
		int TotalRecord = attendDayService.selecCnttAttendDayByFlag();
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setCurrentPage(currentPage);
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE_ANN);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT_ANN);
		pagingInfo.setTotalRecord(TotalRecord);
		map.put("pagingInfo", pagingInfo);
		
		logger.info("conditionList={}",conditionList);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/conditionMonthView")
	@ApiOperation(value ="근태 현황(해당 날짜가 속한 달) 목록")
	public ResponseEntity<Map<String, Object>> conditionMonthView(int departmentNo, String selectDate, int selectNum,int timeNum, String searchKeyword){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		map.put("departmentNo", departmentNo);
		map.put("selectDate", selectDate);
		map.put("selectNum", selectNum);
		map.put("timeNum", timeNum);
		map.put("searchKeyword", searchKeyword);
		
		data = attendDayService.conditionMonthView(map);
		
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/statsView")
	@ApiOperation(value ="근태 통계 목록")
	public ResponseEntity<Map<String, Object>> statsView(int selectItem1,int selectItem2,int selectItem3, String searchEmp,
											String searchDepart, String startDate,String endDate,String selectDate,
											int currentPage,int btCheck){
		/* 엑셀다운용 List 및 검색조건에 따른 List 반환 메서드의 매개변수용 map */
		Map<String, Object> map = new HashMap<>();
		map.put("selectItem1", selectItem1);
		map.put("selectItem2", selectItem2);
		map.put("selectItem3", selectItem3);
		map.put("searchEmp", searchEmp);
		map.put("searchDepart", searchDepart);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("selectDate", selectDate);
		
		if(btCheck==1) {
			int block =currentPage/ConstUtil.BLOCK_SIZE_ANN + 1;
			currentPage= block*ConstUtil.BLOCK_SIZE_ANN +1;
		}else if(btCheck == 2) {
			int block =currentPage/ConstUtil.BLOCK_SIZE_ANN - 1;
			currentPage= block*ConstUtil.BLOCK_SIZE_ANN +1;			
		}
		map.put("currentPage", currentPage);
		
		Map<String,Object> data = attendDayService.statsView(map);
		
		/* 페이징 정보 */
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setCurrentPage(currentPage);
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE_ANN);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT_ANN);
		pagingInfo.setTotalRecord((int)data.get("TotalRecord"));
		
		data.put("pagingInfo", pagingInfo);
		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/selectAttendDayView")
	@ApiOperation(value = "로그인 사원의 특정 날짜 근태 정보")
	public ResponseEntity<AttendDayDTO> selectAttendDayView(AttendDayDTO vo){
		
		AttendDayDTO attendDayVo = attendDayService.selectAttendDayByRegdate(vo);
		
		return new ResponseEntity<AttendDayDTO>(attendDayVo, HttpStatus.OK);
	}
	
	@GetMapping("/selectMonthSumTime")
	@ApiOperation(value="선택한 달의 누적 근무 시간")
	public ResponseEntity<Integer> selectMonthSumTime(int empNo,String nowDate){
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("empNo", empNo);
		map.put("nowDate", nowDate);
		int data = attendDayService.selectSumMonthWork(map);
		
		return new ResponseEntity<Integer>(data,HttpStatus.OK);
	}
	
	/* 선택된 달의 초과 근무 반환 AJAX*/
	@GetMapping("/selectMonthExTime")
	@ApiOperation(value="선택한 달의 초과 근무 시간")
	public ResponseEntity<Integer> selectMonthExTime(int empNo,String nowDate){

		HashMap<String, Object> map = new HashMap<>();
		map.put("empNo", empNo);
		map.put("nowDate", nowDate);
		int data = attendDayService.selectSumMonthWorkEx(map);
		
		return new ResponseEntity<Integer>(data,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/selectMonthWorkTime")
	@ApiOperation(value = "선택한 달의 주간 근무 시간")
	public ResponseEntity<List<Integer>> selectMonthWorkTime(String selectDate, int empNo){
		List<Integer> weekTimelist = attendDayService.selectMonthWorkTime(selectDate, empNo);
		
		return new ResponseEntity<List<Integer>>(weekTimelist, HttpStatus.OK);
	}
	
	@GetMapping("/selectMonth")
	@ApiOperation(value="선택한 월의 근태 정보")
	public ResponseEntity<List<AttendDayDTO>> selectMonth(String selectDate, int empNo){
		List<AttendDayDTO> attendMonthList = attendDayService.selectMonth(selectDate, empNo);

		return new ResponseEntity<List<AttendDayDTO>>(attendMonthList,HttpStatus.OK);
	}
	
	
	@GetMapping
	@ApiOperation(value="금일 기준, 회원의 이번달 근태 종합 정보")
	public ResponseEntity<Map<String,Object>> main(int empNo) {
		Map<String, Object> data = new HashMap<>();
		data = attendDayService.toDayAllData(empNo);
	
		return new ResponseEntity<Map<String,Object>>(data, HttpStatus.OK);
	}
	
	
	@GetMapping("/condition")
	@ApiOperation(value="근태 현황 종합 정보")
	public ResponseEntity<Map<String,Object>> condition(int empNo, int departmentNo) {
		Map<String, Object> data = new HashMap<>();
		data = attendDayService.condition(empNo, departmentNo);
	
		return new ResponseEntity<Map<String,Object>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/today")
	@ApiOperation(value="금일 출퇴근 정보")
	public ResponseEntity<AttendDayDTO> stats(int empNo) {
		AttendDayDTO dto =attendDayService.toDayAttendDay(empNo);
		return new ResponseEntity<AttendDayDTO>(dto, HttpStatus.OK);
	}
	
}
