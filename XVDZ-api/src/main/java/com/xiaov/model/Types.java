package com.xiaov.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
 * parentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "types", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
@JsonIgnoreProperties(value={ "hibernateLazyInitializer" })
public class Types extends Page<Types> implements java.io.Serializable {

	// Fields

	private String id;
	@JsonIgnore
	private Types parentType;
	private String typeName;
	private String typeTag;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String typeRemark;
	private Integer deleteFlag=0;
	
	private List<Material>materials;

	List<Types> children;

	// Constructors

	/** default constructor */
	public Types() {
	}

	/** minimal constructor */
	public Types(String typeName, Date addTime, Integer deleteFlag) {
		this.typeName = typeName;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Types(String id, Types parentType, String typeName, String typeTag, Date addTime, Date updateTime,
			Date deleteTime, String typeRemark, Integer deleteFlag) {
		super();
		this.id = id;
		this.parentType = parentType;
		this.typeName = typeName;
		this.typeTag = typeTag;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.typeRemark = typeRemark;
		this.deleteFlag = deleteFlag;
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
	
	@Column(name = "type_tag", length = 50)
	public String getTypeTag() {
		return typeTag;
	}

	public void setTypeTag(String typeTag) {
		this.typeTag = typeTag;
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

	@Column(name = "type_remark", length = 200)
	public String getTypeRemark() {
		return this.typeRemark;
	}

	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}

	@Column(name = "delete_Flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Transient
	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	@Transient
	public List<Types> getChildren() {
		return children;
	}

	public void setChildren(List<Types> children) {
		this.children = children;
	}
}