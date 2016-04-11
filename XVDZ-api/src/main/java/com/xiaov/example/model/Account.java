package com.xiaov.example.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account", catalog = "xvdz")
public class Account implements java.io.Serializable {

	// Fields

	private String actId;
	private UserInfo userInfo;
	private Double actMm;
	private Double actTtEn;
	private Double actBlc;
	private Double actFm;
	private Integer actState;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private String remark;
	private Boolean deleteFlag;
	private Set<BankCard> bankCards = new HashSet<BankCard>(0);

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String actId, Double actMm, Double actTtEn, Double actBlc,
			Double actFm, Timestamp addTime, Boolean deleteFlag) {
		this.actId = actId;
		this.actMm = actMm;
		this.actTtEn = actTtEn;
		this.actBlc = actBlc;
		this.actFm = actFm;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Account(String actId, UserInfo userInfo, Double actMm,
			Double actTtEn, Double actBlc, Double actFm, Integer actState,
			Timestamp addTime, Timestamp updateTime, Timestamp deleteTime,
			String remark, Boolean deleteFlag, Set<BankCard> bankCards) {
		this.actId = actId;
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
		this.bankCards = bankCards;
	}

	// Property accessors
	@Id
	@Column(name = "act_id", unique = true, nullable = false, length = 20)
	public String getActId() {
		return this.actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "us_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "act_mm", nullable = false, precision = 22, scale = 0)
	public Double getActMm() {
		return this.actMm;
	}

	public void setActMm(Double actMm) {
		this.actMm = actMm;
	}

	@Column(name = "act_tt_en", nullable = false, precision = 22, scale = 0)
	public Double getActTtEn() {
		return this.actTtEn;
	}

	public void setActTtEn(Double actTtEn) {
		this.actTtEn = actTtEn;
	}

	@Column(name = "act_blc", nullable = false, precision = 22, scale = 0)
	public Double getActBlc() {
		return this.actBlc;
	}

	public void setActBlc(Double actBlc) {
		this.actBlc = actBlc;
	}

	@Column(name = "act_fm", nullable = false, precision = 22, scale = 0)
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

	@Column(name = "add_time", nullable = false, length = 0)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Column(name = "update_time", length = 0)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "delete_time", length = 0)
	public Timestamp getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "deleteFlag", nullable = false)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<BankCard> getBankCards() {
		return this.bankCards;
	}

	public void setBankCards(Set<BankCard> bankCards) {
		this.bankCards = bankCards;
	}

}