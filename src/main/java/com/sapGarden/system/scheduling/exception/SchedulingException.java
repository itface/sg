package com.sapGarden.system.scheduling.exception;

public class SchedulingException extends Exception{

	public SchedulingException(){
		super();
	}
	public SchedulingException(String msg){
		super(msg);
	}
	public SchedulingException(String msg, Throwable cause){
		super(msg,cause);
	}
}
