package com.stream.Site;

import org.bson.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MongoConnectionTest {
    private static final String HOST = "27017";
    private static final String PORT = "27017";
    private static final String DB = "spring";

    @Test
    private void assertInsertSucceeds(ConfigurableApplicationContext context) {
        String name = "A";

        MongoTemplate mongo = context.getBean(MongoTemplate.class);
        Document doc = Document.parse("{\"name\":\"" + name + "\"}");
        Document inserted = mongo.insert(doc, "items");

        assertNotNull(inserted.get("_id"));
        assertEquals(inserted.get("name"), name);
    }

    @Test
    public void whenPropertiesConfig_thenInsertSucceeds() {
        SpringApplicationBuilder app = new SpringApplicationBuilder(SiteApplication.class);
        app.run();

        assertInsertSucceeds(app.context());
    }
}
