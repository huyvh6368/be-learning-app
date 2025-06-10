package web.elearning.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.AnswerRequest;
import web.elearning.dto.response.AnswerResponse;
import web.elearning.mapper.AnswerMapper;
import web.elearning.model.Answer;
import web.elearning.model.Question;
import web.elearning.repository.AnswerRepository;
import web.elearning.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerResponse findById(Long id) {
        return AnswerMapper.entityToResponse(answerRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Answer not found with id " + id)));
    }

    public AnswerResponse add(AnswerRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + request.getQuestionId()));
        Answer answer = answerRepository.save(AnswerMapper.addRequestToEntity(request, question));
        return AnswerMapper.entityToResponse(answer);
    }

    public AnswerResponse update(AnswerRequest request, Long id) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + request.getQuestionId()));
        Answer answer = answerRepository.save(AnswerMapper.addRequestToEntity(request, question));
        return AnswerMapper.entityToResponse(answer);
    }

    public List<AnswerResponse> findAllByQuestionId(Long id) {
        return answerRepository.findAllByQuestionId(id).stream().map(AnswerMapper::entityToResponse).toList();
    }
}
