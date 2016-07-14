package com.xiaov.model;

import com.xiaov.orm.annotation.StateDelete;
import com.xiaov.orm.core.FieldType;
import com.xiaov.orm.core.Page;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 支付记录
 * Created by Sharkseven on 2016/7/15.
 */
@Entity
@Table(name = "payment", catalog = "xvdz")
@StateDelete(propertyName = "deleteFlag",type = FieldType.I,value="1")
public class Payment extends Page<Payment>{


    private String id;
    private String body;
    private String buyer_email;
    private String buyer_id;
    private String exterface;
    private String is_success;
    private String notify_id;
    private String notify_time;
    private String notify_type;
    private String payment_type;
    private String seller_email;
    private String seller_id;
    private String trade_no;
    private String trade_status;
    private String sign;
    private String sign_type;

    private Integer deleteFlag=0;

    public Payment(String id, String body, String buyer_email, String buyer_id, String exterface, String is_success, String notify_id, String notify_time, String notify_type, String payment_type, String seller_email, String seller_id, String trade_no, String trade_status, String sign, String sign_type) {
        this.id = id;
        this.body = body;
        this.buyer_email = buyer_email;
        this.buyer_id = buyer_id;
        this.exterface = exterface;
        this.is_success = is_success;
        this.notify_id = notify_id;
        this.notify_time = notify_time;
        this.notify_type = notify_type;
        this.payment_type = payment_type;
        this.seller_email = seller_email;
        this.seller_id = seller_id;
        this.trade_no = trade_no;
        this.trade_status = trade_status;
        this.sign = sign;
        this.sign_type = sign_type;
    }

    public Payment() {
    }

    public Payment(String sign_type, String body, String buyer_email, String buyer_id, String exterface, String is_success, String notify_id, String notify_time, String notify_type, String payment_type, String seller_email, String seller_id, String trade_no, String trade_status, String sign) {
        this.sign_type = sign_type;
        this.body = body;
        this.buyer_email = buyer_email;
        this.buyer_id = buyer_id;
        this.exterface = exterface;
        this.is_success = is_success;
        this.notify_id = notify_id;
        this.notify_time = notify_time;
        this.notify_type = notify_type;
        this.payment_type = payment_type;
        this.seller_email = seller_email;
        this.seller_id = seller_id;
        this.trade_no = trade_no;
        this.trade_status = trade_status;
        this.sign = sign;
    }
    @Id
    @Column(name = "payment_id", unique = true, nullable = true, length = 33)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name="body", length = 100)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Column(name="buyer_email", length = 100)
    public String getBuyer_email() {
        return buyer_email;
    }

    public void setBuyer_email(String buyer_email) {
        this.buyer_email = buyer_email;
    }
    @Column(name="buyer_id", length = 100)
    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }
    @Column(name="exterface", length = 100)
    public String getExterface() {
        return exterface;
    }

    public void setExterface(String exterface) {
        this.exterface = exterface;
    }
    @Column(name="is_success", length = 100)
    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }
    @Column(name="notify_id", length = 100)
    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }
    @Column(name="notify_time", length = 100)
    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }
    @Column(name="notify_type", length = 100)
    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }
    @Column(name="payment_type", length = 100)
    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
    @Column(name="seller_email", length = 100)
    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }
    @Column(name="seller_id", length = 100)
    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }
    @Column(name="trade_no", length = 100)
    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }
    @Column(name="trade_status", length = 100)
    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }
    @Column(name="sign", length = 100)
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    @Column(name="sign_type", length = 100)
    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    @Column(name="deleteFlag", length = 1)
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
