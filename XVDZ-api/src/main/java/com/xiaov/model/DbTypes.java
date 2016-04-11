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

/**
 * DbTypes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "db_types", catalog = "xvdz")
public class DbTypes implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private DbTypes dbTypes;
	private String typeName;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private String typeRemark;
	private Boolean deleteFlage;

	// Constructors

	/** default constructor */
	public DbTypes() {
	}

	/** minimal constructor */
	public DbTypes(String typeName, Timestamp addTime, Boolean deleteFlage) {
		this.typeName = typeName;
		this.addTime = addTime;
		this.deleteFlage = deleteFlage;
	}

	/** full constructor */
	public DbTypes(DbTypes dbTypes, String typeName, Timestamp addTime,
			Timestamp updateTime, Timestamp deleteTime, String typeRemark,
			Boolean deleteFlage) {
		this.dbTypes = dbTypes;
		this.typeName = typeName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.typeRemark = typeRemark;
		this.deleteFlage = deleteFlage;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "type_id", unique = true, nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "father_type_id")
	public DbTypes getDbTypes() {
		return this.dbTypes;
	}

	public void setDbTypes(DbTypes dbTypes) {
		this.dbTypes = dbTypes;
	}

	@Column(name = "type_name", nullable = false, length = 20)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	@Column(name = "type_remark", length = 200)
	public String getTypeRemark() {
		return this.typeRemark;
	}

	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}

	@Column(name = "delete_flage", nullable = false)
	public Boolean getDeleteFlage() {
		return this.deleteFlage;
	}

	public void setDeleteFlage(Boolean deleteFlage) {
		this.deleteFlage = deleteFlage;
	}

}