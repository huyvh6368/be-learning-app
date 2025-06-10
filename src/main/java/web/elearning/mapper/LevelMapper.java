package web.elearning.mapper;

import web.elearning.dto.request.LevelRequest;
import web.elearning.dto.response.LevelResponse;
import web.elearning.model.Level;

public class LevelMapper {
    public static Level addToEntity(LevelRequest request) {
        Level entity = new Level();
        entity.setName(request.getName());
        entity.setDescribes(request.getDescribes());
        return entity;
    }

    public static Level updateToEntity(LevelRequest request, Long id) {
        Level entity = new Level();
        entity.setId(id);
        entity.setName(request.getName());
        entity.setDescribes(request.getDescribes());
        return entity;
    }

    public static LevelResponse entityToResponse(Level entity) {
        LevelResponse response = new LevelResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescribes(entity.getDescribes());
        return response;
    }
}
