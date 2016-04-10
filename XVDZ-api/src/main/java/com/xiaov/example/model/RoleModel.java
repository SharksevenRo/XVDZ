package com.xiaov.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity 
@Table(name="tb_role")
public class RoleModel implements Serializable{

	private Long roleId;
	private String name;
	
	
	@Id
	@Column(name = "roleId", unique = true, nullable = false,insertable=false, updatable=false)
	@GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY) 
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "name", length = 50) 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
