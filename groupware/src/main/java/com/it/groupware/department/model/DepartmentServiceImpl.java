package com.it.groupware.department.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
	private final DepartmentDAO departmentDao;

	//모든 부서 목록
	@Override
	public List<DepartmentDTO> selectAllDepartment() {
		return departmentDao.selectAllDepartment();
	}
	
}
