package com.xiaov.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * Messages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "messages", catalog = "xvdz")
public class Messages extends Page<Messages> implements java.io.Serializable {

	// Fields
	private String id;
	private UserInfo userInfoBySendId;
	private UserInfo userInfoByReceiveId;
	private Integer typeId;
	private String msgContent;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date msgTime;
	private Boolean msgReadState;

	// Constructors

	/** default constructor */
	public Messages() {
	}

	/** minimal constructor */
	public Messages(String id, String msgContent, Date msgTime,
			Boolean msgReadState) {
		this.id = id;
		this.msgContent = msgContent;
		this.msgTime = msgTime;
		this.msgReadState = msgReadState;
	}

	/** full constructor */
	public Messages(String id, UserInfo userInfoBySendId,
			UserInfo userInfoByReceiveId, Integer typeId, String msgContent,
			Date msgTime, Boolean msgReadState) {
		this.id = id;
		this.userInfoBySendId = userInfoBySendId;
		this.userInfoByReceiveId = userInfoByReceiveId;
		this.typeId = typeId;
		this.msgContent = msgContent;
		this.msgTime = msgTime;
		this.msgReadState = msgReadState;
	}

	// Property accessors
	@Id
	@Column(name = "msg_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Column(name = "msg_content", nullable = true, length = 200)
	public String getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name = "msg_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getMsgTime() {
		return this.msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	@Column(name = "msg_read_state", nullable = true)
	public Boolean getMsgReadState() {
		return this.msgReadState;
	}

	public void setMsgReadState(Boolean msgReadState) {
		this.msgReadState = msgReadState;
	}

}