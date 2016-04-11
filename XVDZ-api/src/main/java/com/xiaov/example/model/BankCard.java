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
 * BankCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bank_card", catalog = "xvdz")
public class BankCard implements java.io.Serializable {

	// Fields

	private String bkCdId;
	private DbTypes dbTypes;
	private Account account;
	private String bkCdNo;
	private String bcForName;
	private String bcBdTel;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private String bcRemark;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public BankCard() {
	}

	/** minimal constructor */
	public BankCard(String bkCdId, DbTypes dbTypes, String bkCdNo,
			String bcForName, String bcBdTel, Timestamp addTime,
			Boolean deleteFlag) {
		this.bkCdId = bkCdId;
		this.dbTypes = dbTypes;
		this.bkCdNo = bkCdNo;
		this.bcForName = bcForName;
		this.bcBdTel = bcBdTel;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public BankCard(String bkCdId, DbTypes dbTypes, Account account,
			String bkCdNo, String bcForName, String bcBdTel, Timestamp addTime,
			Timestamp updateTime, Timestamp deleteTime, String bcRemark,
			Boolean deleteFlag) {
		this.bkCdId = bkCdId;
		this.dbTypes = dbTypes;
		this.account = account;
		this.bkCdNo = bkCdNo;
		this.bcForName = bcForName;
		this.bcBdTel = bcBdTel;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.bcRemark = bcRemark;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "bk_cd_id", unique = true, nullable = false, length = 20)
	public String getBkCdId() {
		return this.bkCdId;
	}

	public void setBkCdId(String bkCdId) {
		this.bkCdId = bkCdId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	public DbTypes getDbTypes() {
		return this.dbTypes;
	}

	public void setDbTypes(DbTypes dbTypes) {
		this.dbTypes = dbTypes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "act_id")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "bk_cd_no", nullable = false, length = 20)
	public String getBkCdNo() {
		return this.bkCdNo;
	}

	public void setBkCdNo(String bkCdNo) {
		this.bkCdNo = bkCdNo;
	}

	@Column(name = "bc_for_name", nullable = false, length = 20)
	public String getBcForName() {
		return this.bcForName;
	}

	public void setBcForName(String bcForName) {
		this.bcForName = bcForName;
	}

	@Column(name = "bc_bd_tel", nullable = false, length = 12)
	public String getBcBdTel() {
		return this.bcBdTel;
	}

	public void setBcBdTel(String bcBdTel) {
		this.bcBdTel = bcBdTel;
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

	@Column(name = "bc_remark", length = 200)
	public String getBcRemark() {
		return this.bcRemark;
	}

	public void setBcRemark(String bcRemark) {
		this.bcRemark = bcRemark;
	}

	@Column(name = "delete_flag", nullable = false)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}