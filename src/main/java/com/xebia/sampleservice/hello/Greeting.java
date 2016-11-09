package com.xebia.sampleservice.hello;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {
    private long id;

    private String content;

    private Greeting() {
        // Jackson deserialization
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
