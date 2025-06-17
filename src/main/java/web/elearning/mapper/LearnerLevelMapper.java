package web.elearning.mapper;

import web.elearning.dto.request.LearnerLevelRequest;
import web.elearning.dto.response.LearnerLevelResponse;
import web.elearning.model.Learner;
import web.elearning.model.LearnerLevel;
import web.elearning.model.Level;

public class LearnerLevelMapper {
    public static LearnerLevel requestToEntity(LearnerLevelRequest request, Learner learner, Level level, String status) {
        LearnerLevel learnerLevel = new LearnerLevel();
        learnerLevel.setId(learner.getId());
        learnerLevel.setLearner(learner);
        learnerLevel.setLevel(level);
        learnerLevel.setStatus(status);
        return learnerLevel;
    }

    public static LearnerLevelResponse entityToResponse(LearnerLevel entity) {
        LearnerLevelResponse res = new LearnerLevelResponse();
        res.setId(entity.getId());
        res.setStatus(entity.getStatus());
        res.setLearnerName(entity.getLearner().getName());
        res.setLearnerId(entity.getLearner().getId());
        res.setLevelName(entity.getLevel().getName());
        res.setLevelId(entity.getLevel().getId());
        return res;
    }
}
