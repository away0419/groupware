package com.it.groupware.emp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.groupware.attendDay.model.AttendDayDTO;
import com.it.groupware.attendDay.model.AttendDayService;
import com.it.groupware.booking.model.BookingDTO;
import com.it.groupware.booking.model.BookingService;
import com.it.groupware.common.ConstUtil;
import com.it.groupware.common.CustomErrorCode;
import com.it.groupware.common.CustomException;
import com.it.groupware.common.FileUploadUtil;
import com.it.groupware.common.ImageTranseform;
import com.it.groupware.common.PaginationInfo;
import com.it.groupware.common.SearchVO;
import com.it.groupware.department.model.DepartmentDTO;
import com.it.groupware.department.model.DepartmentService;
import com.it.groupware.electronic.model.ElectronicDTO;
import com.it.groupware.electronic.model.ElectronicService;
import com.it.groupware.electronicAppStamp.model.ElectronicAppStampDTO;
import com.it.groupware.electronicDocFol.model.ElectronicDocFolDTO;
import com.it.groupware.electronicDocSty.model.ElectronicDocStyDTO;
import com.it.groupware.email.model.EmailDTO;
import com.it.groupware.email.model.EmailService;
import com.it.groupware.emp.model.EmpDTO;
import com.it.groupware.emp.model.EmpService;
import com.it.groupware.position.model.PositionDTO;
import com.it.groupware.position.model.PositionService;
import com.it.groupware.schedule.model.ScheduleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emp")
@Api(tags = "회원")
public class EmpController {
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	private final ServletContext servletContext;
	private final EmpService empService;
	private final DepartmentService departmentService;
	private final PositionService positionService;
	private final FileUploadUtil fileUploadUtil;
	private final PasswordEncoder passwordEncoder;
	private final EmailService emailService;
	private final ElectronicService eleService;
	private final BookingService bookingService;
	private final ScheduleService scheduleService;
	private final AttendDayService attendDayService;
	private final ImageTranseform itf;

	// 로그인처리
	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<Map<String, Object>> login_post(@RequestBody EmpDTO empDto) {
		int empNo = empDto.getEmpNo();
		String empPwd = empDto.getEmpPwd();
		Map<String, Object> data = empService.login(empNo, empPwd);
		if (!(boolean) data.get("result")) {
			throw new CustomException(CustomErrorCode.UserNotFound, "로그인 에러");
		}
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}

