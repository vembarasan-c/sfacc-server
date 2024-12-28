package edu.lmsService.api.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.lmsService.api.model.dao.MemberDAO;

public interface IMemberRepository extends MongoRepository<MemberDAO, String>  {
     Optional<MemberDAO> findByEmail(String email);
}
