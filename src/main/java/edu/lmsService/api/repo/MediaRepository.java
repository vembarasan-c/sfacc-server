package edu.lmsService.api.repo;

import edu.lmsService.api.model.dao.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaRepository extends MongoRepository<Media, String> {
}

