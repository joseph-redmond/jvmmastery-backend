package com.josephredmond.jvmmastery.bootstrap;

import com.josephredmond.jvmmastery.domain.episode.Episode;
import com.josephredmond.jvmmastery.repositories.episode.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EpisodeBootstrap implements CommandLineRunner {

    private final EpisodeRepository episodeRepository;

    @Override
    public void run(String... args) {
        initEpisodes();
    }

    private void initEpisodes() {
        if (episodeRepository.count() == 0) {
            final Episode episode1 = Episode.builder()
                    .title("Episode 1")
                    .description("Episode 1 description")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            final Episode episode2 = Episode.builder()
                    .title("Episode 2")
                    .description("Episode 2 description")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            final Episode episode3 = Episode.builder()
                    .title("Episode 3")
                    .description("Episode 3 description")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            episodeRepository.saveAllAndFlush(List.of(episode1, episode2, episode3));
        }
    }
}
