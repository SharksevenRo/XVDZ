package com.xiaov.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xiaov.orm.core.Page;
@Entity 
@Table(name="tb_role")
/**
 * 必须实现Serializable接口，会涉及到分页查询的继承Page<T>,暂时全部继承
 * @author Sharkseven
 *
 */
public class RoleModel extends Page<RoleModel> implements Serializable{

	//必须定义序列化id
	private static final long serialVersionUID = 1L;
	
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
