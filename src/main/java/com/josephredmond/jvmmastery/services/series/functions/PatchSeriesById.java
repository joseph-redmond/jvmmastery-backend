package com.josephredmond.jvmmastery.services.series.functions;

import com.josephredmond.jvmmastery.domain.series.Series;
import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.mapper.series.SeriesMapper;
import com.josephredmond.jvmmastery.repositories.series.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class PatchSeriesById implements BiFunction<UUID, SeriesDTO, Boolean> {
    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;
    @Override
    public Boolean apply(UUID id, SeriesDTO seriesDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (id == null || seriesDTO == null) return result.get();
        seriesRepository.findById(id).ifPresent(series -> {
            Series convertedSeries = seriesMapper.seriesDtoToSeries(seriesDTO);
            if (seriesDTO.getTitle() != null) {
                series.setTitle(convertedSeries.getTitle());
            }
            if (seriesDTO.getDescription() != null) {
                series.setDescription(convertedSeries.getDescription());
            }
            if (seriesDTO.getEpisodes() != null) {
                series.setEpisodes(convertedSeries.getEpisodes());
            }
            if (seriesDTO.getReleaseDate() != null) {
                series.setReleaseDate(convertedSeries.getReleaseDate());
            }
            series.setLastModifiedDate(LocalDateTime.now());
            seriesRepository.save(series);
            result.set(true);
        });
        return result.get();
    }
}
