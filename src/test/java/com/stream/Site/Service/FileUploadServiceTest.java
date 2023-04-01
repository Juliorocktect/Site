package com.stream.Site.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.stream.Site.Service.FileUploadService;
import static org.junit.jupiter.api.Assertions.*;
@Disabled
class FileUploadServiceTest {

    @Test
    void uploadFile() {
    }

    @Test
    void createNewDirectoryForVideoId() {
    }

    @Test
    void uploadThumbnail() {
    }

    @Test
    void getThumbnailUrl(String id) {
        String id = "6428095260595c4b2cae1c10";
        assertEquals(getThumbnailUrl(id),"http://localhost:8080/getThumbnail?id=6428095260595c4b2cae1c10");
    }

    @Test
    void getVideoUrl() {
    }

    @Test
    void renameFile() {
    }
}