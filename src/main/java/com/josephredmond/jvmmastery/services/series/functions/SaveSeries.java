package com.josephredmond.jvmmastery.services.series.functions;

import com.josephredmond.jvmmastery.dto.SeriesDTO;
import com.josephredmond.jvmmastery.mapper.SeriesMapper;
import com.josephredmond.jvmmastery.repositories.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SaveSeries implements Function<SeriesDTO, SeriesDTO> {
    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;
    @Override
    public SeriesDTO apply(SeriesDTO seriesDTO) {
        if (seriesDTO == null) return null;
        return seriesMapper.seriesToSeriesDto(seriesRepository.save(seriesMapper.seriesDtoToSeries(seriesDTO)));
    }
}
