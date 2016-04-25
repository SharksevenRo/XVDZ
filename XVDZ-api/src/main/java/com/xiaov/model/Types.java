package com.xiaov.model;

import static javax.persistence.GenerationType.IDENTITY;

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
 * parentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "types", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="1")
public class Types extends Page<Types> implements java.io.Serializable {

	// Fields

	private String id;
	private Types parentType;
	private String typeName;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String typeRemark;
	private Boolean deleteFlage;

	// Constructors

	/** default constructor */
	public Types() {
	}

	/** minimal constructor */
	public Types(String typeName, Date addTime, Boolean deleteFlage) {
		this.typeName = typeName;
		this.addTime = addTime;
		this.deleteFlage = deleteFlage;
	}

	/** full constructor */
	public Types(Types parentType, String typeName, Date addTime,
			Date updateTime, Date deleteTime, String typeRemark,
			Boolean deleteFlage) {
		this.parentType = parentType;
		this.typeName = typeName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.typeRemark = typeRemark;
		this.deleteFlage = deleteFlage;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name = "type_id", unique = true, nullable = true)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_type_id")
	public Types getParentType() {
		return this.parentType;
	}

	public void setParentType(Types parentType) {
		this.parentType = parentType;
	}

	@Column(name = "type_name", nullable = true, length = 33)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "add_time", nullable = true, length = 0)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "update_time", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "delete_time", length = 0)
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "type_remark", length = 200)
	public String getTypeRemark() {
		return this.typeRemark;
	}

	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}

	@Column(name = "delete_flage", nullable = true)
	public Boolean getDeleteFlage() {
		return this.deleteFlage;
	}

	public void setDeleteFlage(Boolean deleteFlage) {
		this.deleteFlage = deleteFlage;
	}

}