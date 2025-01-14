package com.aadi.chatroomws.repository;

import com.aadi.chatroomws.model.Status;
import com.aadi.chatroomws.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}