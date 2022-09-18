package com.backendchallenge.utils;

import java.util.regex.Pattern;

public class StringUtils {
	private  static  final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
			"[a-zA-Z0-9_+&*-]+)*@" +
			"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
			"A-Z]{2,7}$";

	private StringUtils() {}
	public static String obfuscateEmailAddress(String emailAddress) {
		return emailAddress.replaceAll("(?<=.{3}).(?=[^@]*?.@)", "*");
	}

	public static boolean isNoneBlank(String email){
		return org.apache.commons.lang3.StringUtils.isNoneBlank(email);
	}

	public static boolean isValidEmail(String email)
	{
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}
}
