package com.demo.repository;

import com.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface RoleRepository extends JpaRepository<Role, Serializable> {

    Role findByName(String name);

}
