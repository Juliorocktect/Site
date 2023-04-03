package com.stream.Site.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Content {
    private String name;
    private String type;
    private long bytes;

    public Content(String name, String type, long bytes) {
        this.name = name;
        this.type = type;
        this.bytes = bytes;
    }
}
