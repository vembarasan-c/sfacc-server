package edu.lmsService.api.serviceimpl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import edu.lmsService.api.model.dao.Media;
import edu.lmsService.api.repo.MediaRepository;
import edu.lmsService.api.service.IMediaService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Stack;

@Service
public class MediaService implements IMediaService {


    @Autowired
    private MediaRepository repository;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        GridFSUploadOptions options = new GridFSUploadOptions()
                .chunkSizeBytes(1024 * 1024)
                .metadata(new org.bson.Document("type", file.getContentType()));

        ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), inputStream, options);
        return fileId.toHexString();
    }

    @Override
    public void createBlog(Media media) {
        repository.save(media);
    }

    @Override
    public Stack<Media> getAllBlogs() {
        List<Media> blogs = repository.findAll();


        Stack<Media> stack = new Stack<>();

        for (Media m : blogs){
            System.out.println(m.getName());
            stack.push(m);
        }


        return stack;
    }
}

