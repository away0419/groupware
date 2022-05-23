package com.it.groupware.electronicDocSty.model;

import java.util.List;

public interface ElectronicDocStyService {
	List<ElectronicDocStyDTO> selectAll();
	ElectronicDocStyDTO selectByStyleNo(String styleNo);
}
