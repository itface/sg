package com.sapGarden.global.exception;

public class CustomException extends Exception{

	public CustomException(){
		super();
	}
	public CustomException(String msg){
		super(msg);
	}
	public CustomException(String msg,Throwable cause){
		super(msg,cause);
	}

	public CustomException(Throwable cause){
		super(cause);
	}

}
