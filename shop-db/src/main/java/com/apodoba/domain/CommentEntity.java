package com.apodoba.domain;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "COMMENTS")
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 3610467518227293347L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_gen")
    @SequenceGenerator(name = "comment_seq_gen", sequenceName = "COMMENTS_SEQ")
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOODS_ID", nullable = false)
    private GoodsEntity goods;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "COMMENT_TEXT")
    private String text;
    
    @Column(name = "DATE_POSTED")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null){
            return false;
        }
        if(this == other){
            return true;
        }
        if(other instanceof CommentEntity){
            CommentEntity otherComment = (CommentEntity) other;
            return new EqualsBuilder().
                    append(this.date, otherComment.date).
                    append(this.text, otherComment.text).
                    append(this.goods, otherComment.goods).
                    append(this.userName, otherComment.userName).
                    isEquals();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.date).
                append(this.text).
                append(this.goods).
                append(this.userName).
                hashCode();
    }
        
}
