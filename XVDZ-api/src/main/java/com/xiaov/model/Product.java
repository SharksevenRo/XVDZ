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

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
public class Product extends Page<Product> implements java.io.Serializable {

	// Fields

	private String id;
	private Types productType;
	private Material material;
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
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private Boolean pdtOpenState=true;
	private String remark;
	private String baseId;
	private String allPic;
	private Integer deleteFlag=0;
	
	private List<ProductDetail> detail;
	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(String id, String pdtNo, String pdtName,
			String pdtPicBs, Double pdtPrc, Integer pdtSaleCount,
			Integer pdtGdCount, Integer pdtShareCount, Date addTime,
			Boolean pdtOpenState, Integer deleteFlag) {
		this.id = id;
		this.pdtNo = pdtNo;
		this.pdtName = pdtName;
		this.pdtPicBs = pdtPicBs;
		this.pdtPrc = pdtPrc;
		this.pdtSaleCount = pdtSaleCount;
		this.pdtGdCount = pdtGdCount;
		this.pdtShareCount = pdtShareCount;
		this.addTime = addTime;
		this.pdtOpenState = pdtOpenState;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Product(String id, Types productType,
			 Material material,String usId, String pdtNo,
			String pdtName, Double pdtIntRat, String pdtLabel, String pdtPc,
			String pdtPicBs, String pdtPicBp, Double pdtPrc, Double pdtDsct,
			Integer pdtSaleCount, Integer pdtGdCount, Integer pdtShareCount,
			Date addTime, Date updateTime, Date deleteTime,
			Boolean pdtOpenState, String remark, Integer deleteFlag) {
		this.id = id;
		this.productType = productType;
		this.material = material;
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
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "pdt_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	public Types getProductType() {
		return this.productType;
	}

	public void setProductType(Types productType) {
		this.productType = productType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	@Column(name = "us_id", length = 20)
	public String getUsId() {
		return this.usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	@Column(name = "pdt_no", nullable = true, length = 20)
	public String getPdtNo() {
		return this.pdtNo;
	}

	public void setPdtNo(String pdtNo) {
		this.pdtNo = pdtNo;
	}

	@Column(name = "pdt_name", nullable = true, length = 50)
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

	@Column(name = "pdt_pic_bs", nullable = true, length = 50)
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

	@Column(name = "pdt_prc", nullable = true, precision = 22, scale = 0)
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

	@Column(name = "pdt_sale_count", nullable = true)
	public Integer getPdtSaleCount() {
		return this.pdtSaleCount;
	}

	public void setPdtSaleCount(Integer pdtSaleCount) {
		this.pdtSaleCount = pdtSaleCount;
	}

	@Column(name = "pdt_gd_count", nullable = true)
	public Integer getPdtGdCount() {
		return this.pdtGdCount;
	}

	public void setPdtGdCount(Integer pdtGdCount) {
		this.pdtGdCount = pdtGdCount;
	}

	@Column(name = "pdt_share_count", nullable = true)
	public Integer getPdtShareCount() {
		return this.pdtShareCount;
	}

	public void setPdtShareCount(Integer pdtShareCount) {
		this.pdtShareCount = pdtShareCount;
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

	@Column(name = "pdt_open_state", nullable = true)
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
	@Transient
	public List<ProductDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<ProductDetail> detail) {
		this.detail = detail;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "delet_flag", nullable = true)
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Column(name = "baseid", nullable = true)
	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	@Column(name = "allpic", nullable = true)
	public String getAllPic() {
		return allPic;
	}

	public void setAllPic(String allPic) {
		this.allPic = allPic;
	}
	
}