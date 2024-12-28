package edu.lmsService.api.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "media")
public class Media {
    @Id
    private String id;
    private String name;
    private String description;
    private String title;
    private String imageId;
    private String videoId; // Reference to the video file in GridFS
    private String pdfId;

}

