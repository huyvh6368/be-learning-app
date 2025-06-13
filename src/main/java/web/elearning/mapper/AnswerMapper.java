package web.elearning.mapper;

import web.elearning.dto.request.AnswerRequest;
import web.elearning.dto.response.AnswerResponse;
import web.elearning.model.Answer;
import web.elearning.model.Question;

public class AnswerMapper {
    public static Answer addRequestToEntity(AnswerRequest request, Question question) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setCorrect(request.getCorrect());
        answer.setName(request.getName());
        answer.setCode("test");
        return answer;
    }

    public static Answer updateRequestToEntity(AnswerRequest request, Question question, Long id) {
        Answer answer = new Answer();
        answer.setId(id);
        answer.setQuestion(question);
        answer.setName(request.getName());
        answer.setCorrect(request.getCorrect());
        answer.setCode("test");
        return answer;
    }

    public static AnswerResponse entityToResponse(Answer answer) {
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setId(answer.getId());
        answerResponse.setName(answer.getName());
        answerResponse.setCode(answer.getCode());
        answerResponse.setCorrect(answer.isCorrect());
        answerResponse.setQuestionId(answer.getQuestion().getId());
        answerResponse.setQuestionName(answer.getQuestion().getTitle());
        return answerResponse;
    }
}
