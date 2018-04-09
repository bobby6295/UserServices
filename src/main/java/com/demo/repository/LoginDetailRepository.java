package com.demo.repository;

import com.demo.domain.LoginDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface LoginDetailRepository extends JpaRepository<LoginDetail, Serializable> {
}
