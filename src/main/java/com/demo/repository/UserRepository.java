package com.demo.repository;

import com.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.io.Serializable;

public interface UserRepository extends JpaRepository<User, Serializable> {


    User findByEmail(String email);


    User findByMobileNo(String mobile);

    User findById(Long id);
}
