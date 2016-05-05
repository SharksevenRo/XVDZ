package com.xiaov.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag", type = FieldType.I, value = "1")
public class UserInfo extends Page<UserInfo> implements java.io.Serializable {

	// Fields
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private Integer typeId;
	private String appId;
	private String usNcNa;
	private String usLgNa;
	private String usName;
	private String usPwd;
	private String usSex;
	private Date usBirth;
	private String usPic;
	private String usPicO;
	private String usTel;
	private String usMail;
	private String usNatrue;
	private String usHobby;
	private Integer usState;
	private String usIdCard;
	private String school;
	private String discountCode;

	private Integer usLoginErrorTimes;
	private Date usLastLoginTime;
	private Boolean usLoginState;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String usRemark;
	private Integer deleteFlag = 0;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String usId, String usNcNa, String usLgNa, String usPwd, Integer usState, Integer usLoginErrorTimes,
			Boolean usLoginState, Date addTime, Integer deleteFlag) {
		this.id = usId;
		this.usNcNa = usNcNa;
		this.usLgNa = usLgNa;
		this.usPwd = usPwd;
		this.usState = usState;
		this.usLoginErrorTimes = usLoginErrorTimes;
		this.usLoginState = usLoginState;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public UserInfo(String usId, Integer typeId, String appId, String usNcNa, String usLgNa, String usName,
			String usPwd, String usSex, Date usBirth, String usPic, String usPicO, String usTel, String usMail,
			String usNatrue, String usHobby, Integer usState, String usIdCard, Integer usLoginErrorTimes,
			Date usLastLoginTime, Boolean usLoginState, Date addTime, Date updateTime, Date deleteTime, String usRemark,
			Integer deleteFlag) {
		this.id = usId;
		this.typeId = typeId;
		this.appId = appId;
		this.usNcNa = usNcNa;
		this.usLgNa = usLgNa;
		this.usName = usName;
		this.usPwd = usPwd;
		this.usSex = usSex;
		this.usBirth = usBirth;
		this.usPic = usPic;
		this.usPicO = usPicO;
		this.usTel = usTel;
		this.usMail = usMail;
		this.usNatrue = usNatrue;
		this.usHobby = usHobby;
		this.usState = usState;
		this.usIdCard = usIdCard;
		this.usLoginErrorTimes = usLoginErrorTimes;
		this.usLastLoginTime = usLastLoginTime;
		this.usLoginState = usLoginState;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.usRemark = usRemark;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "us_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String usId) {
		this.id = usId;
	}

	@Column(name = "type_id")
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "appId", length = 40)
	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name = "us_nc_na", nullable = true, length = 20)
	public String getUsNcNa() {
		return this.usNcNa;
	}

	public void setUsNcNa(String usNcNa) {
		this.usNcNa = usNcNa;
	}

	@Column(name = "us_lg_na", nullable = true, length = 20)
	public String getUsLgNa() {
		return this.usLgNa;
	}

	public void setUsLgNa(String usLgNa) {
		this.usLgNa = usLgNa;
	}

	@Column(name = "us_name", length = 10)
	public String getUsName() {
		return this.usName;
	}

	public void setUsName(String usName) {
		this.usName = usName;
	}

	@Column(name = "us_pwd", nullable = true, length = 20)
	public String getUsPwd() {
		return this.usPwd;
	}

	public void setUsPwd(String usPwd) {
		this.usPwd = usPwd;
	}

	@Column(name = "us_sex", length = 10)
	public String getUsSex() {
		return this.usSex;
	}

	public void setUsSex(String usSex) {
		this.usSex = usSex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "us_birth", length = 0)
	public Date getUsBirth() {
		return this.usBirth;
	}

	public void setUsBirth(Date usBirth) {
		this.usBirth = usBirth;
	}

	@Column(name = "us_pic", length = 50)
	public String getUsPic() {
		return this.usPic;
	}

	public void setUsPic(String usPic) {
		this.usPic = usPic;
	}

	@Column(name = "us_pic_o", length = 50)
	public String getUsPicO() {
		return this.usPicO;
	}

	public void setUsPicO(String usPicO) {
		this.usPicO = usPicO;
	}

	@Column(name = "us_tel", length = 12)
	public String getUsTel() {
		return this.usTel;
	}

	public void setUsTel(String usTel) {
		this.usTel = usTel;
	}

	@Column(name = "us_mail", length = 20)
	public String getUsMail() {
		return this.usMail;
	}

	public void setUsMail(String usMail) {
		this.usMail = usMail;
	}

	@Column(name = "us_natrue", length = 200)
	public String getUsNatrue() {
		return this.usNatrue;
	}

	public void setUsNatrue(String usNatrue) {
		this.usNatrue = usNatrue;
	}

	@Column(name = "us_hobby", length = 200)
	public String getUsHobby() {
		return this.usHobby;
	}

	public void setUsHobby(String usHobby) {
		this.usHobby = usHobby;
	}

	@Column(name = "us_state", nullable = true)
	public Integer getUsState() {
		return this.usState;
	}

	public void setUsState(Integer usState) {
		this.usState = usState;
	}

	@Column(name = "us_id_card", length = 20)
	public String getUsIdCard() {
		return this.usIdCard;
	}

	public void setUsIdCard(String usIdCard) {
		this.usIdCard = usIdCard;
	}

	@Column(name = "us_login_error_times", nullable = true)
	public Integer getUsLoginErrorTimes() {
		return this.usLoginErrorTimes;
	}

	public void setUsLoginErrorTimes(Integer usLoginErrorTimes) {
		this.usLoginErrorTimes = usLoginErrorTimes;
	}

	@Column(name = "us_last_login_time", length = 0)
	public Date getUsLastLoginTime() {
		return this.usLastLoginTime;
	}

	public void setUsLastLoginTime(Date usLastLoginTime) {
		this.usLastLoginTime = usLastLoginTime;
	}

	@Column(name = "us_login_state", nullable = true)
	public Boolean getUsLoginState() {
		return this.usLoginState;
	}

	public void setUsLoginState(Boolean usLoginState) {
		this.usLoginState = usLoginState;
	}

	@Column(name = "add_time", nullable = true, length = 0)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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

	@Column(name = "us_remark", length = 200)
	public String getUsRemark() {
		return this.usRemark;
	}

	public void setUsRemark(String usRemark) {
		this.usRemark = usRemark;
	}

	@Column(name = "delete_Flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Column(name = "school")
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	@Column(name = "discountcode")
	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	
}