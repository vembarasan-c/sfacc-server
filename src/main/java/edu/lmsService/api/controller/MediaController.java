package edu.lmsService.api.controller;

import edu.lmsService.api.model.dao.Media;
import edu.lmsService.api.service.IMediaService;
import edu.lmsService.api.serviceimpl.MediaService;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/api/media")
public class MediaController {


    @Autowired
    private GridFSBucket gridFSBucket;
    private IMediaService mediaService;

    public MediaController(IMediaService mediaService ){
        this.mediaService = mediaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMedia(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("title") String title,
            @RequestParam("image") MultipartFile image,
            @RequestParam("video") MultipartFile video,
            @RequestParam("pdf") MultipartFile pdf ) {
        try {
            String imageId = mediaService.storeFile(image);
            String videoId = mediaService.storeFile(video);
            String pdfId = mediaService.storeFile(pdf);

            Media media = new Media();
            media.setName(name);
            media.setDescription(description);
            media.setTitle(title);
            media.setImageId(imageId);
            media.setVideoId(videoId);
            media.setPdfId(pdfId);

            mediaService.createBlog(media);
//            mediaRepository.save(media);

            return ResponseEntity.status(HttpStatus.CREATED).body("Media uploaded successfully with ID: " + media.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading media");
        }
    }

    @GetMapping("/getAll")
    public Stack<Media> getAll(){

        Stack<Media> s =  mediaService.getAllBlogs();


//        List<Media> mediaList = mediaService.getAllBlogs() ;
        return s;
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileId) {
        try {
            // Find the file in GridFS
            GridFSFile gridFSFile = gridFSBucket.find(new org.bson.Document("_id", new org.bson.types.ObjectId(fileId))).first();
            if (gridFSFile == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Stream the file content
            GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            byte[] fileContent = downloadStream.readAllBytes();
            downloadStream.close();

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", gridFSFile.getMetadata().getString("type"));
            headers.add("Content-Disposition", "attachment; filename=\"" + gridFSFile.getFilename() + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}

