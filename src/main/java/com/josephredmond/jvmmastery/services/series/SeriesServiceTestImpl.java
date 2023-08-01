package com.josephredmond.jvmmastery.services.series;

import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class SeriesServiceTestImpl implements SeriesService {
    private final Set<SeriesDTO> seriesDTOSet = new HashSet<>();

    public SeriesServiceTestImpl() {
        final SeriesDTO series1 = SeriesDTO.builder()
                .id(UUID.randomUUID())
                .title("Series 1")
                .description("Series 1 description")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        final SeriesDTO series2 = SeriesDTO.builder()
                .id(UUID.randomUUID())
                .title("Series 2")
                .description("Series 2 description")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        final SeriesDTO series3 = SeriesDTO.builder()
                .id(UUID.randomUUID())
                .title("Series 3")
                .description("Series 3 description")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        seriesDTOSet.addAll(List.of(series1, series2, series3));
    }

    @Override
    public Optional<SeriesDTO> findById(UUID id) {
        return seriesDTOSet.stream().filter(seriesDTO -> seriesDTO.getId().equals(id)).findFirst();
    }

    @Override
    public List<SeriesDTO> findAll() {
        return seriesDTOSet.stream().toList();
    }

    @Override
    public SeriesDTO save(SeriesDTO seriesDTO) {
        seriesDTO.setId(UUID.randomUUID());
        seriesDTOSet.add(seriesDTO);
        return seriesDTO;
    }

    @Override
    public Boolean deleteById(UUID id) {
        return seriesDTOSet.removeIf(seriesDTO -> seriesDTO.getId().equals(id));
    }

    @Override
    public Boolean updateById(UUID id, SeriesDTO seriesDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        seriesDTOSet.stream().filter(seriesDTO1 -> seriesDTO1.getId().equals(id)).findFirst().ifPresent(seriesDTO1 -> {
            seriesDTO1.setTitle(seriesDTO.getTitle());
            seriesDTO1.setDescription(seriesDTO.getDescription());
            seriesDTO1.setEpisodes(seriesDTO.getEpisodes());
            seriesDTO1.setReleaseDate(seriesDTO.getReleaseDate());
            seriesDTO1.setLastModifiedDate(LocalDateTime.now());
            result.set(true);
        });
        return result.get();
    }

    @Override
    public Boolean patchById(UUID id, SeriesDTO seriesDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        seriesDTOSet.stream().filter(seriesDTO1 -> seriesDTO1.getId().equals(id)).findFirst().ifPresent(seriesDTO1 -> {
            if (seriesDTO.getTitle() != null) {
                seriesDTO1.setTitle(seriesDTO.getTitle());
            }
            if (seriesDTO.getDescription() != null) {
                seriesDTO1.setDescription(seriesDTO.getDescription());
            }
            if (seriesDTO.getEpisodes() != null) {
                seriesDTO1.setEpisodes(seriesDTO.getEpisodes());
            }
            if (seriesDTO.getReleaseDate() != null) {
                seriesDTO1.setReleaseDate(seriesDTO.getReleaseDate());
            }
            seriesDTO1.setLastModifiedDate(LocalDateTime.now());
            result.set(true);
        });
        return result.get();
    }
}
