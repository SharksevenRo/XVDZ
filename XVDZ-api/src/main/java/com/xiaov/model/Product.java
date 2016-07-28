package com.xiaov.model;

import java.io.File;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaov.utils.PropertiesUtils;
import com.xiaov.utils.StrKit;
import com.xiaov.utils.StringFileToolkit;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.util.JSONPObject;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag", type = FieldType.I, value = "1")
public class Product extends Page<Product> implements java.io.Serializable {

    // Fields

    private String id;
    @JsonIgnore
    private Types productType;
    private String img;
    private String usId;
    private UserInfo user;
    private String pdtNo;
    private String pdtName;
    private Double pdtIntRat;
    @JsonIgnore
    private String pdtLabel;
    private String pdtPc;
    private String pdtPicBs;
    private String pdtPicBp;
    private Double pdtPrc;
    private Double pdtDsct;
    private Integer pdtSaleCount;
    private Integer pdtGdCount;
    private Integer pdtShareCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;
    private Boolean pdtOpenState = true;
    private String remark;
    private String baseId;
    private String show;
    private String img_back;

    private String backImage;
    private String imageBase64;

    private String productDesigner;

    private Integer isGroup;

    private Integer minnum;

    private Double groupPrice;

    private Integer deleteFlag = 0;


    private Integer isModule;

    private String style;
    private String designer_product_id;
    private String color;

    //溢价
    private Double premium;


    private List<ProductDetail> detail;

    // Constructors

    /**
     * default constructor
     */
    public Product() {
    }

    /**
     * minimal constructor
     */
    public Product(Double premium, String id, Types productType, String img, String usId, UserInfo user, String pdtNo, String pdtName, Double pdtIntRat, String pdtLabel, String pdtPc, String pdtPicBs, String pdtPicBp, Double pdtPrc, Double pdtDsct, Integer pdtSaleCount, Integer pdtGdCount, Integer pdtShareCount, Date addTime, Date updateTime, Date deleteTime, Boolean pdtOpenState, String remark, String baseId, String show, String img_back, String backImage, String imageBase64, String productDesigner, Integer isGroup, Integer minnum, Double groupPrice, Integer deleteFlag, Integer isModule, String style, String designer_product_id, String color) {
        this.premium = premium;
        this.id = id;
        this.productType = productType;
        this.img = img;
        this.usId = usId;
        this.user = user;
        this.pdtNo = pdtNo;
        this.pdtName = pdtName;
        this.pdtIntRat = pdtIntRat;
        this.pdtLabel = pdtLabel;
        this.pdtPc = pdtPc;
        this.pdtPicBs = pdtPicBs;
        this.pdtPicBp = pdtPicBp;
        this.pdtPrc = pdtPrc;
        this.pdtDsct = pdtDsct;
        this.pdtSaleCount = pdtSaleCount;
        this.pdtGdCount = pdtGdCount;
        this.pdtShareCount = pdtShareCount;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
        this.pdtOpenState = pdtOpenState;
        this.remark = remark;
        this.baseId = baseId;
        this.show = show;
        this.img_back = img_back;
        this.backImage = backImage;
        this.imageBase64 = imageBase64;
        this.productDesigner = productDesigner;
        this.isGroup = isGroup;
        this.minnum = minnum;
        this.groupPrice = groupPrice;
        this.deleteFlag = deleteFlag;
        this.isModule = isModule;
        this.style = style;
        this.designer_product_id = designer_product_id;
        this.color = color;
    }

