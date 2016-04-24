package com.xiaov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "product_detail", catalog = "xvdz")
public class ProductDetail {

	private String id;
	private String productId;
	private String pic;
	private String name;
	private String type;
	
	public ProductDetail() {
	}
	
	
	public ProductDetail(String productId, String pic, String name, String type) {
		super();
		this.productId = productId;
		this.pic = pic;
		this.name = name;
		this.type = type;
	}

	public ProductDetail(String id, String productId, String pic, String name, String type) {
		super();
		this.id = id;
		this.productId = productId;
		this.pic = pic;
		this.name = name;
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
	@Column(name = "pic", length = 50)
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "type", length = 50)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
