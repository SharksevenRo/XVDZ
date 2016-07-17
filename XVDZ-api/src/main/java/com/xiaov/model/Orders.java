package com.xiaov.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orders", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
public class Orders extends Page<Orders> implements java.io.Serializable {

	/**
	 * 取消
	 */
	public static final Integer CONCEL=new Integer(-1);
	/**
	 * 未支付
	 */
	public static final Integer UNPAY=new Integer(0);
	/**
	 * 已支付
	 */
	public static final Integer PAY=new Integer(1);
	/**
	 * 失败
	 */
	public static final Integer FAILED=new Integer(2);
	/**
	 * 配送
	 */
	public static final Integer TREANS=new Integer(3);
	/**
	 * 结束
	 */
	public static final Integer FINISHED=new Integer(3);

	// Fields
	private String id;
	private Types dbTypes;
	private DiscountCoupan discountCoupan;
	private UserInfo user;
	private String orNo;
	private Double orTotal;
	private Double orDiscount;
	private Double orRealCost;
	private Integer orState;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String orRemark;
	private Integer deleteFlag=0;

	private String logisticsNo;
	private String logisticsName;

	private List<OrderDetail> orderDetails;



	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(String id, String orNo, Double orTotal, Integer orState,
			Date addTime, Integer deleteFlag) {
		this.id = id;
		this.orNo = orNo;
		this.orTotal = orTotal;
		this.orState = orState;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public Orders(String id, OrderDetail orderDetail, Types dbTypes,
			DiscountCoupan discountCoupan, String ueId, String orNo,
			Double orTotal, Double orDiscount, Double orRealCost,
			Integer orState, Date addTime, Date updateTime,
			Date deleteTime, String orRemark, Integer deleteFlag) {
		this.id = id;
		this.dbTypes = dbTypes;
		this.discountCoupan = discountCoupan;
		this.orNo = orNo;
		this.orTotal = orTotal;
		this.orDiscount = orDiscount;
		this.orRealCost = orRealCost;
		this.orState = orState;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.orRemark = orRemark;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "or_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_type_id")
	public Types getDbTypes() {
		return this.dbTypes;
	}

	public void setDbTypes(Types dbTypes) {
		this.dbTypes = dbTypes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dis_cou_id")
	public DiscountCoupan getDiscountCoupan() {
		return this.discountCoupan;
	}

	public void setDiscountCoupan(DiscountCoupan discountCoupan) {
		this.discountCoupan = discountCoupan;
	}


	@Column(name = "or_no", nullable = true, length = 20)
	public String getOrNo() {
		return this.orNo;
	}

	public void setOrNo(String orNo) {
		this.orNo = orNo;
	}

	@Column(name = "or_total", nullable = true, precision = 22, scale = 0)
	public Double getOrTotal() {
		return this.orTotal;
	}

	public void setOrTotal(Double orTotal) {
		this.orTotal = orTotal;
	}

	@Column(name = "or_discount", precision = 22, scale = 0)
	public Double getOrDiscount() {
		return this.orDiscount;
	}

	public void setOrDiscount(Double orDiscount) {
		this.orDiscount = orDiscount;
	}

	@Column(name = "or_real_cost", precision = 22, scale = 0)
	public Double getOrRealCost() {
		return this.orRealCost;
	}

	public void setOrRealCost(Double orRealCost) {
		this.orRealCost = orRealCost;
	}

	@Column(name = "or_state", nullable = true)
	public Integer getOrState() {
		return this.orState;
	}

	public void setOrState(Integer orState) {
		this.orState = orState;
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

	@Column(name = "or_remark", length = 200)
	public String getOrRemark() {
		return this.orRemark;
	}

	public void setOrRemark(String orRemark) {
		this.orRemark = orRemark;
	}

	@Column(name = "delete_Flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Transient
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "u_id")
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Column(name="logisticsname",length = 20)
	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	@Column(name="logisticsno",length = 20)
	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}
}