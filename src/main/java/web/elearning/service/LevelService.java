package web.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.elearning.dto.request.LevelRequest;
import web.elearning.dto.response.LevelResponse;
import web.elearning.mapper.LevelMapper;
import web.elearning.model.Level;
import web.elearning.repository.LevelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository levelRepository;

    public List<LevelResponse> findAll() {
        return levelRepository.findAll().stream()
                .map(LevelMapper::entityToResponse)
                .toList();
    }

    public Page<LevelResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Level> topicPage = levelRepository.findAll(pageable);
        List<LevelResponse> responseList = topicPage.getContent().stream()
                .map(LevelMapper::entityToResponse)
                .toList();
        return new PageImpl<>(responseList, pageable, topicPage.getTotalElements());
    }

    public LevelResponse findById(Long id) {
        return LevelMapper.entityToResponse(levelRepository.findById(id).orElseThrow(() -> new RuntimeException("level not found")));
    }

    public LevelResponse add(LevelRequest levelRequest) {
        Level level = LevelMapper.addToEntity(levelRequest);
        levelRepository.save(level);
        return LevelMapper.entityToResponse(level);
    }

    public LevelResponse update(LevelRequest levelRequest, Long id) {
        levelRepository.findById(id).orElseThrow(() -> new RuntimeException("level not found"));
        Level level = LevelMapper.updateToEntity(levelRequest, id);
        return LevelMapper.entityToResponse(levelRepository.save(level));
    }
}
