package com.josephredmond.jvmmastery.controllers.series;

import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.services.series.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SeriesControllerImpl implements SeriesController {
    private final SeriesService seriesService;
    public static final String SERIES_PATH = "/api/v1/series";
    public static final String SERIES_PATH_ID = SERIES_PATH + "/{seriesId}";

    @Override
    @GetMapping(SERIES_PATH_ID)
    public ResponseEntity<SeriesDTO> getSeriesById(@PathVariable("seriesId") UUID seriesId) {
        Optional<SeriesDTO> series = seriesService.findById(seriesId);
        return series.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping(SERIES_PATH)
    public ResponseEntity<List<SeriesDTO>> getAllSeries() {
        List<SeriesDTO> seriesList = seriesService.findAll();
        return ResponseEntity.ok(seriesList);
    }

    @Override
    @PostMapping(SERIES_PATH)
    public ResponseEntity<Void> createSeries(@Validated @RequestBody SeriesDTO series) {
        SeriesDTO savedSeries = seriesService.save(series);
        return ResponseEntity.created(URI.create(SERIES_PATH + "/" + savedSeries.getId())).build();
    }

    @Override
    @PutMapping(SERIES_PATH_ID)
    public ResponseEntity<Void> updateSeriesById(@PathVariable("seriesId") UUID id, @Validated @RequestBody SeriesDTO series) {
        Boolean result = seriesService.updateById(id, series);
        if (!result) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping(SERIES_PATH_ID)
    public ResponseEntity<Void> patchSeriesById(@PathVariable("seriesId") UUID id, @Validated @RequestBody SeriesDTO series) {
        Boolean result = seriesService.patchById(id, series);
        if (!result) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping(SERIES_PATH_ID)
    public ResponseEntity<Void> deleteSeriesById(@PathVariable("seriesId") UUID id) {
        Boolean result = seriesService.deleteById(id);
        if (!result) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
