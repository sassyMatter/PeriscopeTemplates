package com.app.repository;



import com.app.models.test.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;




public interface UserRepository extends MongoRepository<User,String>{

    User save(User user);
    List<User> findAll();
}
