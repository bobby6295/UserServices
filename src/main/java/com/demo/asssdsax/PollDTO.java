package com.demo.asssdsax;

import com.demo.domain.PollOptions;
import com.demo.domain.PollQuestion;

public class PollDTO {


    private PollQuestion pollQuestion;

    private PollOptions[] pollOptions;

    public PollQuestion getPollQuestion() {
        return pollQuestion;
    }


    public PollOptions[] getPollOptions() {
        return pollOptions;
    }

}
