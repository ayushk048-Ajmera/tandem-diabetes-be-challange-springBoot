package com.backendchallenge.utils;

public class StringUtils {
	private StringUtils() {}
	public static String obfuscateEmailAddress(String emailAddress) {
		return emailAddress.replaceAll("(?<=.{3}).(?=[^@]*?.@)", "*");
	}
}
