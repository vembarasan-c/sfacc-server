package edu.lmsService.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO {
    private String id;
    private String name;
    private String title;
    private String description;
    private String imageId;
    private String videoId;
    private String pdfId;

}
