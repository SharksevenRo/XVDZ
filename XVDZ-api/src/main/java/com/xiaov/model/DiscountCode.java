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
 * DiscountCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "discount_code", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="1")
public class DiscountCode extends Page<DiscountCode> implements java.io.Serializable {

	// Fields
	private String id;
	private UserInfo userInfoByGnrtUId;
	private UserInfo userInfoByProUId;
	private String disCodeNo;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date disCodeTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date disCodeValidTime;
	private Integer disCodeNum;
	private String disCodeRemark;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public DiscountCode() {
	}

	/** minimal constructor */
	public DiscountCode(String id, String disCodeNo,
			Date disCodeTime, Date disCodeValidTime,
			Integer disCodeNum, Boolean deleteFlag) {
		this.id = id;
		this.disCodeNo = disCodeNo;
		this.disCodeTime = disCodeTime;
		this.disCodeValidTime = disCodeValidTime;
		this.disCodeNum = disCodeNum;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public DiscountCode(String id, UserInfo userInfoByGnrtUId,
			UserInfo userInfoByProUId, String disCodeNo, Date disCodeTime,
			Date disCodeValidTime, Integer disCodeNum,
			String disCodeRemark, Boolean deleteFlag) {
		this.id = id;
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
	@Column(name = "dis_code_id", unique = true, nullable = true, length = 33)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Column(name = "dis_code_no", nullable = true, length = 20)
	public String getDisCodeNo() {
		return this.disCodeNo;
	}

	public void setDisCodeNo(String disCodeNo) {
		this.disCodeNo = disCodeNo;
	}

	@Column(name = "dis_code_time", nullable = true, length = 0)
	public Date getDisCodeTime() {
		return this.disCodeTime;
	}

	public void setDisCodeTime(Date disCodeTime) {
		this.disCodeTime = disCodeTime;
	}

	@Column(name = "dis_code_valid_time", nullable = true, length = 0)
	public Date getDisCodeValidTime() {
		return this.disCodeValidTime;
	}

	public void setDisCodeValidTime(Date disCodeValidTime) {
		this.disCodeValidTime = disCodeValidTime;
	}

	@Column(name = "dis_code_num", nullable = true)
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

	@Column(name = "delete_flag", nullable = true)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}