package com.josephredmond.jvmmastery.bootstrap;

import com.josephredmond.jvmmastery.domain.series.Series;
import com.josephredmond.jvmmastery.repositories.series.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeriesBootstrap implements CommandLineRunner {
    private final SeriesRepository seriesRepository;

    @Override
    public void run(String... args) throws Exception {
        initSeries();
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
