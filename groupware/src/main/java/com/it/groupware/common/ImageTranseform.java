package com.it.groupware.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class ImageTranseform {
	public byte[] transImage(String url) {
		byte[] imageByteArray=null;
		try {
			InputStream imageIS = new FileInputStream(url);
			imageByteArray = IOUtils.toByteArray(imageIS);
			imageIS.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageByteArray;
	}
}
