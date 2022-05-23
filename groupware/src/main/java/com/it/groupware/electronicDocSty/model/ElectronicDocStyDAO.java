package com.it.groupware.electronicDocSty.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElectronicDocStyDAO {
	List<ElectronicDocStyDTO> selectAll();
	ElectronicDocStyDTO selectByStyleNo(String styleNo);
}
