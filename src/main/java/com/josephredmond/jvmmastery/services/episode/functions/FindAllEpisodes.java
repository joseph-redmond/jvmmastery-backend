package com.josephredmond.jvmmastery.services.episode.functions;

import com.josephredmond.jvmmastery.dto.episode.EpisodeDTO;
import com.josephredmond.jvmmastery.mapper.episode.EpisodeMapper;
import com.josephredmond.jvmmastery.repositories.episode.EpisodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class FindAllEpisodes implements Supplier<List<EpisodeDTO>> {
    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;

    @Override
    @Transactional
    public List<EpisodeDTO> get() {
        return episodeRepository.findAll().stream().map(episodeMapper::episodeToEpisodeDto).toList();
    }
}
