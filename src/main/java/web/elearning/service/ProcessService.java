package web.elearning.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.ProcessRequest;
import web.elearning.dto.response.ProcessResponse;
import web.elearning.mapper.ProcessMapper;
import web.elearning.model.*;
import web.elearning.model.Process;
import web.elearning.repository.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessService {
    private final LearnerRepository learnerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ProcessRepository processRepository;
    private final RankRepository rankRepository;

    public void add(ProcessRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id " + request.getQuestionId()));
        Answer answer = answerRepository.findById(request.getAnswerId())
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with id " + request.getAnswerId()));
        Learner learner = learnerRepository.findById(request.getLearnerId())
                .orElseThrow(() -> new EntityNotFoundException("Learner not found with id " + request.getLearnerId()));
        List<Rank> rankList = rankRepository.findAll();
        if (Boolean.TRUE.equals(answer.getCorrect())) {
            //if answer is true
            BigDecimal questionScore = new BigDecimal(question.getScore());
            Process process = ProcessMapper.addToEntity(learner, question, answer, questionScore);
            processRepository.save(process);

            // update total score for learner
            BigDecimal totalScore = learner.getTotalScore().add(questionScore);
            learner.setTotalScore(totalScore);
            // check rank
            for (Rank rank : rankList) {
                if (totalScore.compareTo(rank.getScore()) >= 0) {
                    learner.setRank(rank);
                }
            }
            learnerRepository.save(learner);

        } else {
            //if answer is false
            BigDecimal questionScore = BigDecimal.ZERO;
            Process process = ProcessMapper.addToEntity(learner, question, answer, questionScore);
            processRepository.save(process);
            // update total score  for  learner
            BigDecimal totalScore = learner.getTotalScore().add(questionScore);
            learner.setTotalScore(totalScore);
            for (Rank rank : rankList) {
                if (totalScore.compareTo(rank.getScore()) >= 0) {
                    learner.setRank(rank);
                }
            }
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
