package com.visu.align.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SpringBootApplication
public class App {

	public static String QUERY_INSERT_PRODUCT1_TEST_DATA =
			"INSERT INTO products \n" +
					"   (id, name, brand, price, quantity)" +
					"   VALUES ( 1, 'prod1', 'brand1', 1, 1)";

	public static String QUERY_INSERT_PRODUCT2_TEST_DATA =
			"INSERT INTO products \n" +
					"   (id, name, brand, price, quantity)" +
					"   VALUES ( 2, 'prod2', 'brand2', 2, 10)";

	public static String QUERY_INSERT_PRODUCT3_TEST_DATA =
			"INSERT INTO products \n" +
					"   (id, name, brand, price, quantity)" +
					"   VALUES ( 3, 'prod3', 'brand3', 3, 100)";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
		createData();
	}

	private static void createData() throws Exception {
		String DB_CONNECTION_URL = "jdbc:h2:mem:test;DB_CLOSE_ON_EXIT=FALSE";

		Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, "h2", "h2");
		Statement stmt = connection.createStatement();

		stmt.executeUpdate(QUERY_INSERT_PRODUCT1_TEST_DATA);
		stmt.executeUpdate(QUERY_INSERT_PRODUCT2_TEST_DATA);
		stmt.executeUpdate(QUERY_INSERT_PRODUCT3_TEST_DATA);

		connection.close();
		stmt.close();
	}
}