    /**
     * full constructor
     */
    public Product(String id, Types productType,
                   String img, String usId, String pdtNo,
                   String pdtName, Double pdtIntRat, String pdtLabel, String pdtPc,
                   String pdtPicBs, Double pdtPrc, Double pdtDsct,
                   Integer pdtSaleCount, Integer pdtGdCount, Integer pdtShareCount,
                   Date addTime, Date updateTime, Date deleteTime,
                   Boolean pdtOpenState, String remark, Integer deleteFlag) {
        this.id = id;
        this.productType = productType;
        this.img = img;
        this.usId = usId;
        this.pdtNo = pdtNo;
        this.pdtName = pdtName;
        this.pdtIntRat = pdtIntRat;
        this.pdtLabel = pdtLabel;
        this.pdtPc = pdtPc;
        this.pdtPicBs = pdtPicBs;
        this.pdtPrc = pdtPrc;
        this.pdtDsct = pdtDsct;
        this.pdtSaleCount = pdtSaleCount;
        this.pdtGdCount = pdtGdCount;
        this.pdtShareCount = pdtShareCount;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
        this.pdtOpenState = pdtOpenState;
        this.remark = remark;
        this.deleteFlag = deleteFlag;
    }

    // Property accessors
    @Id
    @Column(name = "pdt_id", unique = true, nullable = true, length = 33)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    public Types getProductType() {
        return this.productType;
    }

    public void setProductType(Types productType) {
        this.productType = productType;
    }

    @Column(name = "img",length = 255)
    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Column(name = "us_id", length = 33)
    public String getUsId() {
        return this.usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    @Column(name = "pdt_no", nullable = true, length = 20)
    public String getPdtNo() {
        return this.pdtNo;
    }

    public void setPdtNo(String pdtNo) {
        this.pdtNo = pdtNo;
    }

    @Column(name = "pdt_name", nullable = true, length = 50)
    public String getPdtName() {
        return this.pdtName;
    }

    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }

    @Column(name = "pdt_int_rat", precision = 22, scale = 2)
    public Double getPdtIntRat() {
        return this.pdtIntRat;
    }

    public void setPdtIntRat(Double pdtIntRat) {
        this.pdtIntRat = pdtIntRat;
    }

    @Column(name = "pdt_label", length = 0)
    public String getPdtLabel() {
        return this.pdtLabel;
    }

    public void setPdtLabel(String pdtLabel) {
        this.pdtLabel = pdtLabel;
    }

    @Column(name = "pdt_pc", length = 2000)
    public String getPdtPc() {
        return this.pdtPc;
    }

    public void setPdtPc(String pdtPc) {
        this.pdtPc = pdtPc;
    }

    @Column(name = "pdt_pic_bs", nullable = true, length = 50)
    public String getPdtPicBs() {
        return this.pdtPicBs;
    }

    public void setPdtPicBs(String pdtPicBs) {
        this.pdtPicBs = pdtPicBs;
    }

    @Column(name = "pdt_prc", nullable = true, precision = 22, scale = 2)
    public Double getPdtPrc() {
        return this.pdtPrc;
    }

    public void setPdtPrc(Double pdtPrc) {
        this.pdtPrc = pdtPrc;
    }

    @Column(name = "pdt_dsct", precision = 22, scale = 2)
    public Double getPdtDsct() {
        return this.pdtDsct;
    }

    public void setPdtDsct(Double pdtDsct) {
        this.pdtDsct = pdtDsct;
    }

    @Column(name = "pdt_sale_count", nullable = true)
    public Integer getPdtSaleCount() {
        return this.pdtSaleCount;
    }

    public void setPdtSaleCount(Integer pdtSaleCount) {
        this.pdtSaleCount = pdtSaleCount;
    }

    @Column(name = "pdt_gd_count", nullable = true)
    public Integer getPdtGdCount() {
        return this.pdtGdCount;
    }

    public void setPdtGdCount(Integer pdtGdCount) {
        this.pdtGdCount = pdtGdCount;
    }

    @Column(name = "pdt_share_count", nullable = true)
    public Integer getPdtShareCount() {
        return this.pdtShareCount;
    }

    public void setPdtShareCount(Integer pdtShareCount) {
        this.pdtShareCount = pdtShareCount;
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

    @Column(name = "pdt_open_state", nullable = true)
    public Boolean getPdtOpenState() {
        return this.pdtOpenState;
    }

    public void setPdtOpenState(Boolean pdtOpenState) {
        this.pdtOpenState = pdtOpenState;
    }

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return this.remark;
    }

