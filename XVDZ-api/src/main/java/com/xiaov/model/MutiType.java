package com.xiaov.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Sharkseven on 2016/7/9.
 * 多类型限定表
 */
@Entity
@Table(name = "mutitype", catalog = "xvdz")
public class MutiType {

    private String id;
    private String userId;
    private String productId;
    private Types type;

    public MutiType(String userId, String productId, Types type) {
        this.userId = userId;
        this.productId = productId;
        this.type = type;
    }

    public MutiType() {
    }
    @Id
    @Column(name = "mtypeid", unique = true, nullable = true, length = 33)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="userid")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name="productid")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeid")
    public Types getType() {
        return type;
    }

    public void setType(Types typeId) {
        this.type = typeId;
    }
}
