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
import com.xiaov.orm.core.Page;

/**
 * DiscountCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "discount_code", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="0")
public class DiscountCode extends Page<DiscountCode> implements java.io.Serializable {

	// Fields
	private String disCodeId;
	private UserInfo userInfoByGnrtUId;
	private UserInfo userInfoByProUId;
	private String disCodeNo;
	private Timestamp disCodeTime;
	private Timestamp disCodeValidTime;
	private Integer disCodeNum;
	private String disCodeRemark;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public DiscountCode() {
	}

	/** minimal constructor */
	public DiscountCode(String disCodeId, String disCodeNo,
			Timestamp disCodeTime, Timestamp disCodeValidTime,
			Integer disCodeNum, Boolean deleteFlag) {
		this.disCodeId = disCodeId;
		this.disCodeNo = disCodeNo;
		this.disCodeTime = disCodeTime;
		this.disCodeValidTime = disCodeValidTime;
		this.disCodeNum = disCodeNum;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public DiscountCode(String disCodeId, UserInfo userInfoByGnrtUId,
			UserInfo userInfoByProUId, String disCodeNo, Timestamp disCodeTime,
			Timestamp disCodeValidTime, Integer disCodeNum,
			String disCodeRemark, Boolean deleteFlag) {
		this.disCodeId = disCodeId;
		this.userInfoByGnrtUId = userInfoByGnrtUId;
		this.userInfoByProUId = userInfoByProUId;
		this.disCodeNo = disCodeNo;
		this.disCodeTime = disCodeTime;
		this.disCodeValidTime = disCodeValidTime;
		this.disCodeNum = disCodeNum;
		this.disCodeRemark = disCodeRemark;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Id
	@Column(name = "dis_code_id", unique = true, nullable = false, length = 33)
	public String getDisCodeId() {
		return this.disCodeId;
	}

	public void setDisCodeId(String disCodeId) {
		this.disCodeId = disCodeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gnrt_u_id")
	public UserInfo getUserInfoByGnrtUId() {
		return this.userInfoByGnrtUId;
	}

	public void setUserInfoByGnrtUId(UserInfo userInfoByGnrtUId) {
		this.userInfoByGnrtUId = userInfoByGnrtUId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_u_id")
	public UserInfo getUserInfoByProUId() {
		return this.userInfoByProUId;
	}

	public void setUserInfoByProUId(UserInfo userInfoByProUId) {
		this.userInfoByProUId = userInfoByProUId;
	}

	@Column(name = "dis_code_no", nullable = false, length = 20)
	public String getDisCodeNo() {
		return this.disCodeNo;
	}

	public void setDisCodeNo(String disCodeNo) {
		this.disCodeNo = disCodeNo;
	}

	@Column(name = "dis_code_time", nullable = false, length = 0)
	public Timestamp getDisCodeTime() {
		return this.disCodeTime;
	}

	public void setDisCodeTime(Timestamp disCodeTime) {
		this.disCodeTime = disCodeTime;
	}

	@Column(name = "dis_code_valid_time", nullable = false, length = 0)
	public Timestamp getDisCodeValidTime() {
		return this.disCodeValidTime;
	}

	public void setDisCodeValidTime(Timestamp disCodeValidTime) {
		this.disCodeValidTime = disCodeValidTime;
	}

	@Column(name = "dis_code_num", nullable = false)
	public Integer getDisCodeNum() {
		return this.disCodeNum;
	}

	public void setDisCodeNum(Integer disCodeNum) {
		this.disCodeNum = disCodeNum;
	}

	@Column(name = "dis_code_remark", length = 200)
	public String getDisCodeRemark() {
		return this.disCodeRemark;
	}

	public void setDisCodeRemark(String disCodeRemark) {
		this.disCodeRemark = disCodeRemark;
	}

	@Column(name = "delete_flag", nullable = false)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}