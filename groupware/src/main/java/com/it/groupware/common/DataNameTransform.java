package com.it.groupware.common;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DataNameTransform {
	ELECTRONIC_DATE("electronicDate"),
	ELECTRONIC_EMERGENCY_FLAG("electronicEmergencyFlag"),
	STYLE_NAME("styleName"),
	ELECTRONIC_TITLE("electronicTitle"),
	EMP_NAME("empName");
	
	private final String label;
	private static final Map<String, DataNameTransform> BY_LABEL
	= Stream.of(values()).collect(Collectors.toMap(DataNameTransform::label,e->e));
	
	DataNameTransform(String label) {
		this.label = label;
	}
	
	public static DataNameTransform valueOfLabel(String label) {
		return BY_LABEL.get(label);
	}
	
	public String label() {
		return label;
	}
	
}
