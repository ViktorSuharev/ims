package com.visu.align.ims.service;

import com.visu.align.ims.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductByName(String name);

    List<Product> getProductByBrand(String brand);

    List<Product> getLeftOvers();

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);
}
