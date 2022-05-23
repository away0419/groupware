package com.it.groupware.electronicReLine.model;

import java.util.List;

import com.it.groupware.electronic.model.ElectronicDTO;

public interface ElectronicReLineService {

	int insertReLine(ElectronicReLineDTO vo);
	List<ElectronicReLineDTO> selectByElectronicNo(int ElectronicNo);
	int AcceptUpdateReLine(ElectronicDTO vo);
	int deleteReLine(ElectronicReLineDTO vo);
	int selectForCheckExist(ElectronicReLineDTO vo);
}
