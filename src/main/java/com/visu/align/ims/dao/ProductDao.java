package com.visu.align.ims.dao;

import com.visu.align.ims.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface ProductDao {

    Product getProductById(BigInteger id);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrand(String brand);

    List<Product> getLeftovers();

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);
}
