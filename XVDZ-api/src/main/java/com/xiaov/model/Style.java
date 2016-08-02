package com.xiaov.model;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sharkseven on 2016/7/13.
 */
@Entity
@Table(name = "Style", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag", type = FieldType.I, value = "1")

public class Style extends Page<Style> implements java.io.Serializable {

    private String id;
    private String catalog;

    private String editArea;

    private String thumb;

    private String title;

    private  String frontimg1;

    private String frontimg2;

    private String backimg1;

    private String backimg2;

    private String coordinate;

    private String description;

    private String pdtdesc;

    private Double price;

    private String clothingsex;

    private String clothingstyle;

    private Integer deleteFlag=0;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    public Style() {
    }

    public Style(String catalog, String editArea, String thumb, String title, String frontimg1, String frontimg2, String backimg1, String backimg2, String coordinate) {
        this.catalog = catalog;
        this.editArea = editArea;
        this.thumb = thumb;
        this.title = title;
        this.frontimg1 = frontimg1;
        this.frontimg2 = frontimg2;
        this.backimg1 = backimg1;
        this.backimg2 = backimg2;
        this.coordinate = coordinate;
    }

    public Style(String id, String catalog, String editArea, String thumb, String title, String frontimg1, String frontimg2, String backimg1, String backimg2, String coordinate) {
        this.id = id;
        this.catalog = catalog;
        this.editArea = editArea;
        this.thumb = thumb;
        this.title = title;
        this.frontimg1 = frontimg1;
        this.frontimg2 = frontimg2;
        this.backimg1 = backimg1;
        this.backimg2 = backimg2;
        this.coordinate = coordinate;
    }
    @Id
    @Column(name = "styleid", unique = true, nullable = true, length = 33)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name="catalog")
    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
    @Column(name="editarea")
    public String getEditArea() {
        return editArea;
    }

    public void setEditArea(String editArea) {
        this.editArea = editArea;
    }
    @Column(name="thumb")
    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }
    @Column(name="title")
    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name="frontimg1")
    public String getFrontimg1() {
        return frontimg1;
    }

    public void setFrontimg1(String frontimg1) {
        this.frontimg1 = frontimg1;
    }
    @Column(name="frontimg2")
    public String getFrontimg2() {
        return frontimg2;
    }

    public void setFrontimg2(String frontimg2) {
        this.frontimg2 = frontimg2;
    }
    @Column(name="backimg1")
    public String getBackimg1() {
        return backimg1;
    }

    public void setBackimg1(String backimg1) {
        this.backimg1 = backimg1;
    }
    @Column(name="backimg2")
    public String getBackimg2() {
        return backimg2;
    }

    public void setBackimg2(String backimg2) {
        this.backimg2 = backimg2;
    }

    @Column(name="coordinate")
    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Column(name="deleteflag")
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Column(name="price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name="pdtdesc")
    public String getPdtdesc() {
        return pdtdesc;
    }

    public void setPdtdesc(String pdtdesc) {
        this.pdtdesc = pdtdesc;
    }
    @Column(name = "add_time", nullable = true)
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getAddTime() {
        return this.addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    @Column(name="clothingsex")
    public String getClothingsex() {
        return clothingsex;
    }

    public void setClothingsex(String clothingsex) {
        this.clothingsex = clothingsex;
    }
    @Column(name="clothingstyle")
    public String getClothingstyle() {
        return clothingstyle;
    }

    public void setClothingstyle(String clothingstyle) {
        this.clothingstyle = clothingstyle;
    }
}
