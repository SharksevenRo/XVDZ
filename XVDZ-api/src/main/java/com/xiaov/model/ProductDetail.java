package com.xiaov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.xiaov.orm.core.Page;

@Entity
@Table(name = "product_detail", catalog = "xvdz")

public class ProductDetail extends Page<ProductDetail>{

	
	private String id;
 	private String productId;
	private Material picB;
	private Material picF;
	private String colorName;
	private String type;
	private Double price;
	
	public ProductDetail() {
	}
	
	
	public ProductDetail(String productId, Material picB, String colorName, String type) {
		super();
		this.productId = productId;
		this.picB = picB;
		this.colorName = colorName;
		this.type = type;
	}

	public ProductDetail(String id, String productId, Material picB, String colorName, String type) {
		super();
		this.id = id;
		this.productId = productId;
		this.picB = picB;
		this.colorName = colorName;
		this.type = type;
	}

	// Property accessors
	@Id
	@Column(name = "detail_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "pdt_id", length = 50)
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "picb")
	public Material getPicB() {
		return picB;
	}
	public void setPicB(Material pic) {
		this.picB = pic;
	}
	@Column(name = "colorname", length = 50)
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String name) {
		this.colorName = name;
	}
	@Column(name = "type", length = 50)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "picf")
	public Material getPicF() {
		return picF;
	}

	
	public void setPicF(Material picF) {
		this.picF = picF;
	}

	@Column(name = "price")
	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}
	
}
