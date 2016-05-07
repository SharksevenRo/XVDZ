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

import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;

/**
 * ReceiveAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "receive_address", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
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
	private String addDefault;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String reAddRemark;
	private Integer deleteFlag=0;

	// Constructors

	/** default constructor */
	public ReceiveAddress() {
	}

	/** minimal constructor */
	public ReceiveAddress(String id, String reAddDet, String reAddTo,
			String reAddTel, String addDefault, Date addTime) {
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
		    String addDefault, Date addTime, Date updateTime,
			Date deleteTime, String reAddRemark, Integer deleteFlag) {
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

	@Column(name = "add_default")
	public String getAddDefault() {
		return this.addDefault;
	}

	public void setAddDefault(String addDefault) {
		this.addDefault = addDefault;
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

	@Column(name = "re_add_remark", length = 200)
	public String getReAddRemark() {
		return this.reAddRemark;
	}

	public void setReAddRemark(String reAddRemark) {
		this.reAddRemark = reAddRemark;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}