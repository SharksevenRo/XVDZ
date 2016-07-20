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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
public class Account extends Page<Account> implements java.io.Serializable {

	// Fields

	private String id;
	private UserInfo userInfo;
	private Double actMm;
	private Double actTtEn;
	private Double actBlc;
	private Double actFm;
	private Double present_amount;
	private Integer actState;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String remark;
	private Integer deleteFlag=0;

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String id, Double actMm, Double actTtEn, Double actBlc,
			Double actFm, Date addTime, Integer deleteFlag) {
		this.id = id;
		this.actMm = actMm;
		this.actTtEn = actTtEn;
		this.actBlc = actBlc;
		this.actFm = actFm;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Account(String id, UserInfo userInfo, Double actMm,
			Double actTtEn, Double actBlc, Double actFm, Integer actState,
			Date addTime, Date updateTime, Date deleteTime,
			String remark, Integer deleteFlag) {
		this.id = id;
		this.userInfo = userInfo;
		this.actMm = actMm;
		this.actTtEn = actTtEn;
		this.actBlc = actBlc;
		this.actFm = actFm;
		this.actState = actState;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.remark = remark;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "act_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
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

	@Column(name = "act_mm", nullable = true, precision = 22, scale = 0)
	public Double getActMm() {
		return this.actMm;
	}

	public void setActMm(Double actMm) {
		this.actMm = actMm;
	}

	@Column(name = "act_tt_en", nullable = true, precision = 22, scale = 0)
	public Double getActTtEn() {
		return this.actTtEn;
	}

	public void setActTtEn(Double actTtEn) {
		this.actTtEn = actTtEn;
	}

	@Column(name = "act_blc", nullable = true, precision = 22, scale = 0)
	public Double getActBlc() {
		return this.actBlc;
	}

	public void setActBlc(Double actBlc) {
		this.actBlc = actBlc;
	}

	@Column(name = "act_fm", nullable = true, precision = 22, scale = 0)
	public Double getActFm() {
		return this.actFm;
	}

	public void setActFm(Double actFm) {
		this.actFm = actFm;
	}

	@Column(name = "act_state")
	public Integer getActState() {
		return this.actState;
	}

	public void setActState(Integer actState) {
		this.actState = actState;
	}

	@Column(name = "add_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "update_time", length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "delete_time", length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "deleteFlag", nullable = true)
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	@Column(name = "present_amount")
	public Double getPresent_amount() {
		return present_amount;
	}
	public void setPresent_amount(Double present_amount) {
		this.present_amount = present_amount;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}