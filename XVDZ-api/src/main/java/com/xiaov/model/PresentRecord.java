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

/**体现记录
 * Created by Sharkseven on 2016/7/17.
 */
@Entity
@Table(name = "present_record", catalog = "xvdz")

public class PresentRecord extends Page<PresentRecord>{


    private String id;
    private Double amount;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date present_time;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date arrival_time;
    private Integer state;

    private String userId;

    private String  receiver_account;
    private String  account_type;

    public PresentRecord() {
    }

    public PresentRecord(String id, Double amount, Date present_time, Integer state) {
        this.id = id;
        this.amount = amount;
        this.present_time = present_time;
        this.state = state;
    }
    @Id
    @Column(name = "present_id", unique = true, nullable = true, length = 33)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name="present_time")
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getPresent_time() {
        return present_time;
    }

    public void setPresent_time(Date present_time) {
        this.present_time = present_time;
    }

    @Column(name="arrival_time")
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getArrival_time() {
        return arrival_time;
    }
    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }

    @Column(name="state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    @Column(name="userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name="receiver_account")
    public String getReceiver_account() {
        return receiver_account;
    }

    public void setReceiver_account(String receiver_account) {
        this.receiver_account = receiver_account;
    }
    @Column(name="account_type")
    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
