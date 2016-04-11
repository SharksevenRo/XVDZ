package com.xiaov.model;

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
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product", catalog = "xvdz")
public class Product implements java.io.Serializable {

	// Fields

	private Integer pdtId;
	private DbTypes dbTypesByColorTypeId;
	private DbTypes dbTypesBySizeTypeId;
	private DbTypes dbTypesByClothTypeId;
	private DbTypes dbTypesByPdtTypeId;
	private Material material;
	private DbTypes dbTypesByStyleTypeId;
	private String usId;
	private String pdtNo;
	private String pdtName;
	private Double pdtIntRat;
	private String pdtLabel;
	private String pdtPc;
	private String pdtPicBs;
	private String pdtPicBp;
	private Double pdtPrc;
	private Double pdtDsct;
	private Integer pdtSaleCount;
	private Integer pdtGdCount;
	private Integer pdtShareCount;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private Boolean pdtOpenState;
	private String remark;
	private Boolean deletFlag;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(Integer pdtId, String pdtNo, String pdtName,
			String pdtPicBs, Double pdtPrc, Integer pdtSaleCount,
			Integer pdtGdCount, Integer pdtShareCount, Timestamp addTime,
			Boolean pdtOpenState, Boolean deletFlag) {
		this.pdtId = pdtId;
		this.pdtNo = pdtNo;
		this.pdtName = pdtName;
		this.pdtPicBs = pdtPicBs;
		this.pdtPrc = pdtPrc;
		this.pdtSaleCount = pdtSaleCount;
		this.pdtGdCount = pdtGdCount;
		this.pdtShareCount = pdtShareCount;
		this.addTime = addTime;
		this.pdtOpenState = pdtOpenState;
		this.deletFlag = deletFlag;
	}

	/** full constructor */
	public Product(Integer pdtId, DbTypes dbTypesByColorTypeId,
			DbTypes dbTypesBySizeTypeId, DbTypes dbTypesByClothTypeId,
			DbTypes dbTypesByPdtTypeId, Material material,
			DbTypes dbTypesByStyleTypeId, String usId, String pdtNo,
			String pdtName, Double pdtIntRat, String pdtLabel, String pdtPc,
			String pdtPicBs, String pdtPicBp, Double pdtPrc, Double pdtDsct,
			Integer pdtSaleCount, Integer pdtGdCount, Integer pdtShareCount,
			Timestamp addTime, Timestamp updateTime, Timestamp deleteTime,
			Boolean pdtOpenState, String remark, Boolean deletFlag) {
		this.pdtId = pdtId;
		this.dbTypesByColorTypeId = dbTypesByColorTypeId;
		this.dbTypesBySizeTypeId = dbTypesBySizeTypeId;
		this.dbTypesByClothTypeId = dbTypesByClothTypeId;
		this.dbTypesByPdtTypeId = dbTypesByPdtTypeId;
		this.material = material;
		this.dbTypesByStyleTypeId = dbTypesByStyleTypeId;
		this.usId = usId;
		this.pdtNo = pdtNo;
		this.pdtName = pdtName;
		this.pdtIntRat = pdtIntRat;
		this.pdtLabel = pdtLabel;
		this.pdtPc = pdtPc;
		this.pdtPicBs = pdtPicBs;
		this.pdtPicBp = pdtPicBp;
		this.pdtPrc = pdtPrc;
		this.pdtDsct = pdtDsct;
		this.pdtSaleCount = pdtSaleCount;
		this.pdtGdCount = pdtGdCount;
		this.pdtShareCount = pdtShareCount;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.pdtOpenState = pdtOpenState;
		this.remark = remark;
		this.deletFlag = deletFlag;
	}

	// Property accessors
	@Id
	@Column(name = "pdt_id", unique = true, nullable = false)
	public Integer getPdtId() {
		return this.pdtId;
	}

	public void setPdtId(Integer pdtId) {
		this.pdtId = pdtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "color_type_id")
	public DbTypes getDbTypesByColorTypeId() {
		return this.dbTypesByColorTypeId;
	}

	public void setDbTypesByColorTypeId(DbTypes dbTypesByColorTypeId) {
		this.dbTypesByColorTypeId = dbTypesByColorTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "size_type_id")
	public DbTypes getDbTypesBySizeTypeId() {
		return this.dbTypesBySizeTypeId;
	}

