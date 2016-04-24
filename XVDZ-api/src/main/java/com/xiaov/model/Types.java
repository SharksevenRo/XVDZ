package com.xiaov.model;

import static javax.persistence.GenerationType.IDENTITY;

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
 * parentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "types", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="0")
public class Types extends Page<Types> implements java.io.Serializable {

	// Fields

	private String typeId;
	private Types parentType;
	private String typeName;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private String typeRemark;
	private Boolean deleteFlage;

	// Constructors

	/** default constructor */
	public Types() {
	}

	/** minimal constructor */
	public Types(String typeName, Timestamp addTime, Boolean deleteFlage) {
		this.typeName = typeName;
		this.addTime = addTime;
		this.deleteFlage = deleteFlage;
	}

	/** full constructor */
	public Types(Types parentType, String typeName, Timestamp addTime,
			Timestamp updateTime, Timestamp deleteTime, String typeRemark,
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
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
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