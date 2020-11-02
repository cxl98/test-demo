package com.cxl.order.dao;

import com.cxl.order.entry.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByName(String name);
}
