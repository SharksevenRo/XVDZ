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

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * BankCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bank_card", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="1")
public class BankCard extends Page<BankCard> implements java.io.Serializable {

	// Fields
	private String id;
	private Types dbTypes;
	private Account account;
	private String bkCdNo;
	private String bcForName;
	private String bcBdTel;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String bcRemark;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public BankCard() {
	}

	/** minimal constructor */
	public BankCard(String id, Types dbTypes, String bkCdNo,
			String bcForName, String bcBdTel, Date addTime,
			Boolean deleteFlag) {
		this.id = id;
		this.dbTypes = dbTypes;
		this.bkCdNo = bkCdNo;
		this.bcForName = bcForName;
		this.bcBdTel = bcBdTel;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public BankCard(String id, Types dbTypes, Account account,
			String bkCdNo, String bcForName, String bcBdTel, Date addTime,
			Date updateTime, Date deleteTime, String bcRemark,
			Boolean deleteFlag) {
		this.id = id;
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
	@Column(name = "bk_cd_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = true)
	public Types getDbTypes() {
		return this.dbTypes;
	}

	public void setDbTypes(Types dbTypes) {
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

	@Column(name = "bk_cd_no", nullable = true, length = 20)
	public String getBkCdNo() {
		return this.bkCdNo;
	}

	public void setBkCdNo(String bkCdNo) {
		this.bkCdNo = bkCdNo;
	}

	@Column(name = "bc_for_name", nullable = true, length = 20)
	public String getBcForName() {
		return this.bcForName;
	}

	public void setBcForName(String bcForName) {
		this.bcForName = bcForName;
	}

	@Column(name = "bc_bd_tel", nullable = true, length = 12)
	public String getBcBdTel() {
		return this.bcBdTel;
	}

	public void setBcBdTel(String bcBdTel) {
		this.bcBdTel = bcBdTel;
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

	@Column(name = "bc_remark", length = 200)
	public String getBcRemark() {
		return this.bcRemark;
	}

	public void setBcRemark(String bcRemark) {
		this.bcRemark = bcRemark;
	}

	@Column(name = "delete_flag", nullable = true)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}