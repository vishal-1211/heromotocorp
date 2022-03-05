package com.heromotocorp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.heromotocorp.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

}
