package com.xiaov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "vote", catalog = "xvdz")
public class Vote {

	
	private String id;
	private String name;
	private String description;
	private String slogan;
	private Integer mount;
	private String pic1;
	private String school;
	private String pic2;
	private String openId;
	
	@Id
	@Column(name = "id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="mount")
	public Integer getMount() {
		return mount;
	}
	public void setMount(Integer count) {
		this.mount = count;
	}
	@Column(name="slogan")
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	@Column(name="pic1")
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	@Column(name="school")
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPic2() {
		return pic2;
	}
	@Column(name="pic2")
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	@Transient
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
