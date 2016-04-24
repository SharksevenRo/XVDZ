package com.xiaov.model;

import java.util.Date;

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
 * Material entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "material", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.B,value="0")
public class Material extends Page<Material> implements java.io.Serializable {

	// Fields
	private String id;
	private Material material;
	private Types dbTypes;
	private String materialNo;
	private String meterialName;
	private String meterialRemark;
	private Date addTime;
	private Date updateTime;
	private Date deleteTime;
	private Boolean deleteFlag;

	// Constructors

	/** default constructor */
	public Material() {
	}

	/** minimal constructor */
	public Material(String id, String materialNo, String meterialName,
			Date addTime, Boolean deleteFlag) {
		this.id = id;
		this.materialNo = materialNo;
		this.meterialName = meterialName;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Material(String id, Material material, Types dbTypes,
			String materialNo, String meterialName, String meterialRemark,
			Date addTime, Date updateTime, Date deleteTime,
			Boolean deleteFlag) {
		this.id = id;
		this.material = material;
		this.dbTypes = dbTypes;
		this.materialNo = materialNo;
		this.meterialName = meterialName;
		this.meterialRemark = meterialRemark;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "material_id", unique = true, nullable = true,length=33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "father_material_id")
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	public Types getDbTypes() {
		return this.dbTypes;
	}

	public void setDbTypes(Types dbTypes) {
		this.dbTypes = dbTypes;
	}

	@Column(name = "material_no", nullable = true, length = 20)
	public String getMaterialNo() {
		return this.materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	@Column(name = "meterial_name", nullable = true, length = 20)
	public String getMeterialName() {
		return this.meterialName;
	}

	public void setMeterialName(String meterialName) {
		this.meterialName = meterialName;
	}

	@Column(name = "meterial_remark", length = 20)
	public String getMeterialRemark() {
		return this.meterialRemark;
	}

	public void setMeterialRemark(String meterialRemark) {
		this.meterialRemark = meterialRemark;
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

	@Column(name = "delete_flag", nullable = true)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}