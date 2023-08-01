package com.josephredmond.jvmmastery.services.episode.functions;

import com.josephredmond.jvmmastery.repositories.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DeleteEpisodeById implements Function<UUID, Boolean> {
    private final EpisodeRepository episodeRepository;
    @Override
    public Boolean apply(UUID id) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (id == null) return result.get();
        episodeRepository.findById(id).ifPresent(episode -> {
            episodeRepository.deleteById(episode.getId());
            result.set(true);
        });
        return result.get();
    }
}
