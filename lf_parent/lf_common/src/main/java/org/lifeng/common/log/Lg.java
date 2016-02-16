package org.lifeng.common.log;

import org.slf4j.LoggerFactory;


public class Lg{
	
	public static void debug(LgType lgType, String message, Object ...param){
		LoggerFactory.getLogger(lgType.toString()).debug(message, param);
	}
	
	public static void info(LgType lgType, String message, Object ...param){
		LoggerFactory.getLogger(lgType.toString()).debug(message, param);
	}
	
	public static void warn(LgType lgType, String message, Object ...param){
		LoggerFactory.getLogger(lgType.toString()).debug(message, param);
	}
	
	public static void error(LgType lgType, String message, Object ...param){
		LoggerFactory.getLogger(lgType.toString()).debug(message, param);
	}
}
