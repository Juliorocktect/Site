package com.stream.Site.Repository;

import com.stream.Site.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserRepo extends MongoRepository<User, String> {

}
