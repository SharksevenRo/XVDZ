package com.xiaov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.xiaov.orm.core.Page;

@Entity
@Table(name = "product_detail", catalog = "xvdz")
public class ProductDetail extends Page<ProductDetail>{

	/**
	 * 该商品的颜色种类
	 */
	public static final String COLOR="color";
	/**
	 * 该商品的面料种类
	 */
	public static final String MATERIAL="material";
	
	/**
	 * 改商品的面料类型
	 */
	public static final String SIZE="size";
	
	private String id;
	private String productId;
	private String picB;
	private String picF;
	private String colorName;
	private String type;
	private Double price;
	
	public ProductDetail() {
	}
	
	
	public ProductDetail(String productId, String picB, String colorName, String type) {
		super();
		this.productId = productId;
		this.picB = picB;
		this.colorName = colorName;
		this.type = type;
	}

	public ProductDetail(String id, String productId, String picB, String colorName, String type) {
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
	@Column(name = "picB", length = 50)
	public String getPicB() {
		return picB;
	}
	public void setPicB(String pic) {
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


	public String getPicF() {
		return picF;
	}

	@Column(name = "picf")
	public void setPicF(String picF) {
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
