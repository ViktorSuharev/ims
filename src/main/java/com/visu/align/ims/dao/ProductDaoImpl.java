package com.visu.align.ims.dao;

import com.visu.align.ims.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    private static final String NAME_ATTR = "name";
    private static final String BRAND_ATTR = "brand";
    private static final String QUANTITY_ATTR = "quantity";

    private static final Integer LEFTOVER_THRESHOLD = 5;

    private static final String GET_PRODUCTS_BY_NAME_QUERY = "FROM Product as prod WHERE prod.name = :name";
    private static final String GET_PRODUCTS_BY_BRAND_QUERY = "FROM Product as prod WHERE prod.brand = :brand";
    private static final String GET_LEFTOVER_PRODUCTS_QUERY = "FROM Product as prod WHERE prod.quantity < :quantity";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product getProductById(BigInteger id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return entityManager.createQuery(GET_PRODUCTS_BY_NAME_QUERY)
                .setParameter(NAME_ATTR, name)
                .getResultList();
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return entityManager.createQuery(GET_PRODUCTS_BY_BRAND_QUERY)
                .setParameter(BRAND_ATTR, brand)
                .getResultList();
    }

    @Override
    public List<Product> getLeftovers() {
        return entityManager.createQuery(GET_LEFTOVER_PRODUCTS_QUERY)
                .setParameter(QUANTITY_ATTR, LEFTOVER_THRESHOLD)
                .getResultList();
    }

    @Override
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void deleteProduct(BigInteger id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
    }
}
