package web.elearning.mapper;

import web.elearning.dto.request.TopicRequest;
import web.elearning.dto.response.TopicResponse;
import web.elearning.model.Level;
import web.elearning.model.Topic;

public class TopicMapper {
    public static Topic addRequestToEntity(TopicRequest request, Level level) {
        Topic topic = new Topic();
        topic.setCode(request.getCode());
        topic.setName(request.getName());
        topic.setDescribes(request.getDescribes());
        topic.setLevel(level);
        return topic;
    }

    public static Topic updateRequestToEntity(TopicRequest request, Level level, Long id) {
        Topic topic = new Topic();
        topic.setId(id);
        topic.setCode(request.getCode());
        topic.setName(request.getName());
        topic.setDescribes(request.getDescribes());
        topic.setLevel(level);
        return topic;
    }

    public static TopicResponse entityToResponse(Topic topic) {
        TopicResponse response = new TopicResponse();
        response.setId(topic.getId());
        response.setCode(topic.getCode());
        response.setName(topic.getName());
        response.setDescribes(topic.getDescribes());
        response.setLevelId(topic.getLevel().getId());
        response.setLevelName(topic.getLevel().getName());
        return response;
    }
}
