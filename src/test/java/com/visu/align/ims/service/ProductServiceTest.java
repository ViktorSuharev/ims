package com.visu.align.ims.service;

import com.visu.align.ims.dao.ProductDao;
import com.visu.align.ims.model.Product;
import com.visu.align.ims.util.TestQuery;
import com.visu.align.ims.util.TestUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    private Connection connection;
    private Statement statement;

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductDao productDao;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection(TestUtil.H2_CONNECTION_URL, TestUtil.H2_USER_NAME, TestUtil.H2_PASSWORD);
        statement = connection.createStatement();

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
        BDDMockito.given(productDao.findOne(Matchers.anyObject()))
                .willReturn(TestUtil.product1);

        Product product = productService.getProductById(BigInteger.ONE);
        Assert.assertNotNull("There is no product with specified id", product);

        Assert.assertEquals(TestUtil.product1, product);
    }

    @Test
    public void testGetProductsByName() {
        BDDMockito.given(productDao.getProductsByName(Matchers.anyString()))
                .willReturn(Arrays.asList(TestUtil.product1, TestUtil.product2));

        String PRODUCT_NAME = "name1";
        List<Product> products = productService.getProductsByName(PRODUCT_NAME);
        Assert.assertEquals("Expected 2 products with specified name", 2, products.size());

        Assert.assertTrue(products.contains(TestUtil.product1));
        Assert.assertTrue(products.contains(TestUtil.product2));
    }

    @Test
    public void testGetProductsByBrand() {
        BDDMockito.given(productDao.getProductsByBrand(Matchers.anyString()))
                .willReturn(Arrays.asList(TestUtil.product2, TestUtil.product3));

        String PRODUCT_BRAND = "brand2";
        List<Product> products = productService.getProductsByBrand(PRODUCT_BRAND);
        Assert.assertEquals("Expected 2 products with specified brand", 2, products.size());

        Assert.assertTrue(products.contains(TestUtil.product2));
        Assert.assertTrue(products.contains(TestUtil.product3));
    }

    @Test
    public void testGetLeftovers() {
        BDDMockito.given(productDao.getLeftovers(5))
                .willReturn(Arrays.asList(TestUtil.product1, TestUtil.product3));

        List<Product> products = productService.getLeftovers();
        Assert.assertEquals("Expected 2 products with quantity less than 5", 2, products.size());

        Assert.assertTrue(products.contains(TestUtil.product1));
        Assert.assertTrue(products.contains(TestUtil.product3));
    }

    @Test
    public void testAddProduct() {
        productDao = Mockito.mock(ProductDao.class);
        Mockito.doCallRealMethod().when(productDao).save(Matchers.any(Product.class));

        String PRODUCT_NAME = "name";
        List<Product> products = productService.getProductsByName(PRODUCT_NAME);
        Assert.assertEquals("Expected no products with specified name", 0, products.size());

        Product productToBeAdded = TestUtil.createTestProduct(null, PRODUCT_NAME, "brand", BigDecimal.TEN, 3);
        productService.addProduct(productToBeAdded);

        products = productService.getProductsByName(PRODUCT_NAME);
        Assert.assertEquals("Expected 1 product with specified name", 1, products.size());

        Product product = products.get(0);
        TestUtil.assertProductContent(product, productToBeAdded);

        Mockito.verify(productDao, Mockito.times(1)).save(productToBeAdded);
    }

    @Test
    public void testUpdateProduct() {
        Product productToBeUpdated = productService.getProductById(BigInteger.ONE);
        Assert.assertEquals("Expected product with name = name1","name1", productToBeUpdated.getName());
        Product product = TestUtil.createTestProduct(null, "name", "brand", BigDecimal.TEN, 3);

        productService.updateProduct(BigInteger.ONE, product);

        Product updatedProduct = productService.getProductById(BigInteger.ONE);
        TestUtil.assertProductContent(product, updatedProduct);
    }

    @Test
    public void testDeleteProduct() {
        BigInteger PRODUCT_ID = BigInteger.ONE;
        Product product = productService.getProductById(PRODUCT_ID);
        Assert.assertNotNull("There is no product with specified ID", product);

        productService.deleteProduct(PRODUCT_ID);

        product = productService.getProductById(PRODUCT_ID);
        Assert.assertNull("Product with specified ID has not been deleted", product);
    }
}
