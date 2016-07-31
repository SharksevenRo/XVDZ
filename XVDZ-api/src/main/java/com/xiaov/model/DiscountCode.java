package com.xiaov.model;

import java.util.Date;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * DiscountCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "discount_code", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")

public class DiscountCode extends Page<DiscountCode> implements java.io.Serializable {

	// Fields
	private String id;
	private String  createId;
	private String  salesman;
	private String disCodeNo;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date disCodeTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date disCodeValidTime;
	private Integer disCodeNum;
	private String disCodeRemark;
	private Integer deleteFlag=0;
	@JsonIgnore
	private String towho;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;

	// Constructors

	/** default constructor */
	public DiscountCode() {
	}

	/** minimal constructor */
	public DiscountCode(String id, String disCodeNo,
			Date disCodeTime, Date disCodeValidTime,
			Integer disCodeNum, Integer deleteFlag) {
		this.id = id;
		this.disCodeNo = disCodeNo;
		this.disCodeTime = disCodeTime;
		this.disCodeValidTime = disCodeValidTime;
		this.disCodeNum = disCodeNum;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public DiscountCode(String id, String disCodeNo, Date disCodeTime,
			Date disCodeValidTime, Integer disCodeNum,
			String disCodeRemark, Integer deleteFlag) {
		this.id = id;

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

	@Column(name = "dis_code_no", nullable = true, length = 20)
	public String getDisCodeNo() {
		return this.disCodeNo;
	}

	public void setDisCodeNo(String disCodeNo) {
		this.disCodeNo = disCodeNo;
	}

	@Column(name = "dis_code_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDisCodeTime() {
		return this.disCodeTime;
	}

	public void setDisCodeTime(Date disCodeTime) {
		this.disCodeTime = disCodeTime;
	}

	@Column(name = "dis_code_valid_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
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

	@Column(name = "delete_Flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name="gnrt_u_id")
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	@Column(name="pro_u_id")
	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	@Transient
	public String getTowho() {
		return towho;
	}

	public void setTowho(String towho) {
		this.towho = towho;
	}
	@Column(name = "add_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}