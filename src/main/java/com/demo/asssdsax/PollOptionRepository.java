package com.demo.asssdsax;

import com.demo.domain.PollOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


public interface PollOptionRepository extends JpaRepository<PollOptions,Serializable>{
}
