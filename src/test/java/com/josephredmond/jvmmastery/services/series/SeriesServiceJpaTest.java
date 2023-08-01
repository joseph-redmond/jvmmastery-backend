package com.josephredmond.jvmmastery.services.series;

import com.josephredmond.jvmmastery.bootstrap.SeriesBootstrap;
import com.josephredmond.jvmmastery.domain.series.Series;
import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.mapper.series.SeriesMapper;
import com.josephredmond.jvmmastery.mapper.series.SeriesMapperImpl;
import com.josephredmond.jvmmastery.repositories.series.SeriesRepository;
import com.josephredmond.jvmmastery.services.series.functions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import({
        SeriesServiceJpa.class,
        SeriesBootstrap.class,
        FindSeriesById.class,
        FindAllSeries.class,
        SaveSeries.class,
        UpdateSeriesById.class,
        PatchSeriesById.class,
        DeleteSeriesById.class,
        SeriesMapperImpl.class
})
class SeriesServiceJpaTest {
    @Autowired
    private SeriesService seriesService;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeriesMapper seriesMapper;

    @Test
    @Order(0)
    void testFindSeriesById() {
        Series series = seriesRepository.findAll().get(0);
        Optional<SeriesDTO> seriesDTOOptional = seriesService.findById(series.getId());
        assertTrue(seriesDTOOptional.isPresent());
        assertEquals(series.getId(), seriesDTOOptional.get().getId());
    }

    @Test
    @Order(0)
    void testFindSeriesByIdNotFound() {
        Optional<SeriesDTO> seriesDTOOptional = seriesService.findById(UUID.randomUUID());
        assertTrue(seriesDTOOptional.isEmpty());
    }

    @Order(0)
    @Test
    void testFindSeriesByIdNull() {
        Optional<SeriesDTO> seriesDTOOptional = seriesService.findById(null);
        assertTrue(seriesDTOOptional.isEmpty());
    }

    @Order(1)
    @Test
    void testListAllSeries() {
        assertEquals(3, seriesService.findAll().size());
    }

    @Test
    @Order(2)
    void testUpdateSeriesById() {
        Series series = seriesRepository.findAll().get(0);
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setTitle("new title");
        Boolean result = seriesService.updateById(series.getId(), seriesDTO);
        assertTrue(result);
    }

    @Test
    @Order(2)
    void testUpdateSeriesByIdNotFound() {
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setTitle("new title");
        Boolean result = seriesService.updateById(UUID.randomUUID(), seriesDTO);
        assertFalse(result);
    }

    @Test
    @Order(2)
    void testUpdateSeriesByIdNull() {
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setTitle("new title");
        Boolean result = seriesService.updateById(null, seriesDTO);
        assertFalse(result);
    }

    @Test
    @Order(3)
    void testPatchSeriesById() {
        Series series = seriesRepository.findAll().get(0);
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setTitle("new title");
        seriesDTO.setDescription("new description");
        seriesDTO.setEpisodes(null);
        seriesDTO.setReleaseDate(LocalDate.now());
        Boolean result = seriesService.patchById(series.getId(), seriesDTO);
        assertTrue(result);
    }

    @Test
    @Order(3)
    void testPatchSeriesByIdNotFound() {
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setTitle("new title");
        Boolean result = seriesService.patchById(UUID.randomUUID(), seriesDTO);
        assertFalse(result);
    }

    @Test
    @Order(3)
    void testPatchSeriesByIdNull() {
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setTitle("new title");
        Boolean result = seriesService.patchById(null, seriesDTO);
        assertFalse(result);
    }

    @Test
    @Order(4)
    void testSaveEpisode() {
        final Series series = Series.builder()
                .title("Series 1")
                .description("Series 1 description")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        SeriesDTO savedSeriesDTO = seriesService.save(seriesMapper.seriesToSeriesDto(series));

        assertNotNull(savedSeriesDTO.getId());
        assertEquals(series.getTitle(), savedSeriesDTO.getTitle());
    }

    @Test
    @Order(4)
    void testSaveSeriesNull() {
        SeriesDTO savedSeriesDTO = seriesService.save(null);
        assertNull(savedSeriesDTO);
    }

    @Test
    @Order(999)
    void testDeleteSeriesById() {
        Series series = seriesRepository.findAll().get(0);
        Boolean result = seriesService.deleteById(series.getId());
        assertTrue(result);
    }

    @Test
    @Order(999)
    void testDeleteSeriesByIdNotFound() {
        Boolean result = seriesService.deleteById(UUID.randomUUID());
        assertFalse(result);
    }

    @Test
    @Order(999)
    void testDeleteSeriesByIdNull() {
        Boolean result = seriesService.deleteById(null);
        assertFalse(result);
    }
}