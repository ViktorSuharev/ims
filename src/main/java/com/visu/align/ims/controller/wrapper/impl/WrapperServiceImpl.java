package com.visu.align.ims.controller.wrapper.impl;

import com.visu.align.ims.controller.ProductServiceRestController;
import com.visu.align.ims.controller.wrapper.WrapperService;
import com.visu.align.ims.entity.Product;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class WrapperServiceImpl implements WrapperService {

    public ResourceSupport createWrappedList(List<Product> products) {
        if (products.isEmpty()) {
            return null;
        }

        List<ResourceSupport> listOfWrappedObj = new ArrayList<>();
        for (Product product : products) {
            ResourceSupport objWrapper = createProductWrapper(product);
            listOfWrappedObj.add(objWrapper);
        }

        ResponseEntity responseEntity = methodOn(ProductServiceRestController.class).getLeftovers();

        return createWrapper(responseEntity, listOfWrappedObj);
    }

    public ResourceSupport createProductWrapper(Product product) {
        if (product == null) {
            return null;
        }

        ResponseEntity responseEntity = methodOn(ProductServiceRestController.class).getProductById(product.getId());

        return createWrapper(responseEntity, product);
    }

    public <T> ResourceSupport createWrapper(ResponseEntity responseEntity, T content) {
        if (content == null) {
            return null;
        }

        ObjectWrapper wrapper = new ObjectWrapper<>(content);
        Link selfLink = linkTo(responseEntity).withSelfRel();
        wrapper.add(selfLink);

        return wrapper;
    }
}
