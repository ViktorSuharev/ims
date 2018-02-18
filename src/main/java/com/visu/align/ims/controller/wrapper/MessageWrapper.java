package com.visu.align.ims.controller.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class MessageWrapper extends ResourceSupport {
    private final String content;

    public MessageWrapper(@JsonProperty("content") String message) {
        this.content = message;
    }

    public String getContent() {
        return content;
    }
}
