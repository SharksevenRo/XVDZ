package com.xiaov.model;

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

import org.hibernate.annotations.GenericGenerator;

/**
 * DiscountCoupan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "discount_coupan", catalog = "xvdz")
public class DiscountCoupan implements java.io.Serializable {

	// Fields
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String disCouId;
	private UserInfo userInfo;
	private String disCouNo;
	private Double disCouPrice;
	private Timestamp disCouTime;
	private Timestamp disCouValidTime;
	private Integer disCouState;
	private String disCouRemark;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public DiscountCoupan() {
	}

	/** minimal constructor */
	public DiscountCoupan(String disCouId, String disCouNo, Double disCouPrice,
			Timestamp disCouTime, Timestamp disCouValidTime,
			Integer disCouState, Boolean deleteFlag) {
		this.disCouId = disCouId;
		this.disCouNo = disCouNo;
		this.disCouPrice = disCouPrice;
		this.disCouTime = disCouTime;
		this.disCouValidTime = disCouValidTime;
		this.disCouState = disCouState;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public DiscountCoupan(String disCouId, UserInfo userInfo, String disCouNo,
			Double disCouPrice, Timestamp disCouTime,
			Timestamp disCouValidTime, Integer disCouState,
			String disCouRemark, Boolean deleteFlag) {
		this.disCouId = disCouId;
		this.userInfo = userInfo;
		this.disCouNo = disCouNo;
		this.disCouPrice = disCouPrice;
		this.disCouTime = disCouTime;
		this.disCouValidTime = disCouValidTime;
		this.disCouState = disCouState;
		this.disCouRemark = disCouRemark;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "dis_cou_id", unique = true, nullable = false, length = 20)
	public String getDisCouId() {
		return this.disCouId;
	}

	public void setDisCouId(String disCouId) {
		this.disCouId = disCouId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "us_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "dis_cou_no", nullable = false, length = 20)
	public String getDisCouNo() {
		return this.disCouNo;
	}

	public void setDisCouNo(String disCouNo) {
		this.disCouNo = disCouNo;
	}

	@Column(name = "dis_cou_price", nullable = false, precision = 22, scale = 0)
	public Double getDisCouPrice() {
		return this.disCouPrice;
	}

	public void setDisCouPrice(Double disCouPrice) {
		this.disCouPrice = disCouPrice;
	}

	@Column(name = "dis_cou_time", nullable = false, length = 0)
	public Timestamp getDisCouTime() {
		return this.disCouTime;
	}

	public void setDisCouTime(Timestamp disCouTime) {
		this.disCouTime = disCouTime;
	}

	@Column(name = "dis_cou_valid_time", nullable = false, length = 0)
	public Timestamp getDisCouValidTime() {
		return this.disCouValidTime;
	}

	public void setDisCouValidTime(Timestamp disCouValidTime) {
		this.disCouValidTime = disCouValidTime;
	}

	@Column(name = "dis_cou_state", nullable = false)
	public Integer getDisCouState() {
		return this.disCouState;
	}

	public void setDisCouState(Integer disCouState) {
		this.disCouState = disCouState;
	}

	@Column(name = "dis_cou_remark", length = 200)
	public String getDisCouRemark() {
		return this.disCouRemark;
	}

	public void setDisCouRemark(String disCouRemark) {
		this.disCouRemark = disCouRemark;
	}

	@Column(name = "delete_flag", nullable = false)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}