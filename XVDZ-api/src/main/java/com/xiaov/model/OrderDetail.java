package com.xiaov.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * OrderDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_detail", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
public class OrderDetail extends Page<OrderDetail> implements java.io.Serializable {

	// Fields
	private String id;
	private String orDtNo;
	private Double orDtPrc;

	private Integer orDtMount;
	private Double orDtDsct;
	private Double orDtItmeTotal;
	private Double orDtRlTotal;
	private String orDtRemark;
	//尺码
	private String size;
	//颜色
	private String color;
	
	//款式
	private String style;
	//用户定制图片
	private String pic;

	private Product designer_product;

	private String image_front;

	private String image_back;


	private List<Material> materials;

	private String designer_product_id;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private Integer deleteFlag=0;

	// Constructors

	/** default constructor */
	public OrderDetail() {
	}

	/** minimal constructor */
	public OrderDetail(String id, String orDtNo, Double orDtPrc,
			Integer orDtMount, Double orDtItmeTotal, Double orDtRlTotal,
			Date addTime, Integer deleteFlag) {
		this.id = id;
		this.orDtNo = orDtNo;
		this.orDtPrc = orDtPrc;
		this.orDtMount = orDtMount;
		this.orDtItmeTotal = orDtItmeTotal;
		this.orDtRlTotal = orDtRlTotal;
		this.addTime = addTime;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public OrderDetail(String id, String orDtNo,
			Double orDtPrc, Integer orDtMount, Double orDtDsct,
			Double orDtItmeTotal, Double orDtRlTotal, String orDtRemark,
			Date addTime, Date updateTime, Date deleteTime,
			Integer deleteFlag) {
		this.id = id;
		this.orDtNo = orDtNo;
		this.orDtPrc = orDtPrc;
		this.orDtMount = orDtMount;
		this.orDtDsct = orDtDsct;
		this.orDtItmeTotal = orDtItmeTotal;
		this.orDtRlTotal = orDtRlTotal;
		this.orDtRemark = orDtRemark;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors
	@Id
	@Column(name = "or_dt_id", unique = true, nullable = true, length = 33)
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "or_dt_no", nullable = true, length = 20)
	public String getOrDtNo() {
		return this.orDtNo;
	}

	public void setOrDtNo(String orDtNo) {
		this.orDtNo = orDtNo;
	}

	@Column(name = "or_dt_prc", nullable = true, precision = 22, scale = 0)
	public Double getOrDtPrc() {
		return this.orDtPrc;
	}

	public void setOrDtPrc(Double orDtPrc) {
		this.orDtPrc = orDtPrc;
	}

	@Column(name = "or_dt_mount", nullable = true)
	public Integer getOrDtMount() {
		return this.orDtMount;
	}

	public void setOrDtMount(Integer orDtMount) {
		this.orDtMount = orDtMount;
	}

	@Column(name = "or_dt_dsct", precision = 22, scale = 0)
	public Double getOrDtDsct() {
		return this.orDtDsct;
	}

	public void setOrDtDsct(Double orDtDsct) {
		this.orDtDsct = orDtDsct;
	}

	@Column(name = "or_dt_itme_total", nullable = true, precision = 22, scale = 0)
	public Double getOrDtItmeTotal() {
		return this.orDtItmeTotal;
	}

	public void setOrDtItmeTotal(Double orDtItmeTotal) {
		this.orDtItmeTotal = orDtItmeTotal;
	}

	@Column(name = "or_dt_rl_total", nullable = true, precision = 22, scale = 0)
	public Double getOrDtRlTotal() {
		return this.orDtRlTotal;
	}

	public void setOrDtRlTotal(Double orDtRlTotal) {
		this.orDtRlTotal = orDtRlTotal;
	}

	@Column(name = "or_dt_remark", length = 200)
	public String getOrDtRemark() {
		return this.orDtRemark;
	}

	public void setOrDtRemark(String orDtRemark) {
		this.orDtRemark = orDtRemark;
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

	@Column(name = "delete_Flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Column(name = "size", length = 0)
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	@Column(name = "color", length = 0)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	@Column(name = "pic", length = 0)
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	@Column(name = "style", length = 0)
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Transient
	public String getImage_front() {
		return image_front;
	}

	public void setImage_front(String designs) {
		this.image_front = designs;
	}

	@Transient
	public String getImage_back() {
		return image_back;
	}

	public void setImage_back(String image_back) {
		this.image_back = image_back;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pdtId")
	public Product getDesigner_product() {
		return designer_product;
	}

	public void setDesigner_product(Product designer_product) {
		this.designer_product = designer_product;
	}

	@Transient
	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	@Transient
	public String getDesigner_product_id() {
		return designer_product_id;
	}

	public void setDesigner_product_id(String designer_product_id) {
		this.designer_product_id = designer_product_id;
	}
}