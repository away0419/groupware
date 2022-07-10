package com.it.groupware.breakTheme.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper 
public interface BreakThemeDAO {
	List<BreakThemeDTO> selectAll(); // 휴가 종류 전체 조회
}
