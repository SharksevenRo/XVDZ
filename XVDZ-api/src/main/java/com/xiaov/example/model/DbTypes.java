package com.xiaov.example.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Set<Product> productsForClothTypeId = new HashSet<Product>(0);
	private Set<DbTypes> dbTypeses = new HashSet<DbTypes>(0);
	private Set<Material> materials = new HashSet<Material>(0);
	private Set<Product> productsForPdtTypeId = new HashSet<Product>(0);
	private Set<Product> productsForSizeTypeId = new HashSet<Product>(0);
	private Set<Product> productsForColorTypeId = new HashSet<Product>(0);
	private Set<Orders> orderses = new HashSet<Orders>(0);
	private Set<Product> productsForStyleTypeId = new HashSet<Product>(0);
	private Set<BankCard> bankCards = new HashSet<BankCard>(0);

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
			Boolean deleteFlage, Set<Product> productsForClothTypeId,
			Set<DbTypes> dbTypeses, Set<Material> materials,
			Set<Product> productsForPdtTypeId,
			Set<Product> productsForSizeTypeId,
			Set<Product> productsForColorTypeId, Set<Orders> orderses,
			Set<Product> productsForStyleTypeId, Set<BankCard> bankCards) {
		this.dbTypes = dbTypes;
		this.typeName = typeName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.typeRemark = typeRemark;
		this.deleteFlage = deleteFlage;
		this.productsForClothTypeId = productsForClothTypeId;
		this.dbTypeses = dbTypeses;
		this.materials = materials;
		this.productsForPdtTypeId = productsForPdtTypeId;
		this.productsForSizeTypeId = productsForSizeTypeId;
		this.productsForColorTypeId = productsForColorTypeId;
		this.orderses = orderses;
		this.productsForStyleTypeId = productsForStyleTypeId;
		this.bankCards = bankCards;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypesByClothTypeId")
	public Set<Product> getProductsForClothTypeId() {
		return this.productsForClothTypeId;
	}

	public void setProductsForClothTypeId(Set<Product> productsForClothTypeId) {
		this.productsForClothTypeId = productsForClothTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypes")
	public Set<DbTypes> getDbTypeses() {
		return this.dbTypeses;
	}

	public void setDbTypeses(Set<DbTypes> dbTypeses) {
		this.dbTypeses = dbTypeses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypes")
	public Set<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypesByPdtTypeId")
	public Set<Product> getProductsForPdtTypeId() {
		return this.productsForPdtTypeId;
	}

	public void setProductsForPdtTypeId(Set<Product> productsForPdtTypeId) {
		this.productsForPdtTypeId = productsForPdtTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypesBySizeTypeId")
	public Set<Product> getProductsForSizeTypeId() {
		return this.productsForSizeTypeId;
	}

	public void setProductsForSizeTypeId(Set<Product> productsForSizeTypeId) {
		this.productsForSizeTypeId = productsForSizeTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypesByColorTypeId")
	public Set<Product> getProductsForColorTypeId() {
		return this.productsForColorTypeId;
	}

	public void setProductsForColorTypeId(Set<Product> productsForColorTypeId) {
		this.productsForColorTypeId = productsForColorTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypes")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypesByStyleTypeId")
	public Set<Product> getProductsForStyleTypeId() {
		return this.productsForStyleTypeId;
	}

	public void setProductsForStyleTypeId(Set<Product> productsForStyleTypeId) {
		this.productsForStyleTypeId = productsForStyleTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dbTypes")
	public Set<BankCard> getBankCards() {
		return this.bankCards;
	}

	public void setBankCards(Set<BankCard> bankCards) {
		this.bankCards = bankCards;
	}

}