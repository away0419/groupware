package com.it.groupware.email.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailListVO {
	private List<EmailDTO> selectedEmail;
}
