package com.it.groupware.boTarget.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoTargetDAO {
	List<BoTargetDTO> selectAllBoTarget();
	int insertBoTarget(BoTargetDTO vo);
	int updateBorTarget(BoTargetDTO vo);
	int deleteBorTarget(int bookingTargetNo);
}
