package com.it.groupware.electronicAppStamp.model;

import java.io.File;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.it.groupware.common.ConstUtil;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicAppStampServiceImpl implements ElectronicAppStampService{
	private final ElectronicAppStampDAO stampDao;
	private final ServletContext servletContext;
	private static final Logger logger = LoggerFactory.getLogger(ElectronicAppStampServiceImpl.class);
	
	//사인 등록
	@Override
	public int registerStamp(ElectronicAppStampDTO elAppStampDto) {
		
		int empNo = elAppStampDto.getEmpNo();
		int result = 0;
		ElectronicAppStampDTO selectDto = stampDao.selectByEmpNo(empNo);
		if(selectDto == null) {
			result = stampDao.insertStamp(elAppStampDto);
		}else {
			result = stampDao.updateStamp(elAppStampDto);
			String str = servletContext.getRealPath(ConstUtil.ELECTRONIC_STAMPUP_PATH);
			File file = new File(str + selectDto.getStampName());
			file.delete();
		}
		
		return result;
	}

	//해당 사원의 사인 불러오기
	@Override
	public ElectronicAppStampDTO selectStamp(int empNo) {
		ElectronicAppStampDTO selectDto = stampDao.selectByEmpNo(empNo);
		return selectDto;
	}

}
