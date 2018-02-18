package com.visu.align.ims.controller.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.visu.align.ims.entity.Product;
import org.springframework.hateoas.ResourceSupport;

public class ObjectWrapper extends ResourceSupport {

    private final Product content;

    @JsonCreator
    public ObjectWrapper(@JsonProperty("content") Product content) {
        this.content = content;
    }

    public Product getContent() {
        return content;
    }
}
