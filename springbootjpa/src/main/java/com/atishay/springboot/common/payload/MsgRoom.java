package com.atishay.springboot.common.payload;

/***
 * This class indicates socket request using spring stomp over web socket.
 * 
 * @author vishvas
 *
 */
public class MsgRoom {

	private String senderUserName;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

}
