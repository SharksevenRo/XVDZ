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

import com.xiaov.orm.core.Page;
import org.hibernate.annotations.GenericGenerator;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;

/**
 * ReceiveAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "receive_address", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="0")
public class ReceiveAddress extends Page<ReceiveAddress> implements java.io.Serializable {

	// Fields
	private String id;
	private UserInfo userInfo;
	private String reAddName;
	private String reAddPro;
	private String reAddCity;
	private String reAddArea;
	private String reAddDet;
	private String reAddTo;
	private String reAddTel;
	private Boolean addDefault;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private String reAddRemark;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public ReceiveAddress() {
	}

	/** minimal constructor */
	public ReceiveAddress(String id, String reAddDet, String reAddTo,
			String reAddTel, Boolean addDefault, Timestamp addTime) {
		this.id = id;
		this.reAddDet = reAddDet;
		this.reAddTo = reAddTo;
		this.reAddTel = reAddTel;
		this.addDefault = addDefault;
		this.addTime = addTime;
	}

	/** full constructor */
	public ReceiveAddress(String id, UserInfo userInfo, String reAddName,
			String reAddPro, String reAddCity, String reAddArea,
			String reAddDet, String reAddTo, String reAddTel,
			Boolean addDefault, Timestamp addTime, Timestamp updateTime,
			Timestamp deleteTime, String reAddRemark, Boolean deleteFlag) {
		this.id = id;
		this.userInfo = userInfo;
		this.reAddName = reAddName;
		this.reAddPro = reAddPro;
		this.reAddCity = reAddCity;
		this.reAddArea = reAddArea;
		this.reAddDet = reAddDet;
		this.reAddTo = reAddTo;
		this.reAddTel = reAddTel;
		this.addDefault = addDefault;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.reAddRemark = reAddRemark;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "re_add_id", unique = true, nullable = true, length = 33)
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

	@Column(name = "re_add_name", length = 200)
	public String getReAddName() {
		return this.reAddName;
	}

	public void setReAddName(String reAddName) {
		this.reAddName = reAddName;
	}

	@Column(name = "re_add_pro", length = 50)
	public String getReAddPro() {
		return this.reAddPro;
	}

	public void setReAddPro(String reAddPro) {
		this.reAddPro = reAddPro;
	}

	@Column(name = "re_add_city", length = 30)
	public String getReAddCity() {
		return this.reAddCity;
	}

	public void setReAddCity(String reAddCity) {
		this.reAddCity = reAddCity;
	}

	@Column(name = "re_add_area", length = 30)
	public String getReAddArea() {
		return this.reAddArea;
	}

	public void setReAddArea(String reAddArea) {
		this.reAddArea = reAddArea;
	}

	@Column(name = "re_add_det", nullable = true, length = 200)
	public String getReAddDet() {
		return this.reAddDet;
	}

	public void setReAddDet(String reAddDet) {
		this.reAddDet = reAddDet;
	}

	@Column(name = "re_add_to", nullable = true, length = 20)
	public String getReAddTo() {
		return this.reAddTo;
	}

	public void setReAddTo(String reAddTo) {
		this.reAddTo = reAddTo;
	}

	@Column(name = "re_add_tel", nullable = true, length = 12)
	public String getReAddTel() {
		return this.reAddTel;
	}

	public void setReAddTel(String reAddTel) {
		this.reAddTel = reAddTel;
	}

	@Column(name = "add_default", nullable = true)
	public Boolean getAddDefault() {
		return this.addDefault;
	}

	public void setAddDefault(Boolean addDefault) {
		this.addDefault = addDefault;
	}

	@Column(name = "add_time", nullable = true, length = 0)
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

	@Column(name = "re_add_remark", length = 200)
	public String getReAddRemark() {
		return this.reAddRemark;
	}

	public void setReAddRemark(String reAddRemark) {
		this.reAddRemark = reAddRemark;
	}

	@Column(name = "delete_flag")
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}