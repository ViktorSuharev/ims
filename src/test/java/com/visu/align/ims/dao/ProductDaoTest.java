package com.visu.align.ims.dao;

import com.visu.align.ims.model.Product;
import com.visu.align.ims.util.TestQuery;
import com.visu.align.ims.util.TestUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:data.sql")
public class ProductDaoTest {

    private Connection connection;
    private Statement statement;

    @Autowired
    private ProductDao productDao;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection(TestUtil.H2_CONNECTION_URL, TestUtil.H2_USER_NAME, TestUtil.H2_PASSWORD);
        statement = connection.createStatement();

        statement.executeUpdate(TestQuery.DELETE_ALL_ROWS);
        statement.executeUpdate(TestQuery.QUERY_INSERT_PRODUCT1_TEST_DATA);
        statement.executeUpdate(TestQuery.QUERY_INSERT_PRODUCT2_TEST_DATA);
        statement.executeUpdate(TestQuery.QUERY_INSERT_PRODUCT3_TEST_DATA);
    }

    @After
    public void tearDown() throws SQLException {
        statement.executeUpdate(TestQuery.DELETE_ALL_ROWS);
        statement.close();
        connection.close();
    }

    @Test
    public void testGetProductById() {
        Product product = productDao.findOne(BigInteger.ONE);
        Assert.assertNotNull("There is no product with specified id", product);

        Assert.assertEquals(TestUtil.product1, product);
    }

    @Test
    public void testGetProductsByName() {
        String NAME = "name1";
        List<Product> products = productDao.getProductsByName(NAME);
        Assert.assertEquals("Expected 2 products with specified name", 2, products.size());

        Assert.assertTrue(products.contains(TestUtil.product1));
        Assert.assertTrue(products.contains(TestUtil.product2));
    }

    @Test
    public void testGetProductsByBrand() {
        String BRAND = "brand2";
        List<Product> products = productDao.getProductsByBrand(BRAND);
        Assert.assertEquals("Expected 2 products with specified brand", 2, products.size());

        Assert.assertTrue(products.contains(TestUtil.product2));
        Assert.assertTrue(products.contains(TestUtil.product3));
    }

    @Test
    public void testGetLeftovers() {
        List<Product> products = productDao.getLeftovers(5);
        Assert.assertEquals("Expected 2 products with quantity less than 5", 2, products.size());

        Assert.assertTrue(products.contains(TestUtil.product1));
        Assert.assertTrue(products.contains(TestUtil.product2));
    }

    @Test
    @Transactional
    public void testAddProduct() {
        String NAME = "name";
        List<Product> products = productDao.getProductsByName(NAME);
        Assert.assertEquals("Expected no products with specified name", 0, products.size());

        Product productToBeAdded = TestUtil.createTestProduct(null, NAME, "brand", BigDecimal.TEN, 3);
        productDao.save(productToBeAdded);

        products = productDao.getProductsByName(NAME);
        Assert.assertEquals("Expected 1 product with specified name", 1, products.size());

        Product product = products.get(0);
        TestUtil.assertProductContent(product, productToBeAdded);
    }

    @Test
    @Transactional
    public void testUpdateProduct() {
        Product productToBeUpdated = productDao.findOne(BigInteger.ONE);
        Assert.assertEquals("Expected product with name = name1","name1", productToBeUpdated.getName());
        Product product = TestUtil.createTestProduct(BigInteger.ONE, "name", "brand", BigDecimal.TEN, 3);

        productDao.save(product);

        Product updatedProduct = productDao.findOne(BigInteger.ONE);
        TestUtil.assertProductContent(product, updatedProduct);
    }

    @Test
    @Transactional
    public void testDeleteProduct() {
        Product productToBeDeleted = TestUtil.createTestProduct(BigInteger.ONE, "name", "brand", BigDecimal.TEN, 3);
        BigInteger PRODUCT_ID = productToBeDeleted.getId();

        Product product = productDao.findOne(PRODUCT_ID);
        Assert.assertNotNull("There is no product with specified PRODUCT_ID", product);

        productDao.delete(product);

        product = productDao.findOne(PRODUCT_ID);
        Assert.assertNull("Product with specified PRODUCT_ID has not been deleted", product);
    }
}


