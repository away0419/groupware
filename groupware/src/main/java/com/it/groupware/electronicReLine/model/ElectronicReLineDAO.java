package com.it.groupware.electronicReLine.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.electronic.model.ElectronicDTO;

@Mapper
public interface ElectronicReLineDAO {
	int insertReLine(ElectronicReLineDTO dto); // 수신 라인 추가
	int selectForCheckExist(int electronicNo, int empNo); //현재 문서에서 해당 사용자가 수신자 맞는지 확인

	List<ElectronicReLineDTO> selectByElectronicNo(int ElectronicNo);
	int AcceptUpdateReLine(ElectronicDTO dto);
	int deleteReLine(ElectronicReLineDTO dto);
}
