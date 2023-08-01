package com.josephredmond.jvmmastery.services.series.functions;

import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.mapper.series.SeriesMapper;
import com.josephredmond.jvmmastery.repositories.series.SeriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SaveSeries implements Function<SeriesDTO, SeriesDTO> {
    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;

    @Override
    @Transactional
    public SeriesDTO apply(SeriesDTO seriesDTO) {
        if (seriesDTO == null) return null;
        return seriesMapper.seriesToSeriesDto(seriesRepository.save(seriesMapper.seriesDtoToSeries(seriesDTO)));
    }
}
