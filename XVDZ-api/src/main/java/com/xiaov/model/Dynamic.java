package com.xiaov.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;

/**
 * Dynamic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dynamic", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="1")
public class Dynamic extends Page<Dynamic> implements java.io.Serializable {

	// Fields
	private String id;
	private UserInfo userInfo;
	private String dynmcContent;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dynmcTime;
	private Integer dynmcGdCnt;
	private Integer dynmcCmmCnt;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public Dynamic() {
	}

	/** minimal constructor */
	public Dynamic(String id, String dynmcContent, Date dynmcTime,
			Integer dynmcGdCnt, Integer dynmcCmmCnt, Boolean deleteFlag) {
		this.id = id;
		this.dynmcContent = dynmcContent;
		this.dynmcTime = dynmcTime;
		this.dynmcGdCnt = dynmcGdCnt;
		this.dynmcCmmCnt = dynmcCmmCnt;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Dynamic(String id, UserInfo userInfo, String dynmcContent,
			Date dynmcTime, Integer dynmcGdCnt, Integer dynmcCmmCnt,
			Boolean deleteFlag) {
		this.id = id;
		this.userInfo = userInfo;
		this.dynmcContent = dynmcContent;
		this.dynmcTime = dynmcTime;
		this.dynmcGdCnt = dynmcGdCnt;
		this.dynmcCmmCnt = dynmcCmmCnt;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid") 
	@Column(name = "dynmc_id", unique = true, nullable = true, length = 33)
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "us_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "dynmc_content", nullable = true, length = 200)
	public String getDynmcContent() {
		return this.dynmcContent;
	}

	public void setDynmcContent(String dynmcContent) {
		this.dynmcContent = dynmcContent;
	}

	@Column(name = "dynmc_time", nullable = true, length = 0)
	public Date getDynmcTime() {
		return this.dynmcTime;
	}

	public void setDynmcTime(Date dynmcTime) {
		this.dynmcTime = dynmcTime;
	}

	@Column(name = "dynmc_gd_cnt", nullable = true)
	public Integer getDynmcGdCnt() {
		return this.dynmcGdCnt;
	}

	public void setDynmcGdCnt(Integer dynmcGdCnt) {
		this.dynmcGdCnt = dynmcGdCnt;
	}

	@Column(name = "dynmc_cmm_cnt", nullable = true)
	public Integer getDynmcCmmCnt() {
		return this.dynmcCmmCnt;
	}

	public void setDynmcCmmCnt(Integer dynmcCmmCnt) {
		this.dynmcCmmCnt = dynmcCmmCnt;
	}

	@Column(name = "delete_flag", nullable = true)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}