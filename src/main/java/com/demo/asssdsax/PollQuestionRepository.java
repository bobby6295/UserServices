package com.demo.asssdsax;

import com.demo.domain.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;



public interface PollQuestionRepository extends JpaRepository<PollQuestion ,Serializable> {

    PollQuestion findByQuestion(String question);
}
