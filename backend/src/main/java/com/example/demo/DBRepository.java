package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DBRepository extends MongoRepository<ToDo, String> {


    List<ToDo> findAllByUserId(String userId);
}