    @Transient
    public List<ProductDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<ProductDetail> detail) {
        this.detail = detail;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "delet_flag", nullable = true)
    public Integer getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Column(name = "baseid", nullable = true)
    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    @Column(name = "allpic",length = 255)
    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    @Column(name = "imagebase64", length = 16777216)
    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    @Column(name = "productdesigner", length = 16777216)
    public String getProductDesigner() {

        return this.productDesigner;
    }

    public void setProductDesigner(String productDesigner) {
        this.productDesigner = productDesigner;
    }

    @Column(name = "isgroup")
    public Integer getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Integer isGroup) {
        this.isGroup = isGroup;
    }

    @Column(name = "minnum")
    public Integer getMinnum() {
        return minnum;
    }

    public void setMinnum(Integer minnum) {
        this.minnum = minnum;
    }

    @Column(name = "groupprice")
    public Double getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(Double groupPrice) {
        this.groupPrice = groupPrice;
    }

    @Column(name = "backimage",length = 255)
    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    @Column(name = "ismodule")
    public Integer getIsModule() {
        return isModule;
    }

    public void setIsModule(Integer isModule) {
        this.isModule = isModule;
    }

    @Column(name = "pdt_pic_bp")
    public String getPdtPicBp() {
        return pdtPicBp;
    }

    public void setPdtPicBp(String pdtPicBp) {
        this.pdtPicBp = pdtPicBp;
    }

    @Column(name = "style", length = 33)
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Column(name = "designer_product_id")
    public String getDesigner_product_id() {
        return designer_product_id;
    }

    public void setDesigner_product_id(String designer_product_id) {
        this.designer_product_id = designer_product_id;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "premium")
    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id",insertable = false,updatable = false)
    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
    @Column(name = "img_back",length = 255)
    public String getImg_back() {
        return img_back;
    }

    public void setImg_back(String img_back) {
        this.img_back = img_back;
    }

    public Product longToShort() {
        try {
            File file;
            String path = "";
            if (StrKit.notBlank(getProductDesigner())){
                path =  PropertiesUtils.getValue("file.path") +"/productDesign" + "/";
                path = path + "/" + UUID.randomUUID().toString() + ".txt";
                file = new File(path);
                StringFileToolkit.string2File(getProductDesigner(), file);
                setProductDesigner(path);
            }else{
                throw  new RuntimeException("文本为空");
            }
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
    }
    public void shortToLong() {
        try {
            File file;
            if(StrKit.notBlank(getProductDesigner())){
                file =new File(getProductDesigner());
                if(file.exists()){
                    this.productDesigner=StringFileToolkit.file2String(file, "utf-8").replace("\r","").replace("\n","");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product buidImage(){

        String base64=getImageBase64();
        base64=URLDecoder.decode(base64);
        String path =  PropertiesUtils.getValue("file.path") +"/base64" + "/";
        Gson gson=new Gson();
        Base64Model base64Model = gson.fromJson(base64, Base64Model.class);
        String [] strs=base64Model.getaDesignImgsBase64();
        File file;
        String name;
        for ( int i=0;i<strs.length;i++){
            name=UUID.randomUUID().toString()+".png";
            if(StrKit.notBlank(strs[i])){
                file=new File(path+name);
                StringFileToolkit.GenerateImage(strs[i].split("[,]")[1],file);
            }
            if(i==0){
                this.setImg("/base64" + "/"+name);
            }else{
                this.setImg_back("/base64" + "/"+name);
            }
        }
        strs=base64Model.getaFullImgsBase64();
        for ( int i=0;i<strs.length;i++){
            name=UUID.randomUUID().toString()+".png";
            if(StrKit.notBlank(strs[i])){
                file=new File(path+name);
                StringFileToolkit.GenerateImage(strs[i].split("[,]")[1],file);
            }
            if(i==0){
                this.setShow("/base64" + "/"+name);
            }else{
                this.setBackImage("/base64" + "/"+name);
            }
        }
        this.setImageBase64("");
        return  this;

    }
}