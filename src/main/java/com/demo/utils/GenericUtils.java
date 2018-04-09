package com.demo.utils;


import org.springframework.security.core.context.SecurityContextHolder;

public class GenericUtils {

	public static String getLoggedInUser() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
