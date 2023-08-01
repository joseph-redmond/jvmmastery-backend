package com.josephredmond.jvmmastery.services.series;

import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.services.series.functions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Primary
public class SeriesServiceJpa implements SeriesService {
    private final FindSeriesById findSeriesById;
    private final FindAllSeries findAllSeries;
    private final SaveSeries saveSeries;
    private final DeleteSeriesById deleteSeriesById;
    private final UpdateSeriesById updateSeriesById;
    private final PatchSeriesById patchSeriesById;

    @Override
    public Optional<SeriesDTO> findById(UUID id) {
        return findSeriesById.apply(id);
    }

    @Override
    public List<SeriesDTO> findAll() {
        return findAllSeries.get();
    }

    @Override
    public SeriesDTO save(SeriesDTO seriesDTO) {
        return saveSeries.apply(seriesDTO);
    }

    @Override
    public Boolean deleteById(UUID id) {
        return deleteSeriesById.apply(id);
    }

    @Override
    public Boolean updateById(UUID id, SeriesDTO seriesDTO) {
        return updateSeriesById.apply(id, seriesDTO);
    }

    @Override
    public Boolean patchById(UUID id, SeriesDTO seriesDTO) {
        return patchSeriesById.apply(id, seriesDTO);
    }
}