	// 임시 비밀번호 발급
	@PutMapping("/findPwd")
	@ApiOperation(value = "임시 비밀번호 발급")
	public ResponseEntity<String> sendRandomPwd(@RequestBody EmpDTO empDto) {
		boolean res = empService.issuePwd(empDto);
		String str = ConstUtil.FAIL;
		if (res) {
			str = ConstUtil.SUCCESS;
			return new ResponseEntity<String>(str, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(str, HttpStatus.OK);
		}
	}

	// 부서 and 사원 가져오기
	@GetMapping("/selectEmpTree")
	@ApiOperation(value = "부서 + 사원 트리 가져오기")
	public ResponseEntity<Map<String, Object>> selectFormTree(String kind, @RequestParam(required = false, defaultValue = "0") int empNo) {
		int num = 0;
		if (kind.equals("ori")) {
			num = 0;
		} else if (kind.equals("select")) {
			num = 1;
		}
		Map<String, Object> data = new HashedMap<String, Object>();
		List<Map<String, Object>> treeData = new ArrayList<>();
		List<DepartmentDTO> departmentList = departmentService.selectAllDepartment();
		if (departmentList != null) {
			String url = servletContext.getRealPath(ConstUtil.EMP_PHOTO_PATH);

			for (int i = 0; i < departmentList.size(); i++) {
				Map<String, Object> tree = new HashedMap<String, Object>();
				List<Map<String, Object>> children = new ArrayList<>();
				DepartmentDTO departmentDto = departmentList.get(i);
				int departmentNo = departmentDto.getDepartmentNo();
				List<EmpDTO> empList = empService.selectByDepartmentNo(departmentNo);

				for (int j = 0; j < empList.size(); j++) {
					Map<String, Object> child = new HashMap<>();
					EmpDTO empDto = empList.get(j);
					if (num == 0) {
						byte[] imageByteArray = itf.transImage(url + empDto.getEmpPhoto());
						child.put("name", empDto.getEmpName());
						child.put("departmentNo", departmentNo);
						child.put("departmentName", empDto.getDepartmentName());
						child.put("empPhoto", imageByteArray);
						child.put("empNo", empDto.getEmpNo());
						child.put("empEmail", empDto.getEmpEmail());
						child.put("empTel", empDto.getEmpTel());
						children.add(child);
					} else if (num == 1) {
						if (empDto.getEmpNo() != empNo) {
							child.put("id", empDto.getEmpNo());
							child.put("label", empDto.getEmpName());
							children.add(child);
						}
					}
				}
				if (num == 0) {
					tree.put("name", departmentDto.getDepartmentName());
					tree.put("children", children);
					treeData.add(tree);
				} else if (num == 1) {
					if (!children.isEmpty()) {
						tree.put("id", departmentDto.getDepartmentName());
						tree.put("label", departmentDto.getDepartmentName());
						tree.put("children", children);
						treeData.add(tree);
					}
				}
			}

			data.put("treeData", treeData);
			data.put("res", ConstUtil.SUCCESS);
		} else {
			data.put("res", ConstUtil.FAIL);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// 사원 사진 가져오기
	@GetMapping("/selectPhoto")
	@ApiOperation(value = "사원 사진 파일 가져오기")
	public ResponseEntity<Map<String, Object>> seletPhoto(@RequestParam int empNo) {
		Map<String, Object> data = new HashedMap<String, Object>();
		EmpDTO empDto = empService.selectByEmpNo(empNo);

		if (empDto == null) {
			data.put("result", ConstUtil.FAIL);
			return new ResponseEntity<>(data, HttpStatus.OK);
		} else {
			String url = servletContext.getRealPath(ConstUtil.EMP_PHOTO_PATH);
			url += empDto.getEmpPhoto();
			byte[] imageByteArray = itf.transImage(url);
			data.put("image", imageByteArray);
			data.put("url", url);
			data.put("result", ConstUtil.SUCCESS);
			return new ResponseEntity<>(data, HttpStatus.OK);
		}

	}

//	//사원등록처리
//	@PostMapping("/empWrite")
//	public String empWrite_post(@ModelAttribute EmpDTO vo, HttpServletRequest request, Model model) {
//		//1
//		logger.info("사원등록처리, 파라미터 vo={}",vo);
//		
//		//2. 사진파일 업로드(단일파일)
//		String fileUrl ="";
//		try {
//			List<Map<String, Object>> list = fileUploadUtil.fileUpload(request, ConstUtil.UPLOAD_EMP_FLAG);
//			for(int i=0; i<list.size();i++ ) {
//				Map<String, Object> map = list.get(i);
//				fileUrl = (String)map.get("fileName");
//			}
//			logger.info("파일 업로드 성공, fileUrl={}",fileUrl);
//		} catch (IllegalStateException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		vo.setEmpPhoto(fileUrl);
//		
//		//3. 등록처리
//		int cnt = empService.insertEmp(vo);
//		logger.info("사원등록정보 vo={}",vo);
//		logger.info("사원등록처리 성공여부, cnt={}",cnt);
//		
//		String msg ="등록실패", url="/emp/empWrite";
//		if(cnt>0) {
//			msg="등록성공";
//			url="/emp/empList";
//		}
//		
//		//4. 모델에 저장 및 리턴
//		model.addAttribute("msg", msg);
//		model.addAttribute("url", url);
//		
//		return "common/message";
//	}

//	/* 사원정보 상세보기 */
//	@GetMapping("/empInfo")
//	public String empinfo(@RequestParam(defaultValue = "0")int empNo, HttpSession session ,Model model) {
//		//1
//		logger.info("사원정보디테일 페이지, 파라미터 empNo={}",empNo);
//		
//		//출퇴근 시간 체크 by 준경
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date now = new Date();
//		logger.info("메인페이지");
//		int empNo2 = Integer.parseInt((String)session.getAttribute("empNo"));
//		
//		EmpDTO empDTO = empService.selectByEmpNo(empNo2);
//		List<ElectronicDTO> elist = eleService.selectUpdateToday(empNo2);
//		List<BookingDTO> bookingList2 = bookingService.selectAllBookingViewByEmpNo(empNo2);
//		List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
//		Date startDate;
//		Date endDate;
//		for(int i=0; i<bookingList2.size(); i++) {
//			try {
//				startDate = sdf.parse(bookingList2.get(i).getBookingStart());
//				endDate = sdf.parse(bookingList2.get(i).getBookingEnd());
//				if(startDate.getTime()<now.getTime() && now.getTime()<endDate.getTime()) {
//					bookingList.add(bookingList2.get(i));
//				}
//			} catch (ParseException e) {
//			}
//		}
//		Date today = new Date();
//		today.setHours(0);
//		today.setMinutes(0);
//		today.setSeconds(0);
//		logger.info("strToday ={}",today);
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("empNo", empNo2);
//		map.put("nowDate", today);
//		int todayScheduleCnt= scheduleService.selectCntScheduleByToday(map);
//		AttendDayDTO atdVO = new AttendDayDTO();
//		atdVO.setEmpNo(empNo2);
//		atdVO.setAttendanceDayRegdate(today);
//		AttendDayDTO attendDayDTO = attendDayService.selectAttendDayByRegdate(atdVO);
//		
//		logger.info("elist={}", elist);
//
//		//안읽은 메일 숫자
//		int mailCount = emailService.totalCountByReadDateMain(empNo2);
//		logger.info("index 안읽은 메일, mailCount={}",mailCount);
//		//파라미터로 넘어온 정보
//		EmpDTO paramEmpVo = empService.selectByEmpNo(empNo);
//		
//		//모델에 저장, 뷰페이지 리턴
//		model.addAttribute("elist", elist);
//		model.addAttribute("empNo", empNo2);
//		model.addAttribute("bookingList", bookingList);
//		model.addAttribute("todayScheduleCnt", todayScheduleCnt);
//		model.addAttribute("attendDayVO", attendDayDTO);
//		model.addAttribute("empVO", empDTO);
//		model.addAttribute("mailCount", mailCount);
//		model.addAttribute("paramEmpVo", paramEmpVo);
//
//		return "emp/empInfo";
//	}
//	

//	/* 사원정보 수정처리 */
//	@PostMapping("/empEdit")
//	public String empEdit_post(@ModelAttribute EmpDTO empVo, @RequestParam String mEmpNo, @RequestParam String loginEmpNo
//			, @RequestParam String mEmpPwd, HttpServletRequest request, @RequestParam String oldFileName, Model model) {
//		logger.info("사원정보 수정처리, vo={}", empVo);
//		
//		//비밀번호 수정 요청자의 사원번호, 비밀번호로 본인확인
//		int result = empService.loginProc(Integer.parseInt(loginEmpNo), mEmpPwd);
//		logger.info("정보확인,result={}",result);
//		
//		String msg="사원정보수정 실패", url="/emp/empEdit?empNo="+empVo.getEmpNo();
//		//본인확인 후 결과처리
//		if(result==EmpService.LOGIN_OK) {
//			//사진파일 수정
//			String fileUrl="";
//			List<Map<String, Object>> list = null;
//			try {
//				list = fileUploadUtil.fileUpload(request, ConstUtil.UPLOAD_EMP_FLAG);
//				
//				for(int i=0; i<list.size();i++ ) {
//					Map<String, Object> map = list.get(i);
//					fileUrl = (String)map.get("fileName");
//				}
//				
//				logger.info("파일 업로드 성공, fileUrl={}",fileUrl);
//			} catch (IllegalStateException | IOException e) {
//				e.printStackTrace();
//			}
//			
//			empVo.setEmpPhoto(fileUrl);
//			
//			//정보 수정 처리
//			int cnt = empService.updateEmp(empVo);
//			logger.info("사원정보수정여부 cnt={}",cnt);
//			if(cnt>0) {
//				msg="사원정보가 수정되었습니다.";
//				url="/emp/empInfo?empNo="+empVo.getEmpNo();
//				
//				//사진파일 수정시 기존파일 삭제
//				if(!list.isEmpty()) {
//	            	if(oldFileName!=null && !oldFileName.isEmpty()) {
//	            		File oldFile = new File(fileUploadUtil.getUploadPath(request,ConstUtil.UPLOAD_EMP_FLAG), oldFileName);
//	            		if(oldFile.exists()) {
//	            			boolean bool = oldFile.delete();
//	            			logger.info("기존파일 삭제여부 : {}", bool);
//	            		}
//	            	}
//	            }//
//			}
//			
//		}else if(result==EmpService.PWD_DISAGREE) {
//			msg="비밀번호가 일치하지 않습니다";
//		}
//		
//		model.addAttribute("msg", msg);
//		model.addAttribute("url", url);
//		
//		return "common/message";
//	}
//	
//	/* 사원리스트 보기 */
//	@RequestMapping("/empList")
//	public String empList(@ModelAttribute SearchVO searchVo , Model model) {
//		logger.info("사원정보리스트 페이지");
//		
//		//2
//		//페이징 처리
//		PaginationInfo pagingInfo = new PaginationInfo();
//		pagingInfo.setCurrentPage(searchVo.getCurrentPage());			 
//		pagingInfo.setBlockSize(ConstUtil.EMP_BLOCK_SIZE); 	 
//		pagingInfo.setRecordCountPerPage(ConstUtil.EMP_RECORD_COUNT);
//		searchVo.setRecordCountPerPage(ConstUtil.EMP_RECORD_COUNT);		 
//		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
//		logger.info("searchVo={}",searchVo);
//		
//		//사원목록선택
//		List<EmpDTO> empList = empService.selectAllEmpList(searchVo);
//		logger.info("사원목록조회, empList.size()={}",empList.size());
//		
//		//검색에 따른 전체데이터 수 얻어오기
//		int totalRecord = empService.selectTotalEmp(searchVo);
//		logger.info("totalRecord={}",totalRecord);
//		
//		//페이징인포에 값 셋팅
//		pagingInfo.setTotalRecord(totalRecord);
//		
//		model.addAttribute("navNo", 8);
//		model.addAttribute("empList", empList);
//		model.addAttribute("pagingInfo", pagingInfo);
//		
//		return "emp/empList";
//		
//	}
//	
//	/* 사원정보삭제 - DB베이스에 정보는 유지 */
//	@PostMapping("/leaveEmp")
//	public String deleteEmp(@RequestParam String modalEmpNo, @RequestParam String modalAdmin, @RequestParam String modalAdminPwd, Model model) {
//		//퇴사하는 사원번호
//		int delEmpNo = Integer.parseInt(modalEmpNo);
//		//퇴사요청하는 관리자번호
//		int adminEmpNo = Integer.parseInt(modalAdmin);
//		
//		logger.info("사원퇴사처리 삭제요청관리자, modalEmpNo={}, adminEmpNo={}",modalEmpNo, adminEmpNo);
//		logger.info("사원퇴사처리 삭제사원번호, modalEmpNo={}",modalEmpNo);
//
//		String msg = "", url= "";
//		//관리자 확인
//		int result = empService.loginProc(adminEmpNo, modalAdminPwd);
//		
//		//결과처리
//		if(result == EmpService.LOGIN_OK) {
//			int cnt = empService.deleteEmp(delEmpNo);
//			msg="퇴사되었습니다.";
//			url="/emp/empList";
//		}else {
//			msg="요청이 실패되었습니다.";
//			url="/emp/empList";
//		}
//		
//		//모델에 저장, 뷰페이지 리턴
//		model.addAttribute("msg", msg);
//		model.addAttribute("url", url);
//		
//		return "common/message";
//	}
//	
//	/* 비밀번호 변경 */
//	@PostMapping("/changePwd")
//	public String changePwd(@ModelAttribute EmpDTO vo, @RequestParam String changeEmpPwd, Model model) {
//		logger.info("비밀번호변경, 변경요청 vo={}",vo);
//		
//		//기존비밀번호 가져오기
//		String dbPwd = empService.selectPwd(vo.getEmpNo());
//		logger.info("비밀번호변경, 변경요청 dbPwd={}, changeEmpPwd={}",dbPwd,changeEmpPwd);
//
//		
//		String msg ="", url="";
//		if(passwordEncoder.matches(vo.getEmpPwd(), dbPwd)) {//기존비밀번호와 일치하면
//			//변경요청
//			vo.setEmpPwd(changeEmpPwd);
//			int cnt = empService.updateTempPwd(vo);
//			
//			if(cnt>0) {
//				msg="비밀번호가 변경되었습니다. 로그아웃됩니다.";
//				url="/login/logout";
//			}else {
//				msg="비밀번호가 변경되지 않았습니다. 다시 시도해주세요";
//				url="/emp/empEdit?empNo="+vo.getEmpPwd();
//			}
//		}else { // 기존비밀번호와 다르면
//			msg="비밀번호가 다릅니다, 다시확인해주세요";
//			url="/emp/empEdit?empNo="+vo.getEmpPwd();
//		}
//		//3
//		model.addAttribute("msg", msg);
//		model.addAttribute("url", url);
//		return "common/message";
//		
//	}
//	
//	/* 이메일 작성 시 사원번호 자동완성 */
//	@ResponseBody
//	@RequestMapping("/searchEmp")
//	public List<EmpDTO> searchEmp(@RequestParam(defaultValue = "0") int searchNo){
//		logger.info("searchNo={}",searchNo);
//		List<EmpDTO> list = empService.selectSearchNum(searchNo);
//		return list;
//	}

}
