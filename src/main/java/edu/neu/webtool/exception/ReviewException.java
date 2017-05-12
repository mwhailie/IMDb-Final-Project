package edu.neu.webtool.exception;

public class ReviewException extends Exception {

	public ReviewException(String message)
	{
		super("ReviewException-"+message);
	}
	
	public ReviewException(String message, Throwable cause)
	{
		super("ReviewException-"+message,cause);
	}
}
