package web.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.LearnerTopicRequest;
import web.elearning.dto.response.LearnerTopicResponse;
import web.elearning.dto.response.TopicResponse;
import web.elearning.mapper.LearnerTopicMapper;
import web.elearning.mapper.TopicMapper;
import web.elearning.model.Learner;
import web.elearning.model.LearnerTopic;
import web.elearning.model.Topic;
import web.elearning.repository.LearnerRepository;
import web.elearning.repository.LearnerTopicRepository;
import web.elearning.repository.TopicRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LearnerTopicService {
    private final LearnerTopicRepository learnerTopicRepository;
    private final TopicRepository topicRepository;
    private final LearnerRepository learnerRepository;

    public void add(LearnerTopicRequest request) {
        Learner learner = learnerRepository.findById(request.getLearnerId()).orElseThrow(() -> new RuntimeException("learner not found"));
        Topic topic = topicRepository.findById(request.getTopicId()).orElseThrow(() -> new RuntimeException("topic not found"));
        LearnerTopic learnerTopic = new LearnerTopic();
        learnerTopic.setLearner(learner);
        learnerTopic.setTopic(topic);
        learnerTopicRepository.save(learnerTopic);
    }

    public Boolean existsByLearnerIdAndTopicId(Long learnerId, Long topicId) {
        return learnerTopicRepository.existsByLearnerIdAndTopicId(learnerId, topicId);
    }

    public List<TopicResponse> findAllTopicsByLearnerId(Long learnerId) {
        List<LearnerTopic> learnerTopics = learnerTopicRepository.findAllWithTopicByLearnerId(learnerId);
        return learnerTopics.stream()
                .map(lt -> TopicMapper.entityToResponse(lt.getTopic()))
                .collect(Collectors.toList());
    }

    public Page<LearnerTopicResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LearnerTopic> entities = learnerTopicRepository.findAll(pageable);
        return entities.map(LearnerTopicMapper::entityToResponse);
    }
}
