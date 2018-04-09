package com.demo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class PollOptions {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String options;


    /*@ManyToOne
    private PollQuestion pollQuestion;
*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
/*
    public PollQuestion getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(PollQuestion pollQuestion) {
        this.pollQuestion = pollQuestion;
    }*/
}
