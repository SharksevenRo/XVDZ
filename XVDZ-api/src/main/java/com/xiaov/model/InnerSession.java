package com.xiaov.model;

import com.xiaov.web.support.CustomDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sharkseven on 2016/7/8.
 */
@Entity
@Table(name = "innersession", catalog = "xvdz")

public class InnerSession {

    private String id;
    private String key;
    private String token;

    private Long time;
    private Long begin;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    public InnerSession() {
    }

    public InnerSession(String key, String token, Long time, Long begin) {

        this.key = key;
        this.token = token;
        this.time = time;
        this.begin = begin;
    }

    public InnerSession(Long begin, String key, String token, Long time, String id) {
        this.begin = begin;
        this.key = key;
        this.token = token;
        this.time = time;
        this.id = id;
    }

    @Id
    @Column(name = "innerid", unique = true, nullable = true, length = 33)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "token", nullable = true, length = 33)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @Column(name = "timelimit")
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
    @Column(name = "begin")
    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }
    @Column(name = "ikey")
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
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
