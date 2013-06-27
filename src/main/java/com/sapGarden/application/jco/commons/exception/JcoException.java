package com.sapGarden.application.jco.commons.exception;

public class JcoException extends Exception{

	public JcoException(){
		super();
	}
	public JcoException(String msg){
		super(msg);
	}
	public JcoException(String msg,Throwable cause){
		super(msg,cause);
	}
	public JcoException(Throwable cause){
		super(cause);
	}

}
