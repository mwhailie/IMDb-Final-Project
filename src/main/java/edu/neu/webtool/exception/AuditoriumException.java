package edu.neu.webtool.exception;

public class AuditoriumException extends Exception{

	public AuditoriumException(String message)
	{
		super("AuditoriumException-"+message);
	}
	
	public AuditoriumException(String message, Throwable cause)
	{
		super("AuditoriumException-"+message,cause);
	}
}
