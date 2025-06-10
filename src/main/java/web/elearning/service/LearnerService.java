package web.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.LearnerRequest;
import web.elearning.dto.response.LearnerResponse;
import web.elearning.mapper.LearnerMapper;
import web.elearning.model.Learner;
import web.elearning.repository.LearnerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearnerService {
    private final LearnerRepository learnerRepository;

    public LearnerResponse update(LearnerRequest learnerRequest, Long id) {
        Learner learner = LearnerMapper.updateToLearner(learnerRequest, id);
        return LearnerMapper.entityToResponse(learnerRepository.save(learner));
    }

    public LearnerResponse getById(Long id) {
        return LearnerMapper
                .entityToResponse(learnerRepository
                        .findById(id).orElseThrow(() -> new RuntimeException("learner not found")));
    }

    public LearnerResponse findByAccountId(Long id) {
        Learner learner = learnerRepository.findByAccountId(id).orElseThrow(() -> new RuntimeException("learner not found"));
        return LearnerMapper.entityToResponse(learner);
    }

    public Page<LearnerResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Learner> topicPage = learnerRepository.findAll(pageable);
        List<LearnerResponse> responseList = topicPage.getContent().stream()
                .map(LearnerMapper::entityToResponse)
                .toList();
        return new PageImpl<>(responseList, pageable, topicPage.getTotalElements());
    }
}
