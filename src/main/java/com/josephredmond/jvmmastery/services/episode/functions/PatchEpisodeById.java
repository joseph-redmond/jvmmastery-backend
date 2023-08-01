package com.josephredmond.jvmmastery.services.episode.functions;

import com.josephredmond.jvmmastery.dto.episode.EpisodeDTO;
import com.josephredmond.jvmmastery.repositories.episode.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class PatchEpisodeById implements BiFunction<UUID, EpisodeDTO, Boolean> {
    private final EpisodeRepository episodeRepository;
    @Override
    public Boolean apply(UUID id, EpisodeDTO episodeDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (id == null || episodeDTO == null) return result.get();
        episodeRepository.findById(id).ifPresent(episode -> {
            if (episodeDTO.getTitle() != null) {
                episode.setTitle(episodeDTO.getTitle());
            }
            if (episodeDTO.getDescription() != null) {
                episode.setDescription(episodeDTO.getDescription());
            }
            if (episodeDTO.getEpisodeNumber() != null) {
                episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
            }
            if (episodeDTO.getSeries() != null) {
                episode.setSeries(episodeDTO.getSeries());
            }
            if (episodeDTO.getVideoUrl() != null) {
                episode.setVideoUrl(episodeDTO.getVideoUrl());
            }
            if (episodeDTO.getReleaseDate() != null) {
                episode.setReleaseDate(episodeDTO.getReleaseDate());
            }
            episode.setLastModifiedDate(LocalDateTime.now());
            episodeRepository.save(episode);
            result.set(true);
        });
        return result.get();
    }
}
