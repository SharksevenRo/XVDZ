package com.xiaov.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Sharkseven on 2016/7/8.
 */
@Entity
@Table(name = "innersession", catalog = "xvdz")
@JsonIgnoreProperties(value={ "hibernateLazyInitializer" })
public class InnerSession {

    private String id;
    private String key;
    private String token;

    private Long time;
    private Long begin;

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
}
