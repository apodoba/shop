package com.apodoba.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.apodoba.domain.CartEntity;
import com.apodoba.domain.CategoryEntity;
import com.apodoba.domain.CommentEntity;
import com.apodoba.domain.GoodsEntity;
import com.apodoba.domain.ImageEntity;
import com.apodoba.domain.UserEntity;

@Repository
public class ShopDaoImpl implements ShopDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public ShopDaoImpl() {
        
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
    public CategoryEntity getCategoryById(Long id) {
        return (CategoryEntity) session().get(CategoryEntity.class, id);
    }

    public GoodsEntity getGoodsById(Long id) {
        return (GoodsEntity) session().get(GoodsEntity.class, id);
    }

    public UserEntity getUserById(Long id) {
        return (UserEntity) session().get(UserEntity.class, id);
    }

    public ImageEntity getImageById(Long id) {
        return (ImageEntity) session().get(ImageEntity.class, id);
    }

    public CartEntity getCartById(Long id) {
        return (CartEntity) session().get(CartEntity.class, id);
    }

    public CommentEntity getCommentById(Long id) {
        return (CommentEntity) session().get(CommentEntity.class, id);
    }

    public void saveOrUpdate(CategoryEntity category) {
        session().saveOrUpdate(category);
    }

    public void saveOrUpdate(GoodsEntity goods) {
        session().saveOrUpdate(goods);
    }

    public void saveOrUpdate(UserEntity user) {
        session().saveOrUpdate(user);

    }

    public void saveOrUpdate(ImageEntity image) {
        session().saveOrUpdate(image);

    }

    public void saveOrUpdate(CartEntity cart) {
        session().saveOrUpdate(cart);

    }

    public void saveOrUpdate(CommentEntity comment) {
        session().saveOrUpdate(comment);

    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getAllUsers() {
        return session().createCriteria(UserEntity.class).list();
    }

    public UserEntity getUserByEmail(String email) {
        return (UserEntity) session().createCriteria(UserEntity.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<CategoryEntity> getCategoriesWithParent(Long parentCategotyId) {
        Query query = session()
                .getNamedQuery(CategoryEntity.CATEGORIES_WITH_PARENT);
        query.setParameter("categoryId", parentCategotyId);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<CategoryEntity> getCategoriesWithoutParent() {
        return session().createCriteria(CategoryEntity.class)
                .add(Restrictions.isNull("parentCategory")).list();
    }

    @SuppressWarnings("unchecked")
    public List<CategoryEntity> getAllCategories() {
        return session().createCriteria(CategoryEntity.class).list();
    }

}
