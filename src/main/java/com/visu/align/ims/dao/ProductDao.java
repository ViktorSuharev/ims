package com.visu.align.ims.dao;

import com.visu.align.ims.entity.Product;

import java.util.List;

public interface ProductDao {
    Product getProductByName(String name);

    Product getProductByBrand(String brand);

    List<Product> getLeftOvers();

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);
}
