package com.josephredmond.jvmmastery.services.episode;

import com.josephredmond.jvmmastery.dto.episode.EpisodeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EpisodeService {
    Optional<EpisodeDTO> findById(UUID id);
    List<EpisodeDTO> findAll();

    EpisodeDTO save(EpisodeDTO episodeDTO);

    Boolean deleteById(UUID id);

    Boolean updateById(UUID id, EpisodeDTO episodeDTO);

    Boolean patchById(UUID id, EpisodeDTO episodeDTO);
}
