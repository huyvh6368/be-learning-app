package web.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.LearnerLevelRequest;
import web.elearning.dto.response.LearnerLevelResponse;
import web.elearning.mapper.LearnerLevelMapper;
import web.elearning.model.Learner;
import web.elearning.model.LearnerLevel;
import web.elearning.model.Level;
import web.elearning.repository.LearnerLevelRepository;
import web.elearning.repository.LearnerRepository;
import web.elearning.repository.LevelRepository;
import web.elearning.utils.LearnerLevelStatus;

@Service
@RequiredArgsConstructor
public class LearnerLevelService {
    private final LearnerLevelRepository learnerLevelRepository;
    private final LearnerRepository learnerRepository;
    private final LevelRepository levelRepository;

    public void add(LearnerLevelRequest request) {
        Learner learner = learnerRepository.findById(request.getLearnerId())
                .orElseThrow(() -> new RuntimeException("Learner not found"));
        Level level = levelRepository.findById(request.getLevelId())
                .orElseThrow(() -> new RuntimeException("Level not found"));
        String status = LearnerLevelStatus.DANG_HOC;
        LearnerLevel learnerLevel = LearnerLevelMapper.requestToEntity(request, learner, level, status);
        learnerLevelRepository.save(learnerLevel);
    }

    public Boolean checkLearnerLevel(Long learnerId, Long levelId) {
        return learnerLevelRepository.existsByLearnerIdAndLevelId(learnerId, levelId);
    }

    public Page<LearnerLevelResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LearnerLevel> entities = learnerLevelRepository.findAll(pageable);
        return entities.map(LearnerLevelMapper::entityToResponse);
    }
}