	public void setDbTypesBySizeTypeId(DbTypes dbTypesBySizeTypeId) {
		this.dbTypesBySizeTypeId = dbTypesBySizeTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cloth_type_id")
	public DbTypes getDbTypesByClothTypeId() {
		return this.dbTypesByClothTypeId;
	}

	public void setDbTypesByClothTypeId(DbTypes dbTypesByClothTypeId) {
		this.dbTypesByClothTypeId = dbTypesByClothTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pdt_type_id")
	public DbTypes getDbTypesByPdtTypeId() {
		return this.dbTypesByPdtTypeId;
	}

	public void setDbTypesByPdtTypeId(DbTypes dbTypesByPdtTypeId) {
		this.dbTypesByPdtTypeId = dbTypesByPdtTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "style_type_id")
	public DbTypes getDbTypesByStyleTypeId() {
		return this.dbTypesByStyleTypeId;
	}

	public void setDbTypesByStyleTypeId(DbTypes dbTypesByStyleTypeId) {
		this.dbTypesByStyleTypeId = dbTypesByStyleTypeId;
	}

	@Column(name = "us_id", length = 20)
	public String getUsId() {
		return this.usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	@Column(name = "pdt_no", nullable = false, length = 20)
	public String getPdtNo() {
		return this.pdtNo;
	}

	public void setPdtNo(String pdtNo) {
		this.pdtNo = pdtNo;
	}

	@Column(name = "pdt_name", nullable = false, length = 50)
	public String getPdtName() {
		return this.pdtName;
	}

	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}

	@Column(name = "pdt_int_rat", precision = 22, scale = 0)
	public Double getPdtIntRat() {
		return this.pdtIntRat;
	}

	public void setPdtIntRat(Double pdtIntRat) {
		this.pdtIntRat = pdtIntRat;
	}

	@Column(name = "pdt_label", length = 0)
	public String getPdtLabel() {
		return this.pdtLabel;
	}

	public void setPdtLabel(String pdtLabel) {
		this.pdtLabel = pdtLabel;
	}

	@Column(name = "pdt_pc", length = 2000)
	public String getPdtPc() {
		return this.pdtPc;
	}

	public void setPdtPc(String pdtPc) {
		this.pdtPc = pdtPc;
	}

	@Column(name = "pdt_pic_bs", nullable = false, length = 50)
	public String getPdtPicBs() {
		return this.pdtPicBs;
	}

	public void setPdtPicBs(String pdtPicBs) {
		this.pdtPicBs = pdtPicBs;
	}

	@Column(name = "pdt_pic_bp", length = 50)
	public String getPdtPicBp() {
		return this.pdtPicBp;
	}

	public void setPdtPicBp(String pdtPicBp) {
		this.pdtPicBp = pdtPicBp;
	}

	@Column(name = "pdt_prc", nullable = false, precision = 22, scale = 0)
	public Double getPdtPrc() {
		return this.pdtPrc;
	}

	public void setPdtPrc(Double pdtPrc) {
		this.pdtPrc = pdtPrc;
	}

	@Column(name = "pdt_dsct", precision = 22, scale = 0)
	public Double getPdtDsct() {
		return this.pdtDsct;
	}

	public void setPdtDsct(Double pdtDsct) {
		this.pdtDsct = pdtDsct;
	}

	@Column(name = "pdt_sale_count", nullable = false)
	public Integer getPdtSaleCount() {
		return this.pdtSaleCount;
	}

	public void setPdtSaleCount(Integer pdtSaleCount) {
		this.pdtSaleCount = pdtSaleCount;
	}

	@Column(name = "pdt_gd_count", nullable = false)
	public Integer getPdtGdCount() {
		return this.pdtGdCount;
	}

	public void setPdtGdCount(Integer pdtGdCount) {
		this.pdtGdCount = pdtGdCount;
	}

	@Column(name = "pdt_share_count", nullable = false)
	public Integer getPdtShareCount() {
		return this.pdtShareCount;
	}

	public void setPdtShareCount(Integer pdtShareCount) {
		this.pdtShareCount = pdtShareCount;
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

	@Column(name = "pdt_open_state", nullable = false)
	public Boolean getPdtOpenState() {
		return this.pdtOpenState;
	}

	public void setPdtOpenState(Boolean pdtOpenState) {
		this.pdtOpenState = pdtOpenState;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "delet_flag", nullable = false)
	public Boolean getDeletFlag() {
		return this.deletFlag;
	}

	public void setDeletFlag(Boolean deletFlag) {
		this.deletFlag = deletFlag;
	}
}