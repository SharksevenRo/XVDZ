package com.xiaov.model;

import com.xiaov.orm.core.Page;
import com.xiaov.web.support.CustomDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 业务员和设计师认证
 * Created by Sharkseven on 2016/7/12.
 */
@Table(name = "identification", catalog = "xvdz")
@Entity

public class Identification extends Page<Identification>{

    private String id;
    private String idCard;
    private String userId;
    private String name;
    private String phone;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private String type;

    public Identification() {
    }

    public Identification(String idCard, String userId, String name, String phone) {
        this.idCard = idCard;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    public Identification(String id, String idCard, String userId, String name, String phone) {
        this.id = id;
        this.idCard = idCard;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    @Id
    @Column(name = "authid", unique = true, nullable = true, length = 33)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name="idcard")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name="userid")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name = "add_time", nullable = true, length = 0)
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getAddTime() {
        return this.addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
