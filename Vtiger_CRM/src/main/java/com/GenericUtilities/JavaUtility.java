package com.GenericUtilities;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	/**
	 * This method will return a random number for every execution
	 * @return
	 */
	public int getRandomNo()
	{
		Random ran = new Random();
		int random = ran.nextInt();
		return random;
	}
	
	/**
	 * This method will generate the current system date
	 * @return
	 */
	public String getSystemDate()
	{
		Date dt = new Date();
		String date = dt.toString();
		return date;
	}

	/**
	 * This method will generate the current system date in given specified format
	 * @return
	 */
	public String getSystemDateInFormat()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date dt = new Date();
		String sysDateInFormat = dateFormat.format(dt);
		return sysDateInFormat;
	}
	
	/**
	 * This method will generate the current system date and Time
	 * @return
	 */
	public String getSystemDateAndTime()
	{
		String timeStamp = LocalDateTime.now().toString().replace(':', '-');
		return timeStamp;
	}
}
