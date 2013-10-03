package com.apodoba.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "IMAGES")
public class ImageEntity implements Serializable {

    private static final long serialVersionUID = -985971366929729996L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq_gen")
    @SequenceGenerator(name = "image_seq_gen", sequenceName = "IMAGES_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "IMAGE")
    @Lob
    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOODS_ID", nullable = false)
    private GoodsEntity goods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null){
            return false;
        }
        if(this == other){
            return true;
        }
        if(other instanceof ImageEntity){
            ImageEntity otherImage = (ImageEntity) other;
            return new EqualsBuilder().
                    append(this.image, otherImage.image).
                    append(this.goods, otherImage.goods).
                    isEquals();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.image).
                append(this.goods).
                hashCode();
    }
}
