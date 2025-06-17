package web.elearning.mapper;

import web.elearning.dto.request.LearnerRequest;
import web.elearning.dto.response.LearnerResponse;
import web.elearning.model.Account;
import web.elearning.model.Learner;

import java.math.BigDecimal;

public class LearnerMapper {
    public static Learner addToLearner(LearnerRequest request, Account account) {
        Learner learner = new Learner();
        learner.setName(request.getName());
        learner.setAccount(account);
        learner.setCode("learnerCODE");
        learner.setRank(null);
        learner.setTotalScore(new BigDecimal(request.getTotalScore()));
        return learner;
    }

    public static Learner updateToLearner(LearnerRequest request, Long id) {
        Learner learner = new Learner();
        learner.setId(id);
        learner.setCode("learnerCODE");
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
            learner.setAccountId(entity.getAccount().getId());
        }
        learner.setTotalScore(entity.getTotalScore());
        return learner;
    }
}
