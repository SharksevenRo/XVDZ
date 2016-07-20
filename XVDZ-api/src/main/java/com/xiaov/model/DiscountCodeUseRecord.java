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
 * DiscountCodeUseRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "discount_code_use_record", catalog = "xvdz")

public class DiscountCodeUseRecord extends Page<DiscountCodeUseRecord> implements java.io.Serializable {

	// Fields
	private String id;
	private UserInfo userInfo;
	private String disCodeId;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date useTime;

	// Constructors

	/** default constructor */
	public DiscountCodeUseRecord() {
	}

	/** minimal constructor */
	public DiscountCodeUseRecord(String id, Date useTime) {
		this.id = id;
		this.useTime = useTime;
	}

	/** full constructor */
	public DiscountCodeUseRecord(String id, UserInfo userInfo,
			String disCodeId, Date useTime) {
		this.id = id;
		this.userInfo = userInfo;
		this.disCodeId = disCodeId;
		this.useTime = useTime;
	}

	// Property accessors
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Id
	@Column(name = "code_use_rcd_id", unique = true, nullable = true, length = 33)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "us_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "dis_code_id", length = 20)
	public String getDisCodeId() {
		return this.disCodeId;
	}

	public void setDisCodeId(String disCodeId) {
		this.disCodeId = disCodeId;
	}

	@Column(name = "use_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

}