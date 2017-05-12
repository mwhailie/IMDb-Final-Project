package edu.neu.webtool.exception;

public class ScheduleException  extends Exception{

	public ScheduleException(String message)
	{
		super("ScheduleException-"+message);
	}
	
	public ScheduleException(String message, Throwable cause)
	{
		super("ScheduleException-"+message,cause);
	}

}
