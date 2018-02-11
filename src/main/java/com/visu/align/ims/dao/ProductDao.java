package com.visu.align.ims.dao;

import com.visu.align.ims.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProductByName(String name);

    List<Product> getProductByBrand(String brand);

    List<Product> getLeftovers();

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);
}
