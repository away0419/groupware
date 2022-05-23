package com.it.groupware.position.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PositionDAO {
	List<PositionDTO> selectAllPosition();
}
