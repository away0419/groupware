package com.it.groupware.department.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentDAO {
	List<DepartmentDTO> selectAllDepartment();
}
