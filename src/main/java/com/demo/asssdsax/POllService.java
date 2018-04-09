package com.demo.asssdsax;


import com.demo.domain.PollOptions;
import com.demo.domain.PollQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class POllService {


    @Autowired
    PollQuestionRepository pollQuestionRepository;


    @Autowired
    PollOptionRepository pollOptionRepository;

    public PollQuestion savePoll(PollDTO pollDTO) {


        String Question = pollDTO.getPollQuestion().getQuestion();
        System.err.println("hello++++++++++++++++++++++" + Question);
        PollOptions pollOptions[] = pollDTO.getPollOptions();
        System.err.println("hello++++++++++++++++++++++" + pollOptions[2].getOptions());
        System.err.println("hello++++++++++++++++++++++" + pollOptions[1].getOptions());

        if (Question.equals(null) || Question.equals("")) {
            System.err.println("hello++++++ If++++++++++++++++");

            return null;
        } else if (pollQuestionRepository.findByQuestion(Question) == null && pollOptions != null) {
            System.err.println("hello++++++Else If++++++++++++++++" + pollOptions[1].getOptions());

            List<PollOptions> pollOptions1 = new ArrayList<>();
            for (PollOptions pollOption : pollOptions) {

                PollOptions pollOptions2 = new PollOptions();
                pollOptions2.setOptions(pollOption.getOptions());
                PollOptions save = pollOptionRepository.save(pollOptions2);
                pollOptions1.add(save);

            }
            PollQuestion pollQuestion = new PollQuestion();
            pollQuestion.setQuestion(Question);
            pollQuestion.setPollOptions(pollOptions1);
            pollQuestionRepository.save(pollQuestion);

            return pollQuestion;

        } else {
            System.err.println("hello++++++ else++++++++++++++++");

            return null;
        }
    }




    public PollQuestion getpoll(String poll)
    {


        PollQuestion pollQuestion=pollQuestionRepository.findByQuestion(poll);
        if (pollQuestion!=null)
        {
            return pollQuestion;

        }
return null;

    }




}
