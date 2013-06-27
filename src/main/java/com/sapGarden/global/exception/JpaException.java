package com.sapGarden.global.exception;

public class JpaException extends Exception{

	public JpaException(){
		super();
	}
	public JpaException(String msg){
		super(msg);
	}
	public JpaException(String msg,Throwable cause){
		super(msg,cause);
	}

	public JpaException(Throwable cause){
		super(cause);
	}
}
