package com.it.groupware.electronicReLine.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ElectronicReLineDTO {
	
	private int receiveLineNo;
	private int electronicNo;
	private int empNo;
	private String receiveLineFlag;
}
