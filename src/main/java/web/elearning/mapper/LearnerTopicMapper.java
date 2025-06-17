package web.elearning.mapper;

import web.elearning.dto.response.LearnerTopicResponse;
import web.elearning.model.LearnerTopic;

public class LearnerTopicMapper {
    public static LearnerTopicResponse entityToResponse (LearnerTopic entity) {
        LearnerTopicResponse response = new LearnerTopicResponse();
            response.setId(entity.getId());
            response.setLearnerId(entity.getLearner().getId());
            response.setLearnerName(entity.getLearner().getName());
            response.setTopicName(entity.getTopic().getName());
            response.setTopicId(entity.getTopic().getId());
        return  response;
    }
}
