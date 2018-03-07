package com.visu.align.ims.util;

import com.visu.align.ims.model.Product;
import org.junit.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestUtil {

    private TestUtil() {}

    public static final String H2_CONNECTION_URL = "jdbc:h2:mem:test;DB_CLOSE_ON_EXIT=FALSE";
    public static final String H2_USER_NAME = "h2";
    public static final String H2_PASSWORD = "h2";

    public static final Product product1 = createTestProduct(BigInteger.ONE, "name1", "brand1", BigDecimal.ONE, 1);
    public static final Product product2 = createTestProduct(BigInteger.valueOf(2), "name1", "brand2", BigDecimal.valueOf(2), 2);
    public static final Product product3 = createTestProduct(BigInteger.valueOf(3), "name2", "brand2", BigDecimal.valueOf(10), 10);

    public static Product createTestProduct(BigInteger id, String name, String brand, BigDecimal price, int quantity) {
        Product product = new Product();

        product.setId(id);
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setQuantity(quantity);

        return product;
    }

    public static void assertProductContent(Product prod1, Product prod2) {
        Assert.assertEquals("Incorrect product name", prod1.getName(), prod2.getName());
        Assert.assertEquals("Incorrect product brand", prod1.getBrand(), prod2.getBrand());
        Assert.assertEquals("Incorrect product quantity", prod1.getQuantity(), prod2.getQuantity());
        Assert.assertTrue("Incorrect product price", prod1.getPrice().compareTo(prod2.getPrice()) == 0);
    }
}
