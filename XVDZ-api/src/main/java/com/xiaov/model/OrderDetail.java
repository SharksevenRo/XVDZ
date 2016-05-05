package com.xiaov.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * OrderDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_detail", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
public class OrderDetail extends Page<OrderDetail> implements java.io.Serializable {

	// Fields
	private String id;
	private Integer pdtId;
	private String orDtNo;
	private Double orDtPrc;
	private Integer orDtMount;
	private Double orDtDsct;
	private Double orDtItmeTotal;
	private Double orDtRlTotal;
	private String orDtRemark;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private Integer deleteFlag=0;

	// Constructors

	/** default constructor */
	public OrderDetail() {
	}

	/** minimal constructor */
	public OrderDetail(String id, String orDtNo, Double orDtPrc,
			Integer orDtMount, Double orDtItmeTotal, Double orDtRlTotal,
			Date addTime, Integer deleteFlag) {
		this.id = id;
		this.orDtNo = orDtNo;
		this.orDtPrc = orDtPrc;
		this.orDtMount = orDtMount;
		this.orDtItmeTotal = orDtItmeTotal;
		this.orDtRlTotal = orDtRlTotal;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public OrderDetail(String id, Integer pdtId, String orDtNo,
			Double orDtPrc, Integer orDtMount, Double orDtDsct,
			Double orDtItmeTotal, Double orDtRlTotal, String orDtRemark,
			Date addTime, Date updateTime, Date deleteTime,
			Integer deleteFlag) {
		this.id = id;
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
	}

	// Property accessors
	@Id
	@Column(name = "or_dt_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "pdt_id")
	public Integer getPdtId() {
		return this.pdtId;
	}

	public void setPdtId(Integer pdtId) {
		this.pdtId = pdtId;
	}

	@Column(name = "or_dt_no", nullable = true, length = 20)
	public String getOrDtNo() {
		return this.orDtNo;
	}

	public void setOrDtNo(String orDtNo) {
		this.orDtNo = orDtNo;
	}

	@Column(name = "or_dt_prc", nullable = true, precision = 22, scale = 0)
	public Double getOrDtPrc() {
		return this.orDtPrc;
	}

	public void setOrDtPrc(Double orDtPrc) {
		this.orDtPrc = orDtPrc;
	}

	@Column(name = "or_dt_mount", nullable = true)
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

	@Column(name = "or_dt_itme_total", nullable = true, precision = 22, scale = 0)
	public Double getOrDtItmeTotal() {
		return this.orDtItmeTotal;
	}

	public void setOrDtItmeTotal(Double orDtItmeTotal) {
		this.orDtItmeTotal = orDtItmeTotal;
	}

	@Column(name = "or_dt_rl_total", nullable = true, precision = 22, scale = 0)
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

	@Column(name = "delete_Flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}