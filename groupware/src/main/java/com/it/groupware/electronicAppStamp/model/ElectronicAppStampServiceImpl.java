package com.it.groupware.electronicAppStamp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicAppStampServiceImpl implements ElectronicAppStampService{
	private final ElectronicAppStampDAO stampDao;
	private static final Logger logger = LoggerFactory.getLogger(ElectronicAppStampServiceImpl.class);


	@Override
	public int insertStamp(ElectronicAppStampDTO stampVo) {
		
		int empNo = stampVo.getEmpNo();
		int result = 0;
		ElectronicAppStampDTO vo = stampDao.selectByEmpNo(empNo);
		if(vo == null) {
			result = stampDao.insertStamp(stampVo);
		}else {
			result = 0;
		}
		
		return result;
	}

}
