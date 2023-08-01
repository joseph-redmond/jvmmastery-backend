package com.josephredmond.jvmmastery.services.episode.functions;

import com.josephredmond.jvmmastery.dto.episode.EpisodeDTO;
import com.josephredmond.jvmmastery.mapper.episode.EpisodeMapper;
import com.josephredmond.jvmmastery.repositories.episode.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FindEpisodeById implements Function<UUID, Optional<EpisodeDTO>> {
    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;
    @Override
    public Optional<EpisodeDTO> apply(UUID id) {
        if (id == null) return Optional.empty();
        return episodeRepository.findById(id).map(episodeMapper::episodeToEpisodeDto);
    }
}
