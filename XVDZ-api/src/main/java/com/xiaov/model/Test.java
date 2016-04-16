package com.xiaov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;

@Entity
@Table(name = "receive_address", catalog = "xvdz")
@StateDelete(propertyName = "isvalid",type = FieldType.B,value="0")
public class Test {

	private String testId;
	private String name;
	private Boolean isValid;
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Id
	@Column(name = "re_add_id", unique = true, nullable = false, length = 20)
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "isvalid", nullable = false)
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
}
