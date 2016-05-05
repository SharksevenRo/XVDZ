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

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * Material entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "material", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
public class Material extends Page<Material> implements java.io.Serializable {

	// Fields
	private String id;
	private Material material;
	private Types dbTypes;
	private String materialNo;
	private String meterialName;
	private String meterialRemark;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private Integer deleteFlag=0;

	// Constructors

	/** default constructor */
	public Material() {
	}

	/** minimal constructor */
	public Material(String id, String materialNo, String meterialName,
			Date addTime, Integer deleteFlag) {
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
			Integer deleteFlag) {
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