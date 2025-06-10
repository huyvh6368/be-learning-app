package web.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.TopicRequest;
import web.elearning.dto.response.TopicResponse;
import web.elearning.mapper.TopicMapper;
import web.elearning.model.Level;
import web.elearning.model.Topic;
import web.elearning.repository.LevelRepository;
import web.elearning.repository.TopicRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final LevelRepository levelRepository;

    public List<TopicResponse> findAll() {
        return topicRepository.findAll().stream()
                .map(TopicMapper::entityToResponse)
                .toList();
    }


    public List<TopicResponse> findAllByLevelId(Long levelId) {
        return topicRepository.findAllByLevelId(levelId).stream()
                .map(TopicMapper::entityToResponse)
                .toList();
    }

    public Page<TopicResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Topic> topicPage = topicRepository.findAll(pageable);
        List<TopicResponse> responseList = topicPage.getContent().stream()
                .map(TopicMapper::entityToResponse)
                .toList();
        return new PageImpl<>(responseList, pageable, topicPage.getTotalElements());
    }

    public Page<TopicResponse> findAllByLevelId(Integer page, Integer size, Long levelId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Topic> topicPage = topicRepository.findAllByLevelId(levelId, pageable);
        return topicPage.map(TopicMapper::entityToResponse);
    }


    public TopicResponse findById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("topic not found"));
        return TopicMapper.entityToResponse(topic);
    }

    public TopicResponse add(TopicRequest topicRequest) {
        Level level = levelRepository.findById(topicRequest.getLevelId()).orElseThrow(() -> new RuntimeException("level not found"));
        Topic topic = TopicMapper.addRequestToEntity(topicRequest, level);
        return TopicMapper.entityToResponse(topicRepository.save(topic));
    }

    public TopicResponse update(TopicRequest topicRequest, Long id) {
        Level level = levelRepository.findById(topicRequest.getLevelId()).orElseThrow(() -> new RuntimeException("level not found"));
        topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("topic not found"));
        Topic topic = TopicMapper.updateRequestToEntity(topicRequest, level, id);
        return TopicMapper.entityToResponse(topicRepository.save(topic));
    }
}

