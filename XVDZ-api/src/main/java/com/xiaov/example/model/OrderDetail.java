package com.xiaov.example.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OrderDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_detail", catalog = "xvdz")
public class OrderDetail implements java.io.Serializable {

	// Fields

	private String orDtId;
	private Integer pdtId;
	private String orDtNo;
	private Double orDtPrc;
	private Integer orDtMount;
	private Double orDtDsct;
	private Double orDtItmeTotal;
	private Double orDtRlTotal;
	private String orDtRemark;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private Boolean deleteFlag;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	// Constructors

	/** default constructor */
	public OrderDetail() {
	}

	/** minimal constructor */
	public OrderDetail(String orDtId, String orDtNo, Double orDtPrc,
			Integer orDtMount, Double orDtItmeTotal, Double orDtRlTotal,
			Timestamp addTime, Boolean deleteFlag) {
		this.orDtId = orDtId;
		this.orDtNo = orDtNo;
		this.orDtPrc = orDtPrc;
		this.orDtMount = orDtMount;
		this.orDtItmeTotal = orDtItmeTotal;
		this.orDtRlTotal = orDtRlTotal;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public OrderDetail(String orDtId, Integer pdtId, String orDtNo,
			Double orDtPrc, Integer orDtMount, Double orDtDsct,
			Double orDtItmeTotal, Double orDtRlTotal, String orDtRemark,
			Timestamp addTime, Timestamp updateTime, Timestamp deleteTime,
			Boolean deleteFlag, Set<Orders> orderses) {
		this.orDtId = orDtId;
		this.pdtId = pdtId;
		this.orDtNo = orDtNo;
		this.orDtPrc = orDtPrc;
		this.orDtMount = orDtMount;
		this.orDtDsct = orDtDsct;
		this.orDtItmeTotal = orDtItmeTotal;
		this.orDtRlTotal = orDtRlTotal;
		this.orDtRemark = orDtRemark;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.deleteFlag = deleteFlag;
		this.orderses = orderses;
	}

	// Property accessors
	@Id
	@Column(name = "or_dt_id", unique = true, nullable = false, length = 20)
	public String getOrDtId() {
		return this.orDtId;
	}

	public void setOrDtId(String orDtId) {
		this.orDtId = orDtId;
	}

	@Column(name = "pdt_id")
	public Integer getPdtId() {
		return this.pdtId;
	}

	public void setPdtId(Integer pdtId) {
		this.pdtId = pdtId;
	}

	@Column(name = "or_dt_no", nullable = false, length = 20)
	public String getOrDtNo() {
		return this.orDtNo;
	}

	public void setOrDtNo(String orDtNo) {
		this.orDtNo = orDtNo;
	}

	@Column(name = "or_dt_prc", nullable = false, precision = 22, scale = 0)
	public Double getOrDtPrc() {
		return this.orDtPrc;
	}

	public void setOrDtPrc(Double orDtPrc) {
		this.orDtPrc = orDtPrc;
	}

	@Column(name = "or_dt_mount", nullable = false)
	public Integer getOrDtMount() {
		return this.orDtMount;
	}

	public void setOrDtMount(Integer orDtMount) {
		this.orDtMount = orDtMount;
	}

	@Column(name = "or_dt_dsct", precision = 22, scale = 0)
	public Double getOrDtDsct() {
		return this.orDtDsct;
	}

	public void setOrDtDsct(Double orDtDsct) {
		this.orDtDsct = orDtDsct;
	}

	@Column(name = "or_dt_itme_total", nullable = false, precision = 22, scale = 0)
	public Double getOrDtItmeTotal() {
		return this.orDtItmeTotal;
	}

	public void setOrDtItmeTotal(Double orDtItmeTotal) {
		this.orDtItmeTotal = orDtItmeTotal;
	}

	@Column(name = "or_dt_rl_total", nullable = false, precision = 22, scale = 0)
	public Double getOrDtRlTotal() {
		return this.orDtRlTotal;
	}

	public void setOrDtRlTotal(Double orDtRlTotal) {
		this.orDtRlTotal = orDtRlTotal;
	}

	@Column(name = "or_dt_remark", length = 200)
	public String getOrDtRemark() {
		return this.orDtRemark;
	}

	public void setOrDtRemark(String orDtRemark) {
		this.orDtRemark = orDtRemark;
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

	@Column(name = "delete_flag", nullable = false)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderDetail")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}