package com.it.groupware.index.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.groupware.attend.model.AttendDTO;
import com.it.groupware.attend.model.AttendService;
import com.it.groupware.attendDay.model.AttendDayDTO;
import com.it.groupware.attendDay.model.AttendDayService;
import com.it.groupware.board.model.BoardDTO;
import com.it.groupware.board.model.BoardService;
import com.it.groupware.booking.model.BookingDTO;
import com.it.groupware.booking.model.BookingService;
import com.it.groupware.department.model.DepartmentDTO;
import com.it.groupware.department.model.DepartmentService;
import com.it.groupware.electronic.model.ElectronicDTO;
import com.it.groupware.electronic.model.ElectronicService;
import com.it.groupware.email.model.EmailDTO;
import com.it.groupware.email.model.EmailService;
import com.it.groupware.emp.model.EmpDTO;
import com.it.groupware.emp.model.EmpService;
import com.it.groupware.index.model.OriVo;
import com.it.groupware.schedule.model.ScheduleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	private final EmpService empService;
	private final DepartmentService departmentService;
	private final ElectronicService eleService;
	private final BookingService bookingService;
	private final ScheduleService scheduleService;
	private final AttendDayService attendDayService;
	private final EmailService emailService;
	private final BoardService boardService;
	
	@RequestMapping("/inc/organizationChart")
	public void organizationChart() {
		logger.info("메인 화면 보여주기");

	}

	@RequestMapping("/index")
	public void test(HttpSession session, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		logger.info("메인페이지");
		int empNo = Integer.parseInt((String)session.getAttribute("empNo"));
		
		List<EmpDTO> allEmp = empService.selectAllEmp();
		EmpDTO empDTO = empService.selectByEmpNo(empNo);
		List<ElectronicDTO> elist = eleService.selectUpdateToday(empNo);
		List<BookingDTO> bookingList2 = bookingService.selectAllBookingViewByEmpNo(empNo);
		List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
		Date startDate;
		Date endDate;
		for(int i=0; i<bookingList2.size(); i++) {
			try {
				startDate = sdf.parse(bookingList2.get(i).getBookingStart());
				endDate = sdf.parse(bookingList2.get(i).getBookingEnd());
				if(startDate.getTime()<now.getTime() && now.getTime()<endDate.getTime()) {
					bookingList.add(bookingList2.get(i));
				}
			} catch (ParseException e) {
			}
		}
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
		logger.info("strToday ={}",today);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("empNo", empNo);
		map.put("nowDate", today);
		int todayScheduleCnt= scheduleService.selectCntScheduleByToday(map);
		AttendDayDTO atdVO = new AttendDayDTO();
		atdVO.setEmpNo(empNo);
		atdVO.setAttendanceDayRegdate(today);
		AttendDayDTO attendDayDTO = attendDayService.selectAttendDayByRegdate(atdVO);
		
		logger.info("elist={}", elist);

		//안읽은 메일 선택
		int mailCount = emailService.totalCountByReadDateMain(empNo);
		List<EmailDTO> emailVo = emailService.selectNotRead(Integer.toString(empNo));
		
		logger.info("index 안읽은 메일, emailVo={}",emailVo);
		logger.info("empVO={}", empDTO);
		
		//공지사항
		List<BoardDTO> notice = boardService.selectBoardMain(1);
		logger.info("공지사항 리스트 목록, notice.size()={}", notice.size());
		
		model.addAttribute("elist", elist);
		model.addAttribute("empNo", empNo);
		model.addAttribute("allEmp", allEmp);
		model.addAttribute("bookingList", bookingList);
		model.addAttribute("todayScheduleCnt", todayScheduleCnt);
		model.addAttribute("attendDayVO", attendDayDTO);
		model.addAttribute("empVO", empDTO);
		model.addAttribute("mailCount", mailCount);
		model.addAttribute("emailVo", emailVo);
		model.addAttribute("notice", notice);
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		logger.info("관리자페이지");
		int adminCount = empService.selectTotalAdmin();
		int empCount = empService.selectTotalEmpCount();
		model.addAttribute("navNo", 8);
		model.addAttribute("adminCount", adminCount);
		model.addAttribute("empCount", empCount);
		
		return "adminMain";
	}
	
	@ResponseBody
	@GetMapping("/chartList")
	public HashMap<String, Object> chartList(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> ageEmp = empService.selectAge();
		List<HashMap<String, Object>> ageEmpGroup = empService.selectAgeGrop();
		List<HashMap<String, Object>> joinLeave = empService.selectJoinLeave();
		List<HashMap<String, Object>> comJL = new ArrayList<HashMap<String,Object>>();
		if(joinLeave==null || joinLeave.isEmpty()) {
			HashMap<String, Object> jlmap = new HashMap<String, Object>();
			jlmap.put("name", "정보없음");
			jlmap.put("y", 100);
			comJL.add(jlmap);
		}else {
			HashMap<String, Object> jlmap = new HashMap<String, Object>();
			HashMap<String, Object> jlmap2 = new HashMap<String, Object>();
			int jcnt = (int)joinLeave.get(0).get("reg");
			int lcnt = (int)joinLeave.get(0).get("res");
			int jper = (int)((float)jcnt/(jcnt+lcnt)*10000)/100;
			int lper = (int)((float)lcnt/(jcnt+lcnt)*10000)/100;
			logger.info("jper={}",jper);
			logger.info("lper={}",lper);
			jlmap.put("name", "입사");
			jlmap.put("y", jper);
			jlmap2.put("name", "퇴사");
			jlmap2.put("y", lper);
			comJL.add(jlmap);
			comJL.add(jlmap2);
		}
		
		List<HashMap<String, Object>> ageDetailList = new ArrayList<HashMap<String,Object>>();
		
		
		for(int i=0; i<ageEmpGroup.size();i++) {
			HashMap<String, Object> ageDetailMap = new HashMap<String, Object>();
			List<HashMap<String, Object>> ageList = new ArrayList<HashMap<String, Object>>();
			ageDetailMap.put("name", ageEmpGroup.get(i).get("name"));
			ageDetailMap.put("id", ageEmpGroup.get(i).get("name"));
			
			for(int j=0; j<ageEmp.size();j++) {
				if(ageEmpGroup.get(i).get("name")==ageEmp.get(j).get("ageGroup")) {
					HashMap<String, Object> ageMap = new HashMap<String, Object>();				
					ageMap.put("name", ageEmp.get(j).get("age"));
					ageMap.put("y", ageEmp.get(j).get("per"));
					ageList.add(ageMap);
				}
			}
			
			ageDetailMap.put("data", ageList);
			ageDetailList.add(ageDetailMap);
		}
		
		map.put("ageDetailList", ageDetailList);
		map.put("ageEmpGroup", ageEmpGroup);
		map.put("ageEmp", ageEmp);
		map.put("comJL", comJL);
		return map;
	}

	
	@ResponseBody
	@RequestMapping("/inc/list")
	public List<OriVo> selectOri() {
		logger.info("트리뷰 보여주기");

		List<OriVo> olist = new ArrayList<>();
		List<EmpDTO> elist = empService.selectAllEmpForEle(); //사용자
		List<DepartmentDTO> dlist = departmentService.selectAllDepartment(); //부서

		for (int i = 0; i < elist.size(); i++) {

			EmpDTO empVo = elist.get(i);
			OriVo orivo = new OriVo();
			int depNo = empVo.getDepartmentNo();
			if (depNo == 0) {
				orivo.setParent("#");
			} else {
				orivo.setParent(Integer.toString(depNo));
			}
			orivo.setId(Integer.toString(empVo.getEmpNo()));
			orivo.setText(empVo.getEmpName());
			orivo.setUserEmail(empVo.getEmpEmail());
			orivo.setUserTel(empVo.getEmpTel());
			orivo.setUserImg(empVo.getEmpPhoto());
			olist.add(orivo);
		}

		for (int i = 0; i < dlist.size(); i++) {

			DepartmentDTO departVo = dlist.get(i);
			OriVo orivo = new OriVo();
			orivo.setId(Integer.toString(departVo.getDepartmentNo()));
			orivo.setText(departVo.getDepartmentName());
			orivo.setParent("#");

			olist.add(orivo);
		}
		
		logger.info("트리뷰 결과 olist={}", olist);
		
		return olist;

	}
	
	@ResponseBody
	@RequestMapping("/inc/getInfomation")
	public EmpDTO getInfomation(@RequestParam int EmpNo) {
		logger.info("aside modal 에뿌려줄  유저 정보 가져오기 파라미터 EmpNo={} ", EmpNo);
		EmpDTO empVo = empService.selectByEmpNo(EmpNo);
		logger.info("empVo={}", empVo);
		return empVo;
	}
	
	
	@RequestMapping("/mainWeather")
	public void weather() {
		logger.info("날씨보여주기");
		
	}

}
