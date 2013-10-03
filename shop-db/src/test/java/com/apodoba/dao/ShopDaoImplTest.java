package com.apodoba.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.apodoba.domain.CartEntity;
import com.apodoba.domain.CategoryEntity;
import com.apodoba.domain.CommentEntity;
import com.apodoba.domain.GoodsEntity;
import com.apodoba.domain.ImageEntity;
import com.apodoba.domain.UserEntity;
import com.apodoba.domain.UserRole;

/**
 * Test class for DAO with integration test.
 * 
 * Remember that by default JUnit will not run test from class whose name
 * doesn't end with 'Test'.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@Transactional
// Force Session factory to open a new session and begin transaction
public class ShopDaoImplTest {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ShopDao shopDao;

	private void flushAndClearSession() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}

	@Test
	public void testNothing() {

	}

	@Test
	public void testFindGoods() {
		GoodsEntity goodsEntity = shopDao.getGoodsById(1L);
		Assert.assertNotNull(goodsEntity);
		Assert.assertEquals("Nokia 3310", goodsEntity.getName());
		CategoryEntity categoryEntity = shopDao.getCategoryById(3L);
		Assert.assertTrue(categoryEntity.getGoods().contains(goodsEntity));
	}

	@Test
	public void testPersistCategory() {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName("Test name");
		categoryEntity.setDescription("");
		shopDao.saveOrUpdate(categoryEntity);
		Long assignedId = categoryEntity.getId();

		flushAndClearSession();

		categoryEntity = shopDao.getCategoryById(assignedId);
		Assert.assertNotNull(categoryEntity);
		Assert.assertEquals(assignedId, categoryEntity.getId());
		Assert.assertEquals("Test name", categoryEntity.getName());

	}

	@Test
	public void testPersistGoods() {
		GoodsEntity goodsEntity = new GoodsEntity();
		goodsEntity.setName("Test goods name");
		goodsEntity.setDescription("Goods description");
		goodsEntity.setPrice(BigDecimal.valueOf(1100.0));
		goodsEntity.setQuintity(3);
		goodsEntity.setManufacturer("Test manufacturer");

		CategoryEntity categoryEntity = shopDao.getCategoryById(3L);
		goodsEntity.setCategory(categoryEntity);

		shopDao.saveOrUpdate(goodsEntity);
		Long assignedId = goodsEntity.getId();

		flushAndClearSession();

		goodsEntity = shopDao.getGoodsById(assignedId);
		Assert.assertNotNull(goodsEntity);
		Assert.assertEquals(assignedId, goodsEntity.getId());
		Assert.assertEquals("Test goods name", goodsEntity.getName());
	}

	@Test
	public void testFindCategory() {
		CategoryEntity categoryEntity = shopDao.getCategoryById(1L);
		Assert.assertNotNull(categoryEntity);
		Assert.assertEquals("Electronic", categoryEntity.getName());
		Assert.assertEquals(null, categoryEntity.getParentCategory());

		CategoryEntity childCategoryEntity = shopDao.getCategoryById(2L);
		Assert.assertNotNull(childCategoryEntity);
		Assert.assertEquals("Notebooks", childCategoryEntity.getName());
		Assert.assertEquals(categoryEntity,
				childCategoryEntity.getParentCategory());
	}

	@Test
	public void testFindUser() {
		UserEntity userEntity = shopDao.getUserById(1L);
		Assert.assertNotNull(userEntity);
		Assert.assertEquals("admin@shop.com", userEntity.getEmail());
		Assert.assertEquals(UserRole.ADMIN, userEntity.getUserRole());

		Assert.assertNotEquals(0, userEntity.getCarts().size());
	}

	@Test
	public void testPersistUser() {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("test@mail.ru");
		userEntity.setPassword("2222");
		userEntity.setFirstName("user");
		userEntity.setPhone("+35(066)2463454");
		userEntity.setUserRole(UserRole.CUSTOMER);

		shopDao.saveOrUpdate(userEntity);
		Long assignedId = userEntity.getId();

		flushAndClearSession();

		userEntity = shopDao.getUserById(assignedId);
		Assert.assertNotNull(userEntity);
		Assert.assertEquals(assignedId, userEntity.getId());
		Assert.assertEquals("test@mail.ru", userEntity.getEmail());
	}

	@Test
	public void testFindCart() {
		CartEntity cartEntity = shopDao.getCartById(1L);
		Assert.assertNotNull(cartEntity);
		UserEntity cartOwner = shopDao.getUserById(1L);
		Assert.assertTrue(cartOwner.getCarts().contains(cartEntity));

		GoodsEntity cartGoods = shopDao.getGoodsById(1L);
		Assert.assertTrue(cartGoods.getCarts().contains(cartEntity));
	}

	@Test
	public void testPersistCart() {
		CartEntity cartEntity = new CartEntity();

		UserEntity cartUserEntity = shopDao.getUserById(1L);
		cartEntity.setUser(cartUserEntity);

		GoodsEntity cartGoodsEntity = shopDao.getGoodsById(1L);
		cartEntity.setGoods(cartGoodsEntity);
		cartEntity.setQuintity(3);
		cartEntity.setAddress("Test address");
		cartEntity.setDescription("Test description");

		shopDao.saveOrUpdate(cartEntity);
		Long assignedId = cartEntity.getId();

		flushAndClearSession();

		cartEntity = shopDao.getCartById(assignedId);
		Assert.assertNotNull(cartEntity);
		Assert.assertEquals(assignedId, cartEntity.getId());
		Assert.assertEquals("Test address", cartEntity.getAddress());

	}

	@Test
	public void testFindImage() {
		ImageEntity imageEntity = shopDao.getImageById(1L);
		Assert.assertNotNull(imageEntity);

		GoodsEntity imageGoods = shopDao.getGoodsById(1L);
		Assert.assertEquals(imageGoods.getImage(), imageEntity);
	}

	@Test
	public void testPersistImage() {
		ImageEntity imageEntity = new ImageEntity();

		GoodsEntity imageGoods = shopDao.getGoodsById(2L);
		imageEntity.setGoods(imageGoods);
		shopDao.saveOrUpdate(imageEntity);
		Long assignedId = imageEntity.getId();

		flushAndClearSession();

		imageEntity = shopDao.getImageById(assignedId);
		imageGoods = shopDao.getGoodsById(2L);
		Assert.assertNotNull(imageEntity);
		Assert.assertEquals(assignedId, imageEntity.getId());
		Assert.assertEquals(imageGoods.getImage(), imageEntity);

	}

	@Test
	public void testFindComment() {
		CommentEntity commentEntity = shopDao.getCommentById(1L);
		Assert.assertNotNull(commentEntity);
		Assert.assertEquals("Vasya", commentEntity.getUserName());
		Assert.assertNotEquals(null, commentEntity.getGoods());

		GoodsEntity commentGoods = shopDao.getGoodsById(1L);
		Assert.assertNotNull(commentGoods);
		Assert.assertEquals("Nokia 3310", commentGoods.getName());
		Assert.assertTrue(commentGoods.getComments().contains(commentEntity));
	}

	@Test
	public void testPersistComment() {
		CommentEntity commentsEntity = new CommentEntity();
		commentsEntity.setUserName("Peter");
		commentsEntity.setText("Peters comments");

		GoodsEntity goodsEntity = shopDao.getGoodsById(1L);
		commentsEntity.setGoods(goodsEntity);
		commentsEntity.setDate(new Date());

		shopDao.saveOrUpdate(commentsEntity);
		Long assignedId = commentsEntity.getId();

		flushAndClearSession();

		commentsEntity = shopDao.getCommentById(assignedId);
		Assert.assertNotNull(commentsEntity);
		Assert.assertEquals(assignedId, commentsEntity.getId());
		Assert.assertEquals("Peter", commentsEntity.getUserName());

	}

	@Test
	public void testGetAllUsers() {
		List<UserEntity> entities = shopDao.getAllUsers();
		Assert.assertNotNull(entities);
		Assert.assertTrue("Shoul retriev at least one user",
				entities.size() > 0);
	}

	@Test
	public void testGetUserByEmail() {
		String email = "admin@shop.com";
		Long id = 1L;

		UserEntity entity = shopDao.getUserByEmail(email);
		Assert.assertNotNull(entity);
		Assert.assertEquals(id, entity.getId());
	}

	@Test
	public void testGetCategoriesWithParent() {
		List<CategoryEntity> childCategories = shopDao
				.getCategoriesWithParent(1L);
		Assert.assertNotNull(childCategories);
		Assert.assertTrue("Shoul retriev at least one category",
				childCategories.size() > 0);
	}
}
