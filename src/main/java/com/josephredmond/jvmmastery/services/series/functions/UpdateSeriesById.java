package com.josephredmond.jvmmastery.services.series.functions;

import com.josephredmond.jvmmastery.domain.series.Series;
import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.mapper.series.SeriesMapper;
import com.josephredmond.jvmmastery.repositories.series.SeriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class UpdateSeriesById implements BiFunction<UUID, SeriesDTO, Boolean> {
    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;

    @Override
    @Transactional
    public Boolean apply(UUID id, SeriesDTO seriesDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (id == null || seriesDTO == null) return result.get();
        seriesRepository.findById(id).ifPresent(series -> {
            Series convertedSeries = seriesMapper.seriesDtoToSeries(seriesDTO);
            series.setTitle(convertedSeries.getTitle());
            series.setDescription(convertedSeries.getDescription());
            series.setEpisodes(convertedSeries.getEpisodes());
            series.setReleaseDate(convertedSeries.getReleaseDate());
            series.setLastModifiedDate(LocalDateTime.now());
            seriesRepository.save(series);
            result.set(true);
        });
        return result.get();
    }
}
