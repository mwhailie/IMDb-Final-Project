package edu.neu.webtool.exception;

public class GenreException extends Exception{

	public GenreException(String message)
	{
		super("GenreException-"+message);
	}
	
	public GenreException(String message, Throwable cause)
	{
		super("GenreException-"+message,cause);
	}
	
}
