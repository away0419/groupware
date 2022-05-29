package com.it.groupware.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.it.groupware.emp.model.EmpDTO;
import com.it.groupware.emp.model.EmpService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private final EmpService empService;
	
	/* 비밀번호 찾기 */
	@RequestMapping("login/findPwd")
	public String sendRandomPwd(@ModelAttribute EmpDTO empVo, Model model) {
		//1 파라미터 셋팅
		int chkEmpNo= empVo.getEmpNo();
		String checkEmail = empVo.getEmpEmail();
		logger.info("임시비밀번호 파라미터, chkEmpNo={},modalEmpEmail={}",chkEmpNo,empVo.getEmpEmail());
		
		//2
		//사원번호로 email 체크
		String dbEmail = empService.checkEmail(chkEmpNo);
		String msg="",url="/";
		if(checkEmail.equals(dbEmail)) {
			//입력된 이메일과 데이터베이스의 이메일이 동일하면 임시비밀번호 생성
			String tempPwd =  RandomPwd.getRandomPwd(8);
			// vo에 저장
			empVo.setEmpPwd(tempPwd);
			logger.info("생성된 tempPwd={}",tempPwd);
			logger.info("empVo={}",empVo);
			
			// 등록된 메일로 메일전송
			empService.sendEmail(empVo);
			
			// 데이터베이스에 업데이트
			int cnt = empService.updateTempPwd(empVo);
			logger.info("임시비밀번호성공, cnt={}",cnt);
			
			// 성공 or 실패안내
			if(cnt>0) {
				msg="임시비밀번호가 발급되었습니다";
			}else {
				msg="임시비밀번호 발급실패";
			}
		}else { //입력된 이메일과 데이터베이스의 이메일이 동일하지 않으면 불일치 안내 
			msg="이메일이 일치하지 않습니다.";
		}
		
		//3 모델 저장, 뷰페이지 리턴
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}

}
