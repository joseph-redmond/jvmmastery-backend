package com.josephredmond.jvmmastery.services.episode.functions;

import com.josephredmond.jvmmastery.dto.EpisodeDTO;
import com.josephredmond.jvmmastery.mapper.EpisodeMapper;
import com.josephredmond.jvmmastery.repositories.EpisodeRepository;
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
    public List<EpisodeDTO> get() {
        return episodeRepository.findAll().stream().map(episodeMapper::episodeToEpisodeDto).toList();
    }
}
