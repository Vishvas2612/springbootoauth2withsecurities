package com.atishay.springboot.common.payload;

/***
 * This class indicates socket response using spring stomp over web socket.
 * 
 * @author vishvas
 *
 */
public class MsgResponse {

	private String result;
	private String senderUserName;

	public MsgResponse(String senderUserName, String result) {
		this.senderUserName = senderUserName;
		this.result = result;

	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

}
