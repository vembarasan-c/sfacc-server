package edu.lmsService.api.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.lmsService.api.model.dao.UserDAO;


public interface IUserRepository extends MongoRepository<UserDAO,String>{
    Optional<UserDAO> findByEmail(String email);
}
