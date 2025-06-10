package web.elearning.mapper;

import web.elearning.dto.request.QuestionRequest;
import web.elearning.dto.response.QuestionResponse;
import web.elearning.model.Level;
import web.elearning.model.Question;
import web.elearning.model.Topic;

public class QuestionMapper {
    public static Question addRequestToEntity(QuestionRequest request, Topic topic) {
        Question question = new Question();
        question.setCode(request.getCode());
        question.setTitle(request.getTitle());
        question.setImgUrl(request.getImgUrl());
        question.setDescribes(request.getDescribes());
        question.setScore(request.getScore());
        question.setTopic(topic);
        return question;
    }

    public static Question updateRequestToEntity(QuestionRequest request, Topic topic, Long id) {
        Question question = new Question();
        question.setId(id);
        question.setCode(request.getCode());
        question.setTitle(request.getTitle());
        question.setImgUrl(request.getImgUrl());
        question.setTopic(topic);
        question.setDescribes(request.getDescribes());
        return question;
    }

    public static QuestionResponse entityToResponse(Question question) {
        QuestionResponse response = new QuestionResponse();
        response.setCode(question.getCode());
        response.setTitle(question.getTitle());
        question.setScore(question.getScore());
        response.setImgUrl(question.getImgUrl());
        response.setDescribes(question.getDescribes());
        response.setTopicId(question.getTopic().getId());
        response.setTopicName(question.getTopic().getName());
        response.setAnswerId(question.getCorrectAnswer().getId());
        response.setAnswerName(question.getCorrectAnswer().getName());
        return response;
    }
}
