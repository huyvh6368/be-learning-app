package web.elearning.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.LearnerRequest;
import web.elearning.dto.response.LearnerResponse;
import web.elearning.mapper.LearnerMapper;
import web.elearning.model.Account;
import web.elearning.model.Learner;
import web.elearning.repository.AccountRepository;
import web.elearning.repository.LearnerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearnerService {
    private final LearnerRepository learnerRepository;
    private final AccountRepository accountRepository;

    public LearnerResponse update(LearnerRequest learnerRequest, Long id) {
        // find learner late
        Learner learnerFind = learnerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Learner not found"));
        Learner learner = LearnerMapper.updateToLearner(learnerRequest, learnerFind);
        Learner learnerUpdated = learnerRepository.save(learner);
        // find lai account to update account name
        Account account = accountRepository.findById(learnerUpdated.getAccount().getId()).orElseThrow(() -> new RuntimeException("account not found"));
        account.setName(learnerRequest.getName());
        accountRepository.save(account);
        return LearnerMapper.entityToResponse(learnerUpdated);
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
        Pageable pageable = PageRequest.of(page, size, Sort.by("totalScore").descending());
        Page<Learner> topicPage = learnerRepository.findAll(pageable);
        List<LearnerResponse> responseList = topicPage.getContent().stream()
                .map(LearnerMapper::entityToResponse)
                .toList();
        return new PageImpl<>(responseList, pageable, topicPage.getTotalElements());
    }
}
