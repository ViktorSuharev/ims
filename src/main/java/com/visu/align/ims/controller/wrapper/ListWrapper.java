package com.visu.align.ims.controller.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class ListWrapper extends ResourceSupport {
    private final List<ObjectWrapper> content;

    @JsonCreator
    public ListWrapper(@JsonProperty("content") List<ObjectWrapper> content) {
        this.content = content;
    }

    public List<ObjectWrapper> getContent() {
        return content;
    }
}
