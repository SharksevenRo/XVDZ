package com.xiaov.example.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info", catalog = "xvdz")
public class UserInfo implements java.io.Serializable {

	// Fields

	private String usId;
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
	private Integer usLoginErrorTimes;
	private Timestamp usLastLoginTime;
	private Boolean usLoginState;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Timestamp deleteTime;
	private String usRemark;
	private Boolean deleteFlag;
	private Set<ReceiveAddress> receiveAddresses = new HashSet<ReceiveAddress>(
			0);
	private Set<Messages> messagesesForReceiveId = new HashSet<Messages>(0);
	private Set<Messages> messagesesForSendId = new HashSet<Messages>(0);
	private Set<Account> accounts = new HashSet<Account>(0);
	private Set<DiscountCoupan> discountCoupans = new HashSet<DiscountCoupan>(0);
	private Set<DiscountCode> discountCodesForProUId = new HashSet<DiscountCode>(
			0);
	private Set<Dynamic> dynamics = new HashSet<Dynamic>(0);
	private Set<DiscountCodeUseRecord> discountCodeUseRecords = new HashSet<DiscountCodeUseRecord>(
			0);
	private Set<Advertisment> advertismentsForDeleteId = new HashSet<Advertisment>(
			0);
	private Set<DiscountCode> discountCodesForGnrtUId = new HashSet<DiscountCode>(
			0);
	private Set<Advertisment> advertismentsForUpdateId = new HashSet<Advertisment>(
			0);

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String usId, String usNcNa, String usLgNa, String usPwd,
			Integer usState, Integer usLoginErrorTimes, Boolean usLoginState,
			Timestamp addTime, Boolean deleteFlag) {
		this.usId = usId;
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
	public UserInfo(String usId, Integer typeId, String appId, String usNcNa,
			String usLgNa, String usName, String usPwd, String usSex,
			Date usBirth, String usPic, String usPicO, String usTel,
			String usMail, String usNatrue, String usHobby, Integer usState,
			String usIdCard, Integer usLoginErrorTimes,
			Timestamp usLastLoginTime, Boolean usLoginState, Timestamp addTime,
			Timestamp updateTime, Timestamp deleteTime, String usRemark,
			Boolean deleteFlag, Set<ReceiveAddress> receiveAddresses,
			Set<Messages> messagesesForReceiveId,
			Set<Messages> messagesesForSendId, Set<Account> accounts,
			Set<DiscountCoupan> discountCoupans,
			Set<DiscountCode> discountCodesForProUId, Set<Dynamic> dynamics,
			Set<DiscountCodeUseRecord> discountCodeUseRecords,
			Set<Advertisment> advertismentsForDeleteId,
			Set<DiscountCode> discountCodesForGnrtUId,
			Set<Advertisment> advertismentsForUpdateId) {
		this.usId = usId;
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
		this.receiveAddresses = receiveAddresses;
		this.messagesesForReceiveId = messagesesForReceiveId;
		this.messagesesForSendId = messagesesForSendId;
		this.accounts = accounts;
		this.discountCoupans = discountCoupans;
		this.discountCodesForProUId = discountCodesForProUId;
		this.dynamics = dynamics;
		this.discountCodeUseRecords = discountCodeUseRecords;
		this.advertismentsForDeleteId = advertismentsForDeleteId;
		this.discountCodesForGnrtUId = discountCodesForGnrtUId;
		this.advertismentsForUpdateId = advertismentsForUpdateId;
	}

	// Property accessors
	@Id
	@Column(name = "us_id", unique = true, nullable = false, length = 20)
	public String getUsId() {
		return this.usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
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

	@Column(name = "us_nc_na", nullable = false, length = 20)
	public String getUsNcNa() {
		return this.usNcNa;
	}

	public void setUsNcNa(String usNcNa) {
		this.usNcNa = usNcNa;
	}

	@Column(name = "us_lg_na", nullable = false, length = 20)
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

	@Column(name = "us_pwd", nullable = false, length = 20)
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

	@Column(name = "us_state", nullable = false)
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

	@Column(name = "us_login_error_times", nullable = false)
	public Integer getUsLoginErrorTimes() {
		return this.usLoginErrorTimes;
	}

	public void setUsLoginErrorTimes(Integer usLoginErrorTimes) {
		this.usLoginErrorTimes = usLoginErrorTimes;
	}

	@Column(name = "us_last_login_time", length = 0)
	public Timestamp getUsLastLoginTime() {
		return this.usLastLoginTime;
	}

	public void setUsLastLoginTime(Timestamp usLastLoginTime) {
		this.usLastLoginTime = usLastLoginTime;
	}

	@Column(name = "us_login_state", nullable = false)
	public Boolean getUsLoginState() {
		return this.usLoginState;
	}

	public void setUsLoginState(Boolean usLoginState) {
		this.usLoginState = usLoginState;
	}

	@Column(name = "add_time", nullable = false, length = 0)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Column(name = "update_time", length = 0)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "delete_time", length = 0)
	public Timestamp getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "us_remark", length = 200)
	public String getUsRemark() {
		return this.usRemark;
	}

	public void setUsRemark(String usRemark) {
		this.usRemark = usRemark;
	}

	@Column(name = "delete_flag", nullable = false)
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<ReceiveAddress> getReceiveAddresses() {
		return this.receiveAddresses;
	}

	public void setReceiveAddresses(Set<ReceiveAddress> receiveAddresses) {
		this.receiveAddresses = receiveAddresses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfoByReceiveId")
	public Set<Messages> getMessagesesForReceiveId() {
		return this.messagesesForReceiveId;
	}

	public void setMessagesesForReceiveId(Set<Messages> messagesesForReceiveId) {
		this.messagesesForReceiveId = messagesesForReceiveId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfoBySendId")
	public Set<Messages> getMessagesesForSendId() {
		return this.messagesesForSendId;
	}

	public void setMessagesesForSendId(Set<Messages> messagesesForSendId) {
		this.messagesesForSendId = messagesesForSendId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<DiscountCoupan> getDiscountCoupans() {
		return this.discountCoupans;
	}

	public void setDiscountCoupans(Set<DiscountCoupan> discountCoupans) {
		this.discountCoupans = discountCoupans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfoByProUId")
	public Set<DiscountCode> getDiscountCodesForProUId() {
		return this.discountCodesForProUId;
	}

	public void setDiscountCodesForProUId(
			Set<DiscountCode> discountCodesForProUId) {
		this.discountCodesForProUId = discountCodesForProUId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<Dynamic> getDynamics() {
		return this.dynamics;
	}

	public void setDynamics(Set<Dynamic> dynamics) {
		this.dynamics = dynamics;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<DiscountCodeUseRecord> getDiscountCodeUseRecords() {
		return this.discountCodeUseRecords;
	}

	public void setDiscountCodeUseRecords(
			Set<DiscountCodeUseRecord> discountCodeUseRecords) {
		this.discountCodeUseRecords = discountCodeUseRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfoByDeleteId")
	public Set<Advertisment> getAdvertismentsForDeleteId() {
		return this.advertismentsForDeleteId;
	}

	public void setAdvertismentsForDeleteId(
			Set<Advertisment> advertismentsForDeleteId) {
		this.advertismentsForDeleteId = advertismentsForDeleteId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfoByGnrtUId")
	public Set<DiscountCode> getDiscountCodesForGnrtUId() {
		return this.discountCodesForGnrtUId;
	}

	public void setDiscountCodesForGnrtUId(
			Set<DiscountCode> discountCodesForGnrtUId) {
		this.discountCodesForGnrtUId = discountCodesForGnrtUId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfoByUpdateId")
	public Set<Advertisment> getAdvertismentsForUpdateId() {
		return this.advertismentsForUpdateId;
	}

	public void setAdvertismentsForUpdateId(
			Set<Advertisment> advertismentsForUpdateId) {
		this.advertismentsForUpdateId = advertismentsForUpdateId;
	}

}