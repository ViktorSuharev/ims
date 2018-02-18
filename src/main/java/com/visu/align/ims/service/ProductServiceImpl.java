package com.visu.align.ims.service;

import com.visu.align.ims.dao.ProductDao;
import com.visu.align.ims.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

// TODO: 15.02.2018 Transactional or not?
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public Product getProductById(BigInteger id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productDao.getProductsByName(name);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productDao.getProductsByBrand(brand);
    }

    @Override
    public List<Product> getLeftovers() {
        return productDao.getLeftovers();
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void updateProduct(BigInteger id, Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(BigInteger id) {
        productDao.deleteProduct(id);
    }
}
