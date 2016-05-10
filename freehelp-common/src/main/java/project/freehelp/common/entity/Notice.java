package project.freehelp.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "NOTICE")
public class Notice {
	@Id
	private String id;
	private String sender;// 发送
	private String senderID;// 发送
	private String receiver;// 接收
	private String receiverID;// 接收
	private String msg;// 消息
	private int status;// 状态

	public String getId() {
		return id;
	}

	public Notice setId(String id) {
		this.id = id;
		return this;
	}

	public String getSender() {
		return sender;
	}

	public Notice setSender(String sender) {
		this.sender = sender;
		return this;
	}

	public String getSenderID() {
		return senderID;
	}

	public Notice setSenderID(String senderID) {
		this.senderID = senderID;
		return this;
	}

	public String getReceiver() {
		return receiver;
	}

	public Notice setReceiver(String receiver) {
		this.receiver = receiver;
		return this;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public Notice setReceiverID(String receiverID) {
		this.receiverID = receiverID;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Notice setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public Notice setStatus(int status) {
		this.status = status;
		return this;
	}

	public Notice(String id) {
		super();
		this.id = id;
	}

	public Notice() {
		super();
	}

}
