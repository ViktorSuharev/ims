package com.visu.align.ims.util;

public class TestQuery {

    public static String QUERY_INSERT_PRODUCT1_TEST_DATA =
            "INSERT INTO products \n" +
                    "   (id, name, brand, price, quantity)" +
                    "   VALUES ( 1, 'name1', 'brand1', 1.00, 1)";

    public static String QUERY_INSERT_PRODUCT2_TEST_DATA =
            "INSERT INTO products \n" +
                    "   (id, name, brand, price, quantity)" +
                    "   VALUES ( 2, 'name1', 'brand2', 2.00, 2)";

    public static String QUERY_INSERT_PRODUCT3_TEST_DATA =
            "INSERT INTO products \n" +
                    "   (id, name, brand, price, quantity)" +
                    "   VALUES ( 3, 'name2', 'brand2', 10.00, 10)";

    public static String DELETE_ALL_ROWS =
            "DELETE FROM products";

    private TestQuery() {}
}
