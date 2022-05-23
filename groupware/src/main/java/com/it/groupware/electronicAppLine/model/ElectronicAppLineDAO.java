package com.it.groupware.electronicAppLine.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.electronic.model.ElectronicDTO;

@Mapper
public interface ElectronicAppLineDAO {
	
	int insertAppLine(ElectronicAppLineDTO vo);
	List<ElectronicAppLineDTO> selectByElectronicNo(int ElectronicNo);
	int AcceptUpdateAppLine(ElectronicDTO vo);
	int deleteAppLine(ElectronicAppLineDTO vo);
	int selectForCheckExist(ElectronicAppLineDTO vo);
	ElectronicAppLineDTO selectAppLineCheck(ElectronicDTO vo);
}
