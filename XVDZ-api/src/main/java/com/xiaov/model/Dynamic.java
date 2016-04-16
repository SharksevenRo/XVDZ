package com.xiaov.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;

/**
 * Dynamic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dynamic", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="0")
public class Dynamic implements java.io.Serializable {

	// Fields
	private String dynmcId;
	private UserInfo userInfo;
	private String dynmcContent;
	private Timestamp dynmcTime;
	private Integer dynmcGdCnt;
	private Integer dynmcCmmCnt;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public Dynamic() {
	}

	/** minimal constructor */
	public Dynamic(String dynmcId, String dynmcContent, Timestamp dynmcTime,
			Integer dynmcGdCnt, Integer dynmcCmmCnt, Boolean deleteFlag) {
		this.dynmcId = dynmcId;
		this.dynmcContent = dynmcContent;
		this.dynmcTime = dynmcTime;
		this.dynmcGdCnt = dynmcGdCnt;
		this.dynmcCmmCnt = dynmcCmmCnt;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Dynamic(String dynmcId, UserInfo userInfo, String dynmcContent,
			Timestamp dynmcTime, Integer dynmcGdCnt, Integer dynmcCmmCnt,
			Boolean deleteFlag) {
		this.dynmcId = dynmcId;
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
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getDynmcId() {
		return this.dynmcId;
	}

	public void setDynmcId(String dynmcId) {
		this.dynmcId = dynmcId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "us_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "dynmc_content", nullable = false, length = 200)
	public String getDynmcContent() {
		return this.dynmcContent;
	}

	public void setDynmcContent(String dynmcContent) {
		this.dynmcContent = dynmcContent;
	}

	@Column(name = "dynmc_time", nullable = false, length = 0)
	public Timestamp getDynmcTime() {
		return this.dynmcTime;
	}

	public void setDynmcTime(Timestamp dynmcTime) {
		this.dynmcTime = dynmcTime;
	}

	@Column(name = "dynmc_gd_cnt", nullable = false)
	public Integer getDynmcGdCnt() {
		return this.dynmcGdCnt;
	}

	public void setDynmcGdCnt(Integer dynmcGdCnt) {
		this.dynmcGdCnt = dynmcGdCnt;
	}

	@Column(name = "dynmc_cmm_cnt", nullable = false)
	public Integer getDynmcCmmCnt() {
		return this.dynmcCmmCnt;
	}

	public void setDynmcCmmCnt(Integer dynmcCmmCnt) {
		this.dynmcCmmCnt = dynmcCmmCnt;
	}

	@Column(name = "delete_flag", nullable = false)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}