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
    public List<Product> getProductByName(String name) {
        return productDao.getProductByName(name);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productDao.getProductByBrand(brand);
    }

    @Override
    public List<Product> getLeftOvers() {
        return productDao.getLeftovers();
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }
}
