package com.it.groupware.electronicReLine.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.electronic.model.ElectronicDTO;

@Mapper
public interface ElectronicReLineDAO {
	int insertReLine(ElectronicReLineDTO vo);
	List<ElectronicReLineDTO> selectByElectronicNo(int ElectronicNo);
	int AcceptUpdateReLine(ElectronicDTO vo);
	int deleteReLine(ElectronicReLineDTO vo);
	int selectForCheckExist(ElectronicReLineDTO vo);
}
