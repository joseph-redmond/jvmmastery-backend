package com.josephredmond.jvmmastery.controllers.series;

import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public interface SeriesController {
    ResponseEntity<SeriesDTO> getSeriesById(UUID id);

    ResponseEntity<List<SeriesDTO>> getAllSeries();

    ResponseEntity<Void> createSeries(SeriesDTO series);

    ResponseEntity<Void> updateSeriesById(UUID id, SeriesDTO series);

    ResponseEntity<Void> patchSeriesById(UUID id, SeriesDTO series);

    ResponseEntity<Void> deleteSeriesById(UUID id);

}
