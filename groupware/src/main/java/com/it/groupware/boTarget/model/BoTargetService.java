package com.it.groupware.boTarget.model;

import java.util.List;

public interface BoTargetService {
	List<BoTargetDTO> selectAllBoTarget();
	int insertBoTarget(BoTargetDTO vo);
	int updateBorTarget(BoTargetDTO vo);
	int deleteBorTarget(int bookingTargetNo);
}
