package com.visu.align.ims.service;

import com.visu.align.ims.dao.ProductDao;
import com.visu.align.ims.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductByName(String name) {
        return null;
    }

    @Override
    public Product getProductByBrand(String brand) {
        return null;
    }

    @Override
    public List<Product> getLeftOvers() {
        return null;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }
}
