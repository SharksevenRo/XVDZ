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

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * Advertisment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "advertisment", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")

public class Advertisment extends Page<Advertisment> implements java.io.Serializable {

	// Fields

	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private UserInfo userInfoByDeleteId;
	private UserInfo userInfoByUpdateId;
	private String addId;
	private String adsTt;
	private String adsContent;
	private String adsPic;
	private Boolean adsState;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date adsOnme;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private Integer deleteFlag=0;

	// Constructors

	/** default constructor */
	public Advertisment() {
	}

	/** minimal constructor */
	public Advertisment(String id, String adsContent, Boolean adsState,
			Date addTime) {
		this.id = id;
		this.adsContent = adsContent;
		this.adsState = adsState;
		this.addTime = addTime;
	}

	/** full constructor */
	public Advertisment(String id, UserInfo userInfoByDeleteId,
			UserInfo userInfoByUpdateId, String addId, String adsTt,
			String adsContent, String adsPic, Boolean adsState,
			Date addTime, Date adsOnme, Date updateTime,
			Date deleteTime, Integer deleteFlag) {
		this.id = id;
		this.userInfoByDeleteId = userInfoByDeleteId;
		this.userInfoByUpdateId = userInfoByUpdateId;
		this.addId = addId;
		this.adsTt = adsTt;
		this.adsContent = adsContent;
		this.adsPic = adsPic;
		this.adsState = adsState;
		this.addTime = addTime;
		this.adsOnme = adsOnme;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "ads_id", unique = true, nullable = true ,length=33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delete_id")
	public UserInfo getUserInfoByDeleteId() {
		return this.userInfoByDeleteId;
	}

	public void setUserInfoByDeleteId(UserInfo userInfoByDeleteId) {
		this.userInfoByDeleteId = userInfoByDeleteId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_id")
	public UserInfo getUserInfoByUpdateId() {
		return this.userInfoByUpdateId;
	}

	public void setUserInfoByUpdateId(UserInfo userInfoByUpdateId) {
		this.userInfoByUpdateId = userInfoByUpdateId;
	}

	@Column(name = "add_id", length = 20)
	public String getAddId() {
		return this.addId;
	}

	public void setAddId(String addId) {
		this.addId = addId;
	}

	@Column(name = "ads_tt", length = 0)
	public String getAdsTt() {
		return this.adsTt;
	}

	public void setAdsTt(String adsTt) {
		this.adsTt = adsTt;
	}

	@Column(name = "ads_content", nullable = true, length = 0)
	public String getAdsContent() {
		return this.adsContent;
	}

	public void setAdsContent(String adsContent) {
		this.adsContent = adsContent;
	}

	@Column(name = "ads_pic", length = 0)
	public String getAdsPic() {
		return this.adsPic;
	}

	public void setAdsPic(String adsPic) {
		this.adsPic = adsPic;
	}

	@Column(name = "ads_state", nullable = true)
	public Boolean getAdsState() {
		return this.adsState;
	}

	public void setAdsState(Boolean adsState) {
		this.adsState = adsState;
	}

	@Column(name = "add_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "ads_onme", length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAdsOnme() {
		return this.adsOnme;
	}

	public void setAdsOnme(Date adsOnme) {
		this.adsOnme = adsOnme;
	}

	@Column(name = "update_time", length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "delete_time", length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}