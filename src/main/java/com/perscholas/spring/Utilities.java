package com.perscholas.spring;

public class Utilities {

	public static String formatPhoneNumber(String phone) {
		return String.valueOf(phone).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
	}
	
}
