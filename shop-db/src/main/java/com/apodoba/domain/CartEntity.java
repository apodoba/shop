package com.apodoba.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "CART")
public class CartEntity implements Serializable{

    private static final long serialVersionUID = 7277249512413402291L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cart_seq_gen")
    @SequenceGenerator(name="cart_seq_gen", sequenceName="CART_SEQ")
    @Column(name="ID")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOODS_ID", nullable = false)
    private GoodsEntity goods;
    
    @Column(name="QUANTITY", nullable=false)
    private Integer quintity;
    
    @Column(name="ADDRESS")
    private String address;
    
    @Column(name="DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public Integer getQuintity() {
        return quintity;
    }

    public void setQuintity(Integer quintity) {
        this.quintity = quintity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (other instanceof CartEntity) {
            CartEntity otherCart = (CartEntity) other;
            return new EqualsBuilder()
                    .append(this.address, otherCart.address)
                    .append(this.description, otherCart.description)
                    .append(this.goods, otherCart.goods)
                    .append(this.quintity, otherCart.quintity)
                    .append(this.user, otherCart.user).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.address)
                .append(this.description).append(this.goods)
                .append(this.quintity).append(this.user).hashCode();
    }
}
