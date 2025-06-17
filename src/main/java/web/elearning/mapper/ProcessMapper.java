package web.elearning.mapper;

import web.elearning.dto.response.ProcessResponse;
import web.elearning.model.Answer;
import web.elearning.model.Learner;
import web.elearning.model.Process;
import web.elearning.model.Question;

import java.math.BigDecimal;

public class ProcessMapper {
    public static Process addToEntity(Learner learner, Question question, Answer answer, BigDecimal score) {
        Process process = new Process();
        process.setAnswer(answer);
        process.setQuestion(question);
        process.setLearner(learner);
        process.setScore(score);
        return process;
    }

    public static ProcessResponse entityToResponse(Process process) {
        ProcessResponse response = new ProcessResponse();
        response.setId(process.getId());
        response.setLearnerId(String.valueOf(process.getLearner().getId()));
        response.setLearnerName(process.getLearner().getName());
        response.setQuestionId(String.valueOf(process.getQuestion().getId()));
        response.setQuestionName(process.getQuestion().getTitle());
        response.setScore(process.getScore());
        response.setAnswerId(String.valueOf(process.getAnswer().getId()));
        response.setAnswerName(process.getAnswer().getName());
        return response;
    }
}
