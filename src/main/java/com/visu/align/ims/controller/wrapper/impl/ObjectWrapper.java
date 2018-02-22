package com.visu.align.ims.controller.wrapper.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class ObjectWrapper<T> extends ResourceSupport {

    private final T content;

    @JsonCreator
    public ObjectWrapper(@JsonProperty("content") T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
