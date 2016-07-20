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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * ProductComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product_comment", catalog = "xvdz")

public class ProductComment extends Page<Orders> implements java.io.Serializable {

	// Fields
	private String id;
	private Product product;
	private Material material;
	private ProductComment productComment;
	private String cmtContent;
	private Integer cmtQtSt;
	private Integer cmtLogisticsSt;
	private Integer cmtWrapSt;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date cmtTime;

	// Constructors

	/** default constructor */
	public ProductComment() {
	}

	/** minimal constructor */
	public ProductComment(String id, String cmtContent, Integer cmtQtSt,
			Integer cmtLogisticsSt, Integer cmtWrapSt, Date cmtTime) {
		this.id = id;
		this.cmtContent = cmtContent;
		this.cmtQtSt = cmtQtSt;
		this.cmtLogisticsSt = cmtLogisticsSt;
		this.cmtWrapSt = cmtWrapSt;
		this.cmtTime = cmtTime;
	}

	/** full constructor */
	public ProductComment(String id, Product product, Material material,
			ProductComment productComment, String cmtContent, Integer cmtQtSt,
			Integer cmtLogisticsSt, Integer cmtWrapSt, Date cmtTime) {
		this.id = id;
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
	@Column(name = "cmt_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Column(name = "cmt_content", nullable = true, length = 200)
	public String getCmtContent() {
		return this.cmtContent;
	}

	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}

	@Column(name = "cmt_qt_st")
	public Integer getCmtQtSt() {
		return this.cmtQtSt;
	}

	public void setCmtQtSt(Integer cmtQtSt) {
		this.cmtQtSt = cmtQtSt;
	}

	@Column(name = "cmt_logistics_st")
	public Integer getCmtLogisticsSt() {
		return this.cmtLogisticsSt;
	}

	public void setCmtLogisticsSt(Integer cmtLogisticsSt) {
		this.cmtLogisticsSt = cmtLogisticsSt;
	}

	@Column(name = "cmt_wrap_st")
	public Integer getCmtWrapSt() {
		return this.cmtWrapSt;
	}

	public void setCmtWrapSt(Integer cmtWrapSt) {
		this.cmtWrapSt = cmtWrapSt;
	}

	@Column(name = "cmt_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCmtTime() {
		return this.cmtTime;
	}

	public void setCmtTime(Date cmtTime) {
		this.cmtTime = cmtTime;
	}

}