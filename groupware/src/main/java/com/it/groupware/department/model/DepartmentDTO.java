package com.it.groupware.department.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentDTO {
	@ApiModelProperty(value="부서 번호", example="1")
	private int departmentNo;
	@ApiModelProperty(value="부서 이름", example="전략기획팀")
	private String departmentName;
}
