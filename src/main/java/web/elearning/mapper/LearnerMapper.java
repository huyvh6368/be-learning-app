package web.elearning.mapper;

import web.elearning.dto.request.LearnerRequest;
import web.elearning.dto.response.LearnerResponse;
import web.elearning.model.Account;
import web.elearning.model.Learner;


public class LearnerMapper {
    public static Learner addToLearner(LearnerRequest request, Account account) {
        Learner learner = new Learner();
        learner.setName(request.getName());
        learner.setAccount(account);
        learner.setUrlImage(request.getUrlImage());
        learner.setCode("learnerCODE");
        learner.setRank(null);
        return learner;
    }

    public static Learner updateToLearner(LearnerRequest request, Learner learner) {
        learner.setUrlImage(request.getUrlImage());
        learner.setName(request.getName());
        return learner;
    }

    public static LearnerResponse entityToResponse(Learner entity) {
        LearnerResponse learner = new LearnerResponse();
        learner.setId(entity.getId());
        learner.setCode(entity.getCode());
        learner.setName(entity.getName());
        if (entity.getRank() != null) {
            learner.setRankId(entity.getRank().getId());
            learner.setRankName(entity.getRank().getName());
        }
        if (entity.getAccount() != null) {
            learner.setAccountName(entity.getAccount().getName());
            learner.setAccountEmail(entity.getAccount().getEmail());
            learner.setAccountId(entity.getAccount().getId());
        }
        learner.setTotalScore(entity.getTotalScore());
        learner.setUrlImage(entity.getUrlImage());
        return learner;
    }
}
