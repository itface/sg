package com.sapGarden.global.exception;

public class ObjectToJsonException extends Exception{

	public ObjectToJsonException(){
		super();
	}
	public ObjectToJsonException(String msg){
		super(msg);
	}
	public ObjectToJsonException(String msg,Throwable cause){
		super(msg,cause);
	}
}
