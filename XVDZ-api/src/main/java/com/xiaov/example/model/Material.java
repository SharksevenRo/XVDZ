package com.xiaov.example.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Material entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "material", catalog = "xvdz")
public class Material implements java.io.Serializable {

	// Fields

	private Integer materialId;
	private Material material;
	private DbTypes dbTypes;
	private String materialNo;
	private String meterialName;
	private String meterialRemark;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private Boolean deleteFlag;
	private Set<Product> products = new HashSet<Product>(0);
	private Set<Material> materials = new HashSet<Material>(0);
	private Set<ProductComment> productComments = new HashSet<ProductComment>(0);

	// Constructors

	/** default constructor */
	public Material() {
	}

	/** minimal constructor */
	public Material(Integer materialId, String materialNo, String meterialName,
			Timestamp addTime, Boolean deleteFlag) {
		this.materialId = materialId;
		this.materialNo = materialNo;
		this.meterialName = meterialName;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Material(Integer materialId, Material material, DbTypes dbTypes,
			String materialNo, String meterialName, String meterialRemark,
			Timestamp addTime, Timestamp updateTime, Timestamp deleteTime,
			Boolean deleteFlag, Set<Product> products, Set<Material> materials,
			Set<ProductComment> productComments) {
		this.materialId = materialId;
		this.material = material;
		this.dbTypes = dbTypes;
		this.materialNo = materialNo;
		this.meterialName = meterialName;
		this.meterialRemark = meterialRemark;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.deleteFlag = deleteFlag;
		this.products = products;
		this.materials = materials;
		this.productComments = productComments;
	}

	// Property accessors
	@Id
	@Column(name = "material_id", unique = true, nullable = false)
	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
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
	public DbTypes getDbTypes() {
		return this.dbTypes;
	}

	public void setDbTypes(DbTypes dbTypes) {
		this.dbTypes = dbTypes;
	}

	@Column(name = "material_no", nullable = false, length = 20)
	public String getMaterialNo() {
		return this.materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	@Column(name = "meterial_name", nullable = false, length = 20)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "material")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "material")
	public Set<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "material")
	public Set<ProductComment> getProductComments() {
		return this.productComments;
	}

	public void setProductComments(Set<ProductComment> productComments) {
		this.productComments = productComments;
	}

}