package com.visu.align.ims.controller.wrapper;

import com.visu.align.ims.entity.Product;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * REST HATEOAS principles wrapper
 */
public interface WrapperService {

    /**
     * Create wrapper for list response
     *
     * @param products
     * @return object of type {@link ResourceSupport} with list of wrapped Products
     */
    ResourceSupport createWrappedList(List<Product> products);

    /**
     * Create wrapper for {@link Product}
     *
     * @param product
     * @return object of type {@link ResourceSupport} with wrapped Product
     */
    ResourceSupport createProductWrapper(Product product);

    /**
     * Create wrapper for common object
     *
     * @param responseEntity
     * @param content object to be wrapped
     * @param <T> type of object to be wrapped
     * @return object of type {@link ResourceSupport} with list of wrapped object
     */
    <T> ResourceSupport createWrapper(ResponseEntity responseEntity, T content);
}
