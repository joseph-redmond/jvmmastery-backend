package com.josephredmond.jvmmastery.services.episode.functions;

import com.josephredmond.jvmmastery.dto.EpisodeDTO;
import com.josephredmond.jvmmastery.repositories.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class UpdateEpisodeById implements BiFunction<UUID, EpisodeDTO, Boolean> {
    private final EpisodeRepository episodeRepository;
    @Override
    public Boolean apply(UUID id, EpisodeDTO episodeDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (id == null || episodeDTO == null) return result.get();
        episodeRepository.findById(id).ifPresent(episode -> {
            episode.setTitle(episodeDTO.getTitle());
            episode.setDescription(episodeDTO.getDescription());
            episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
            episode.setSeries(episodeDTO.getSeries());
            episode.setVideoUrl(episodeDTO.getVideoUrl());
            episode.setReleaseDate(episodeDTO.getReleaseDate());
            episode.setLastModifiedDate(LocalDateTime.now());
            episodeRepository.save(episode);
            result.set(true);
        });
        return result.get();
    }
}
