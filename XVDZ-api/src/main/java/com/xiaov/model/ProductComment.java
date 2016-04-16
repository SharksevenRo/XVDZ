package com.xiaov.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ProductComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product_comment", catalog = "xvdz")
public class ProductComment implements java.io.Serializable {

	// Fields
	private String cmtId;
	private Product product;
	private Material material;
	private ProductComment productComment;
	private String cmtContent;
	private Integer cmtQtSt;
	private Integer cmtLogisticsSt;
	private Integer cmtWrapSt;
	private Timestamp cmtTime;

	// Constructors

	/** default constructor */
	public ProductComment() {
	}

	/** minimal constructor */
	public ProductComment(String cmtId, String cmtContent, Integer cmtQtSt,
			Integer cmtLogisticsSt, Integer cmtWrapSt, Timestamp cmtTime) {
		this.cmtId = cmtId;
		this.cmtContent = cmtContent;
		this.cmtQtSt = cmtQtSt;
		this.cmtLogisticsSt = cmtLogisticsSt;
		this.cmtWrapSt = cmtWrapSt;
		this.cmtTime = cmtTime;
	}

	/** full constructor */
	public ProductComment(String cmtId, Product product, Material material,
			ProductComment productComment, String cmtContent, Integer cmtQtSt,
			Integer cmtLogisticsSt, Integer cmtWrapSt, Timestamp cmtTime) {
		this.cmtId = cmtId;
		this.product = product;
		this.material = material;
		this.productComment = productComment;
		this.cmtContent = cmtContent;
		this.cmtQtSt = cmtQtSt;
		this.cmtLogisticsSt = cmtLogisticsSt;
		this.cmtWrapSt = cmtWrapSt;
		this.cmtTime = cmtTime;
	}

	// Property accessors
	@Id
	@Column(name = "cmt_id", unique = true, nullable = false, length = 20)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getCmtId() {
		return this.cmtId;
	}

	public void setCmtId(String cmtId) {
		this.cmtId = cmtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pdt_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
	@JoinColumn(name = "f_cmt_id")
	public ProductComment getProductComment() {
		return this.productComment;
	}

	public void setProductComment(ProductComment productComment) {
		this.productComment = productComment;
	}

	@Column(name = "cmt_content", nullable = false, length = 200)
	public String getCmtContent() {
		return this.cmtContent;
	}

	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}

	@Column(name = "cmt_qt_st", nullable = false)
	public Integer getCmtQtSt() {
		return this.cmtQtSt;
	}

	public void setCmtQtSt(Integer cmtQtSt) {
		this.cmtQtSt = cmtQtSt;
	}

	@Column(name = "cmt_logistics_st", nullable = false)
	public Integer getCmtLogisticsSt() {
		return this.cmtLogisticsSt;
	}

	public void setCmtLogisticsSt(Integer cmtLogisticsSt) {
		this.cmtLogisticsSt = cmtLogisticsSt;
	}

	@Column(name = "cmt_wrap_st", nullable = false)
	public Integer getCmtWrapSt() {
		return this.cmtWrapSt;
	}

	public void setCmtWrapSt(Integer cmtWrapSt) {
		this.cmtWrapSt = cmtWrapSt;
	}

	@Column(name = "cmt_time", nullable = false, length = 0)
	public Timestamp getCmtTime() {
		return this.cmtTime;
	}

	public void setCmtTime(Timestamp cmtTime) {
		this.cmtTime = cmtTime;
	}

}