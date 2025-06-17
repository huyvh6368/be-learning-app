package web.elearning.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.ProcessRequest;
import web.elearning.dto.response.ProcessResponse;
import web.elearning.mapper.ProcessMapper;
import web.elearning.model.Answer;
import web.elearning.model.Learner;
import web.elearning.model.Process;
import web.elearning.model.Question;
import web.elearning.repository.AnswerRepository;
import web.elearning.repository.LearnerRepository;
import web.elearning.repository.ProcessRepository;
import web.elearning.repository.QuestionRepository;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class ProcessService {
    private final LearnerRepository learnerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ProcessRepository processRepository;

    public void add(ProcessRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + request.getQuestionId()));
        Answer answer = answerRepository.findById(request.getAnswerId())
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with id " + request.getAnswerId()));
        Learner learner = learnerRepository.findById(request.getLearnerId())
                .orElseThrow(() -> new EntityNotFoundException("Learner not found with id " + request.getLearnerId()));

        if (Boolean.TRUE.equals(answer.getCorrect())) {
            //if answer is true
            BigDecimal questionScore = new BigDecimal(question.getScore());
            Process process = ProcessMapper.addToEntity(learner, question, answer, questionScore);
            processRepository.save(process);
            // update total score  for  learner
            BigDecimal totalScore = learner.getTotalScore().add(questionScore);
            learner.setTotalScore(totalScore);
            learnerRepository.save(learner);

        } else {
            //if answer is false
            BigDecimal questionScore = BigDecimal.ZERO;
            Process process = ProcessMapper.addToEntity(learner, question, answer, questionScore);
            processRepository.save(process);
            // update total score  for  learner
            BigDecimal totalScore = learner.getTotalScore().add(questionScore);
            learner.setTotalScore(totalScore);
            learnerRepository.save(learner);
        }

    }

    public Boolean check(Long learnerId, Long questionId) {
        return processRepository.existsByLearnerIdAndQuestionId(learnerId, questionId);
    }

    public ProcessResponse findScore(Long learnerId, Long questionId) {
        Process process = processRepository.findByLearnerIdAndQuestionId(learnerId, questionId);
        if (process == null) {
            throw new RuntimeException("Process not found with id " + learnerId + " and id " + questionId);
        }
        return ProcessMapper.entityToResponse(process);
    }

    public ProcessResponse findById(Long id) {
        Process process = processRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Process not found with id " + id));
        return ProcessMapper.entityToResponse(process);
    }
}
