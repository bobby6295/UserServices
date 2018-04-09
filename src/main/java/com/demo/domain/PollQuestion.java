package com.demo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class PollQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;


    @OneToMany
    private List<PollOptions> pollOptions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<PollOptions> getPollOptions() {
        return pollOptions;
    }

    public void setPollOptions(List<PollOptions> pollOptions) {
        this.pollOptions = pollOptions;
    }

   }
