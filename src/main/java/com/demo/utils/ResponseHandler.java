package com.demo.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean error,String message, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("timestamp", new Date());
			map.put("status", status.value());
			map.put("isSuccess", error);
			map.put("message", message);
			map.put("data", responseObj);

			return new ResponseEntity<Object>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map,status);
		}
	}
	
	public static Map<String, Object> generateServiceResponse(boolean error,String message, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("isSuccess", error);
			map.put("message", message);
			map.put("data", responseObj);
			return map;
		} catch (Exception e) {
			map.clear();
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return map;
		}
	}
	public static ResponseEntity<Object> generateLoginResponse(HttpStatus status, boolean error,String message, Object responseObj,Object token) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("status", status.value());
			map.put("isSuccess", error);
			map.put("message", message);
			map.put("data", responseObj);
			map.put("token", token);
			return new ResponseEntity<Object>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map,status);
		}
	}
	
	public static ResponseEntity<Object> userNotFoundResponse(HttpStatus status, boolean error,String message, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("status", status.value());
			map.put("isSuccess", error);
			map.put("message", message);
			map.put("data", new HashMap<String,Object>());

			return new ResponseEntity<Object>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map,status);
		}
	}
	
	public static ResponseEntity<Object> invalidResponse(HttpStatus status, boolean error,String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("status", status.value());
			map.put("isSuccess", error);
			map.put("message", message);
			return new ResponseEntity<Object>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map,status);
		}
	}
	public static ResponseEntity<Object> verifyResponse(HttpStatus status, boolean error,String message,Object json) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("data", json);
			map.put("status", status.value());
			map.put("isSuccess", error);
			map.put("message", message);
			return new ResponseEntity<Object>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map,status);
		}
	}	
	
	public static ResponseEntity<Object> responseinvalidPasswordObject(HttpStatus status, boolean error,String message,Map<String,Object> json) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map=json;
			map.put("data", new HashMap<>());
			map.put("status", status.value());
			map.put("isSuccess", error);
			map.put("message", message);
			return new ResponseEntity<Object>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map,status);
		}
	}	
	
}
