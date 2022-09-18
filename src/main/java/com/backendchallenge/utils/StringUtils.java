package com.backendchallenge.utils;

import java.util.regex.Pattern;

public class StringUtils {
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." +
			"[a-zA-Z0-9_+&*-]+)*@" +
			"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
			"A-Z]{2,7}$";

	private StringUtils() {
	}

	public static String obfuscateEmailAddress(String emailAddress) {
		return emailAddress.replaceAll("(?<=.{3}).(?=[^@]*?.@)", "*");
	}

	public static boolean isNoneBlank(String email) {
		return org.apache.commons.lang3.StringUtils.isNoneBlank(email);
	}

	public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		if (email == null)
			return false;
		return pattern.matcher(email).matches();
	}
}
