package com.apodoba.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "GOODS")
public class GoodsEntity implements Serializable {

    private static final long serialVersionUID = 2270726919362215659L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_seq_gen")
    @SequenceGenerator(name = "goods_seq_gen", sequenceName = "GOODS_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quintity;

    @Column(name = "MANUFACTURER", nullable = false)
    private String manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
    private Set<CartEntity> carts = new HashSet<CartEntity>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
    private Set<CommentEntity> comments = new HashSet<CommentEntity>();
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "goods")
    private ImageEntity image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Integer getQuintity() {
        return quintity;
    }

    public void setQuintity(Integer quintity) {
        this.quintity = quintity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts(Set<CartEntity> carts) {
        this.carts = carts;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }    
    
    
    @Override
    public boolean equals(Object other) {
        if(other == null){
            return false;
        }
        if(this == other){
            return true;
        }
        if(other instanceof GoodsEntity){
            GoodsEntity otherGoods = (GoodsEntity) other;
            return new EqualsBuilder().
                    append(this.category, otherGoods.category).
                    append(this.description, otherGoods.description).
                    append(this.manufacturer, otherGoods.manufacturer).
                    append(this.name, otherGoods.name).
                    append(this.price, otherGoods.price).
                    append(this.quintity, otherGoods.quintity).
                    isEquals();
        }
        return false;
    }
    
    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.description).
                append(this.category).
                append(this.manufacturer).
                append(this.name).
                append(this.price).
                append(this.quintity).
                hashCode();
    }
}
