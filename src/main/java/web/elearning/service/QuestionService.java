package web.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.QuestionRequest;
import web.elearning.dto.response.QuestionResponse;
import web.elearning.mapper.QuestionMapper;
import web.elearning.model.Answer;
import web.elearning.model.Question;
import web.elearning.model.Topic;
import web.elearning.repository.AnswerRepository;
import web.elearning.repository.QuestionRepository;
import web.elearning.repository.TopicRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private final AnswerRepository answerRepository;

    public QuestionResponse findById(Long id) {
        return QuestionMapper.entityToResponse(questionRepository.findById(id).orElseThrow(() ->
                new RuntimeException("question not found")));
    }

    public List<QuestionResponse> findAllByTopicId(Long id) {
        return questionRepository.findAllByTopicId(id).stream().map(QuestionMapper::entityToResponse).toList();
    }

    public Page<QuestionResponse> findAllByTopicId(Long topicId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "topicId"));
        Page<Question> questionPage = questionRepository.findAllByTopicId(topicId, pageable);
        return questionPage.map(QuestionMapper::entityToResponse);
    }


    public QuestionResponse add(QuestionRequest request) {
        Topic topic = topicRepository.findById(Long.parseLong(request.getTopicId())).orElseThrow(() -> new RuntimeException("topic not found"));
        Question question = questionRepository.save(QuestionMapper.addRequestToEntity(request, topic));
        return QuestionMapper.entityToResponse(question);
    }

    public QuestionResponse update(QuestionRequest request, Long id) {
        Topic topic = topicRepository.findById(Long.parseLong(request.getTopicId())).orElseThrow(() -> new RuntimeException("topic not found"));
        questionRepository.findById(id).orElseThrow(() -> new RuntimeException("question not found"));
//        Answer answer = answerRepository.findById(id).orElseThrow(() -> new RuntimeException("answer not found"));
        Question question = questionRepository.save(QuestionMapper.updateRequestToEntity(request, topic, id));
        return QuestionMapper.entityToResponse(question);
    }
}
