package com.xiaov.example.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Messages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "messages", catalog = "xvdz")
public class Messages implements java.io.Serializable {

	// Fields

	private String msgId;
	private UserInfo userInfoBySendId;
	private UserInfo userInfoByReceiveId;
	private Integer typeId;
	private String msgContent;
	private Timestamp msgTime;
	private Boolean msgReadState;

	// Constructors

	/** default constructor */
	public Messages() {
	}

	/** minimal constructor */
	public Messages(String msgId, String msgContent, Timestamp msgTime,
			Boolean msgReadState) {
		this.msgId = msgId;
		this.msgContent = msgContent;
		this.msgTime = msgTime;
		this.msgReadState = msgReadState;
	}

	/** full constructor */
	public Messages(String msgId, UserInfo userInfoBySendId,
			UserInfo userInfoByReceiveId, Integer typeId, String msgContent,
			Timestamp msgTime, Boolean msgReadState) {
		this.msgId = msgId;
		this.userInfoBySendId = userInfoBySendId;
		this.userInfoByReceiveId = userInfoByReceiveId;
		this.typeId = typeId;
		this.msgContent = msgContent;
		this.msgTime = msgTime;
		this.msgReadState = msgReadState;
	}

	// Property accessors
	@Id
	@Column(name = "msg_id", unique = true, nullable = false, length = 20)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "send_id")
	public UserInfo getUserInfoBySendId() {
		return this.userInfoBySendId;
	}

	public void setUserInfoBySendId(UserInfo userInfoBySendId) {
		this.userInfoBySendId = userInfoBySendId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receive_id")
	public UserInfo getUserInfoByReceiveId() {
		return this.userInfoByReceiveId;
	}

	public void setUserInfoByReceiveId(UserInfo userInfoByReceiveId) {
		this.userInfoByReceiveId = userInfoByReceiveId;
	}

	@Column(name = "type_id")
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "msg_content", nullable = false, length = 200)
	public String getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name = "msg_time", nullable = false, length = 0)
	public Timestamp getMsgTime() {
		return this.msgTime;
	}

	public void setMsgTime(Timestamp msgTime) {
		this.msgTime = msgTime;
	}

	@Column(name = "msg_read_state", nullable = false)
	public Boolean getMsgReadState() {
		return this.msgReadState;
	}

	public void setMsgReadState(Boolean msgReadState) {
		this.msgReadState = msgReadState;
	}

}