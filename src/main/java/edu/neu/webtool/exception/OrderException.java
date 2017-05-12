package edu.neu.webtool.exception;

public class OrderException  extends Exception {

	public OrderException(String message)
	{
		super("OrderException-"+message);
	}
	
	public OrderException(String message, Throwable cause)
	{
		super("OrderException-"+message,cause);
	}
}
