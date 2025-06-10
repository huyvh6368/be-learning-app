package web.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.RankRequest;
import web.elearning.dto.response.RankResponse;
import web.elearning.dto.response.TopicResponse;
import web.elearning.mapper.RankMapper;
import web.elearning.mapper.TopicMapper;
import web.elearning.model.Rank;
import web.elearning.model.Topic;
import web.elearning.repository.RankRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;

    public List<RankResponse> findAll() {
        return rankRepository.findAll().stream()
                .map(RankMapper::entityToResponse)
                .toList();
    }

    public Page<RankResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rank> topicPage = rankRepository.findAll(pageable);
        List<RankResponse> responseList = topicPage.getContent().stream()
                .map(RankMapper::entityToResponse)
                .toList();
        return new PageImpl<>(responseList, pageable, topicPage.getTotalElements());
    }

    public RankResponse findById(Long id) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("rank not found"));
        return RankMapper.entityToResponse(rank);
    }

    public RankResponse add(RankRequest rankRequest) {
        Rank rank = RankMapper.addRequestToEntity(rankRequest);
        return RankMapper.entityToResponse(rankRepository.save(rank));
    }

    public RankResponse update(RankRequest rankRequest, Long id) {
        rankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("rank not found"));
        Rank rank = RankMapper.updateRequestToEntity(rankRequest, id);
        return RankMapper.entityToResponse(rankRepository.save(rank));
    }
}
