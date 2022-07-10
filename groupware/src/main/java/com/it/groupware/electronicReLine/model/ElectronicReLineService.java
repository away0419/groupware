package com.it.groupware.electronicReLine.model;

import java.util.List;

import com.it.groupware.electronic.model.ElectronicDTO;

public interface ElectronicReLineService {

	int insertReLine(ElectronicReLineDTO dto); // 수신 라인 추가
	int selectForCheckExist(int electronicNo, int empNo); //수신자 맞는지 확인

	List<ElectronicReLineDTO> selectByElectronicNo(int ElectronicNo);

	int AcceptUpdateReLine(ElectronicDTO dto);

	int deleteReLine(ElectronicReLineDTO dto);

}
