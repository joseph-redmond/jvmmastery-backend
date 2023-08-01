package com.josephredmond.jvmmastery.bootstrap;

import com.josephredmond.jvmmastery.domain.Episode;
import com.josephredmond.jvmmastery.domain.Series;
import com.josephredmond.jvmmastery.domain.User;
import com.josephredmond.jvmmastery.repositories.EpisodeRepository;
import com.josephredmond.jvmmastery.repositories.SeriesRepository;
import com.josephredmond.jvmmastery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EpisodeRepository episodeRepository;
    private final SeriesRepository seriesRepository;
    @Override
    public void run(String... args) {
        initUsers();
        initEpisodes();
        initSeries();
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            final User user1 = User.builder()
                    .firstName("Joe")
                    .lastName("Redmond")
                    .email("me@test.com")
                    .password("password")
                    .build();

            final User user2 = User.builder()
                    .firstName("Jane")
                    .lastName("Doe")
                    .email("jane@janodoe.com")
                    .password("password")
                    .build();

            final User user3 = User.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john@johndoe.com")
                    .password("password")
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
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

    private void initSeries() {
        if (seriesRepository.count() == 0) {
            final Series series1 = Series.builder()
                    .title("Series 1")
                    .description("Series 1 description")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            final Series series2 = Series.builder()
                    .title("Series 2")
                    .description("Series 2 description")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            final Series series3 = Series.builder()
                    .title("Series 3")
                    .description("Series 3 description")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            seriesRepository.saveAllAndFlush(List.of(series1, series2, series3));
        }
    }
}
