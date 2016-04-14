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

import com.xiaov.orm.core.Page;
@Entity 
@Table(name="tb_user")
/**
 * 必须实现Serializable接口，会涉及到分页查询的继承Page<T>,暂时全部继承
 * @author Sharkseven
 *
 */
public class UserModel extends Page<UserModel> implements Serializable{

	//必须定义序列化id
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String name;
	private RoleModel role;
	
	@Id 
	@Column(name = "userid", unique = true, nullable = false,insertable=false, updatable=false) 
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
	@JoinColumn(name = "roleid") 
	public RoleModel getRole() {
		return role;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}
}
