package com.josephredmond.jvmmastery.services.series.functions;

import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.mapper.series.SeriesMapper;
import com.josephredmond.jvmmastery.repositories.series.SeriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FindSeriesById implements Function<UUID, Optional<SeriesDTO>> {
    private final SeriesMapper seriesMapper;
    private final SeriesRepository seriesRepository;

    @Override
    @Transactional
    public Optional<SeriesDTO> apply(UUID id) {
        if (id == null) return Optional.empty();
        return seriesRepository.findById(id).map(seriesMapper::seriesToSeriesDto);
    }
}
