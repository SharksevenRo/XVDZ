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
 * Orders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orders", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="0")
public class Orders extends Page<Orders> implements java.io.Serializable {

	// Fields
	private String id;
	private OrderDetail orderDetail;
	private Types dbTypes;
	private DiscountCoupan discountCoupan;
	private String ueId;
	private String orNo;
	private Double orTotal;
	private Double orDiscount;
	private Double orRealCost;
	private Integer orState;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private String orRemark;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(String id, String orNo, Double orTotal, Integer orState,
			Timestamp addTime, Boolean deleteFlag) {
		this.id = id;
		this.orNo = orNo;
		this.orTotal = orTotal;
		this.orState = orState;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Orders(String id, OrderDetail orderDetail, Types dbTypes,
			DiscountCoupan discountCoupan, String ueId, String orNo,
			Double orTotal, Double orDiscount, Double orRealCost,
			Integer orState, Timestamp addTime, Timestamp updateTime,
			Timestamp deleteTime, String orRemark, Boolean deleteFlag) {
		this.id = id;
		this.orderDetail = orderDetail;
		this.dbTypes = dbTypes;
		this.discountCoupan = discountCoupan;
		this.ueId = ueId;
		this.orNo = orNo;
		this.orTotal = orTotal;
		this.orDiscount = orDiscount;
		this.orRealCost = orRealCost;
		this.orState = orState;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.orRemark = orRemark;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "or_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "or_dt_id")
	public OrderDetail getOrderDetail() {
		return this.orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_type_id")
	public Types getDbTypes() {
		return this.dbTypes;
	}

	public void setDbTypes(Types dbTypes) {
		this.dbTypes = dbTypes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dis_cou_id")
	public DiscountCoupan getDiscountCoupan() {
		return this.discountCoupan;
	}

	public void setDiscountCoupan(DiscountCoupan discountCoupan) {
		this.discountCoupan = discountCoupan;
	}

	@Column(name = "ue_id", length = 20)
	public String getUeId() {
		return this.ueId;
	}

	public void setUeId(String ueId) {
		this.ueId = ueId;
	}

	@Column(name = "or_no", nullable = true, length = 20)
	public String getOrNo() {
		return this.orNo;
	}

	public void setOrNo(String orNo) {
		this.orNo = orNo;
	}

	@Column(name = "or_total", nullable = true, precision = 22, scale = 0)
	public Double getOrTotal() {
		return this.orTotal;
	}

	public void setOrTotal(Double orTotal) {
		this.orTotal = orTotal;
	}

	@Column(name = "or_discount", precision = 22, scale = 0)
	public Double getOrDiscount() {
		return this.orDiscount;
	}

	public void setOrDiscount(Double orDiscount) {
		this.orDiscount = orDiscount;
	}

	@Column(name = "or_real_cost", precision = 22, scale = 0)
	public Double getOrRealCost() {
		return this.orRealCost;
	}

	public void setOrRealCost(Double orRealCost) {
		this.orRealCost = orRealCost;
	}

	@Column(name = "or_state", nullable = true)
	public Integer getOrState() {
		return this.orState;
	}

	public void setOrState(Integer orState) {
		this.orState = orState;
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

	@Column(name = "or_remark", length = 200)
	public String getOrRemark() {
		return this.orRemark;
	}

	public void setOrRemark(String orRemark) {
		this.orRemark = orRemark;
	}

	@Column(name = "delete_flag", nullable = true)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}