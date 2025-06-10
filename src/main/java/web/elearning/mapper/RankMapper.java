package web.elearning.mapper;

import web.elearning.dto.request.RankRequest;
import web.elearning.dto.response.RankResponse;
import web.elearning.model.Rank;

public class RankMapper {
    public static Rank addRequestToEntity(RankRequest request) {
        Rank rank = new Rank();
        rank.setName(request.getName());
        rank.setDescribes(request.getDescribes());
        return rank;
    }

    public static Rank updateRequestToEntity(RankRequest request, Long id) {
        Rank rank = new Rank();
        rank.setId(id);
        rank.setName(request.getName());
        rank.setDescribes(request.getDescribes());
        return rank;
    }

    public static RankResponse entityToResponse(Rank entity) {
        RankResponse rank = new RankResponse();
        rank.setId(entity.getId());
        rank.setName(entity.getName());
        rank.setDescribes(entity.getDescribes());
        return rank;
    }

}
