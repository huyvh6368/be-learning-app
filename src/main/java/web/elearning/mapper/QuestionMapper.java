package web.elearning.mapper;

import web.elearning.dto.request.QuestionRequest;
import web.elearning.dto.response.QuestionResponse;
import web.elearning.model.Answer;
import web.elearning.model.Question;
import web.elearning.model.Topic;

public class QuestionMapper {
    public static Question addRequestToEntity(QuestionRequest request, Topic topic) {
        Question question = new Question();
        question.setCode(request.getCode());
        question.setTitle(request.getTitle());
        question.setImgUrl(request.getImgUrl());
        question.setDescribes(request.getDescribes());
        question.setScore(Integer.parseInt(request.getScore()));
        question.setTopic(topic);
        return question;
    }

    public static Question updateRequestToEntity(QuestionRequest request, Topic topic, Long id) {
        Question question = new Question();
        question.setId(id);
        question.setCode(request.getCode());
        question.setTitle(request.getTitle());
        question.setScore(Integer.parseInt(request.getScore()));
        question.setImgUrl(request.getImgUrl());
        question.setTopic(topic);
//        question.setCorrectAnswer(answer);
        question.setDescribes(request.getDescribes());
        return question;
    }

    public static QuestionResponse entityToResponse(Question entity) {
        QuestionResponse response = new QuestionResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setTitle(entity.getTitle());
        response.setScore(entity.getScore());
        response.setImgUrl(entity.getImgUrl());
        response.setDescribes(entity.getDescribes());
        response.setTopicId(entity.getTopic().getId());
        response.setTopicName(entity.getTopic().getName());
//        if (entity.getCorrectAnswer() != null) {
//            response.setAnswerId(entity.getCorrectAnswer().getId());
//            response.setAnswerName(entity.getCorrectAnswer().getName());
//        }
        return response;
    }
}
