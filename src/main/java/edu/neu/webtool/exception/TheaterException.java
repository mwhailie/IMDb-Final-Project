package edu.neu.webtool.exception;

public class TheaterException extends Exception{

	public TheaterException(String message)
	{
		super("TheaterException-"+message);
	}
	
	public TheaterException(String message, Throwable cause)
	{
		super("TheaterException-"+message,cause);
	}
}
