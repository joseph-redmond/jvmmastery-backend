package com.josephredmond.jvmmastery.services.series;

import com.josephredmond.jvmmastery.dto.series.SeriesDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeriesService {
    Optional<SeriesDTO> findById(UUID id);
    List<SeriesDTO> findAll();

    SeriesDTO save(SeriesDTO seriesDTO);

    Boolean deleteById(UUID id);

    Boolean updateById(UUID id, SeriesDTO seriesDTO);

    Boolean patchById(UUID id, SeriesDTO seriesDTO);
}
