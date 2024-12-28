package edu.lmsService.api.service;


import edu.lmsService.api.model.dao.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

public interface IMediaService {

    void createBlog(Media media);

    Stack<Media> getAllBlogs();

    String storeFile(MultipartFile file) throws IOException;



}
