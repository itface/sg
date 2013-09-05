package com.sapGarden.application.commons.runtime.scheduling.exception;

public class SchedulingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3117922037301711818L;
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
