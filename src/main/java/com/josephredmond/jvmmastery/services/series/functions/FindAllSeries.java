package com.josephredmond.jvmmastery.services.series.functions;

import com.josephredmond.jvmmastery.dto.SeriesDTO;
import com.josephredmond.jvmmastery.mapper.SeriesMapper;
import com.josephredmond.jvmmastery.repositories.SeriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class FindAllSeries implements Supplier<List<SeriesDTO>> {
    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;
    @Override
    @Transactional
    public List<SeriesDTO> get() {
        return seriesRepository.findAll().stream().map(seriesMapper::seriesToSeriesDto).toList();
    }
}
