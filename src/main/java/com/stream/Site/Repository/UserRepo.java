package com.stream.Site.Repository;


import com.stream.Site.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepo extends MongoRepository<User, String> {

}

