package com.apodoba.domain;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * 
 * @author arina
 *
 */

@Entity
@Table(name = "CATEGORIES")
@NamedQueries({
    @NamedQuery(name=CategoryEntity.CATEGORIES_WITH_PARENT,
                query="from CategoryEntity c where parentCategory.id = :categoryId")
})
public class CategoryEntity implements Serializable{

    public static final String CATEGORIES_WITH_PARENT =
             "com.apodoba.domain.CategoryEntity.CATEGORIES_WITH_PARENT";

    private static final long serialVersionUID = 7099088069719953260L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_seq_gen")
    @SequenceGenerator(name="category_seq_gen", sequenceName="CATEGORIES_SEQ")
    @Column(name="ID")
    private Long id;

    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PARENT_CATEGORY_ID")
    private CategoryEntity parentCategory;
    
    @Column(name="PARENT_CATEGORY_ID", insertable=false, updatable=false)
    private Long parentCategoryId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<GoodsEntity> goods = new HashSet<GoodsEntity>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }
    
    public Long getParentCategoryId() {
        return parentCategoryId;
    }
    
    public void setParentCategoryId(Long parentCategoryId) {
       this.parentCategoryId = parentCategoryId;
    }
    
    public Set<GoodsEntity> getGoods() {
        return goods;
    }

    public void setGoods(Set<GoodsEntity> goods) {
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
        if(other instanceof CategoryEntity){
            CategoryEntity otherCategory = (CategoryEntity) other;
            return new EqualsBuilder().
                    append(this.name, otherCategory.name).
                    append(this.description, otherCategory.description).
                   /* append(this.parentCategory.name, otherCategory.parentCategory.name).*/
                    isEquals();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.name).
                append(this.description).
                /*append(this.parentCategory.name).*/
                hashCode();
    }
    
}
