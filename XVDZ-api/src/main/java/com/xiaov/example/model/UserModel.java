package com.xiaov.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity 
@Table(name="tb_user")
public class UserModel implements Serializable{

	private Long userId;
	private String name;
	private RoleModel role;
	
	@Id 
	@Column(name = "id", unique = true, nullable = false,insertable=false, updatable=false) 
	@GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY) 
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "name", length = 50) 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "roleId") 
	public RoleModel getRole() {
		return role;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}
}
