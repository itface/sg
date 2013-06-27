package com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail;

import java.io.Serializable;

public class Export_return implements Serializable{

	private static final long serialVersionUID = 8961653795826720715L;

	private String type;
	private String code;
	private String message;
	private String log_no;
	private String log_msg_no;
	private String message_v1;
	private String message_v2;
	private String message_v3;
	private String message_v4;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLog_no() {
		return log_no;
	}
	public void setLog_no(String log_no) {
		this.log_no = log_no;
	}
	public String getMessage_v1() {
		return message_v1;
	}
	public void setMessage_v1(String message_v1) {
		this.message_v1 = message_v1;
	}
	public String getLog_msg_no() {
		return log_msg_no;
	}
	public void setLog_msg_no(String log_msg_no) {
		this.log_msg_no = log_msg_no;
	}
	public String getMessage_v2() {
		return message_v2;
	}
	public void setMessage_v2(String message_v2) {
		this.message_v2 = message_v2;
	}
	public String getMessage_v3() {
		return message_v3;
	}
	public void setMessage_v3(String message_v3) {
		this.message_v3 = message_v3;
	}
	public String getMessage_v4() {
		return message_v4;
	}
	public void setMessage_v4(String message_v4) {
		this.message_v4 = message_v4;
	}
	
}
