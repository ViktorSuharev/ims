package com.visu.align.ims.controller.wrapper;

import com.visu.align.ims.entity.Product;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WrapperService {

    ResourceSupport createWrappedList(List<Product> products);

    ResourceSupport createProductWrapper(Product product);

    <T> ResourceSupport createWrapper(ResponseEntity responseEntity, T content);
}
