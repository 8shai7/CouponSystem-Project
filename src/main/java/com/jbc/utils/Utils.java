package com.jbc.utils;

import java.util.Date;

public class Utils {
	//this method is just to set the Date right 
	public static Date addDate(int y,int m,int d) {
		return new Date(y-1900,m-1,d+1);
	}
	public static Date addDate(Date date) {
		return new Date(date.getYear()-1900,date.getMonth()-1,date.getDay()+1);
	}
}
