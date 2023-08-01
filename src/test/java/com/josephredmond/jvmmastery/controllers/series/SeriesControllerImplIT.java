package com.josephredmond.jvmmastery.controllers.series;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephredmond.jvmmastery.bootstrap.SeriesBootstrap;
import com.josephredmond.jvmmastery.domain.series.Series;
import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.mapper.series.SeriesMapper;
import com.josephredmond.jvmmastery.repositories.series.SeriesRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class SeriesControllerImplIT {
    @Autowired
    SeriesController seriesController;

    @Autowired
    SeriesRepository seriesRepository;

    @Autowired
    SeriesMapper seriesMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        new SeriesBootstrap(seriesRepository).run();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testFindSeriesById() throws Exception {
        Series testSeries = seriesRepository.findAll().get(0);
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH_ID, testSeries.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testSeries.getId().toString()));
    }

    @Test
    void testFindSeriesByIdNotFound() throws Exception {
        Series testSeries = seriesRepository.findAll().get(0);
        seriesRepository.deleteAll();
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH_ID, testSeries.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testFindSeriesByIdBadInput() throws Exception {
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH_ID, "bad input")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void testUpdateSeriesById() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        testSeriesDTO.setTitle("new title");
        mockMvc.perform(put(SeriesControllerImpl.SERIES_PATH_ID, testSeriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSeriesDTO)))
                .andExpect(status().isNoContent());

        Optional<Series> updatedSeries = seriesRepository.findById(testSeriesDTO.getId());
        assertTrue(updatedSeries.isPresent());
        assertEquals(testSeriesDTO.getTitle(), updatedSeries.get().getTitle());
    }

    @Test
    @Transactional
    void testUpdateSeriesByIdNotFound() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        testSeriesDTO.setId(UUID.randomUUID());
        testSeriesDTO.setTitle("new title");
        mockMvc.perform(put(SeriesControllerImpl.SERIES_PATH_ID, testSeriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSeriesDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void testUpdateSeriesByIdBadRequest() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        testSeriesDTO.setId(null);
        testSeriesDTO.setTitle(null);
        mockMvc.perform(put(SeriesControllerImpl.SERIES_PATH_ID, "not-a-uuid")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSeriesDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void testPatchSeriesById() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        testSeriesDTO.setTitle("new title");
        mockMvc.perform(patch(SeriesControllerImpl.SERIES_PATH_ID, testSeriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSeriesDTO)))
                .andExpect(status().isNoContent());

        Optional<Series> updatedSeries = seriesRepository.findById(testSeriesDTO.getId());
        assertTrue(updatedSeries.isPresent());
        assertEquals(testSeriesDTO.getTitle(), updatedSeries.get().getTitle());
    }

    @Test
    @Transactional
    void testPatchSeriesByIdNotFound() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        testSeriesDTO.setId(UUID.randomUUID());
        testSeriesDTO.setTitle("new title");
        mockMvc.perform(patch(SeriesControllerImpl.SERIES_PATH_ID, testSeriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSeriesDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void testPatchSeriesByIdBadRequest() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        testSeriesDTO.setId(null);
        testSeriesDTO.setTitle(null);
        mockMvc.perform(patch(SeriesControllerImpl.SERIES_PATH_ID, "not-a-uuid")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSeriesDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void saveSeries() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        testSeriesDTO.setId(null);
        MvcResult result = mockMvc.perform(post(SeriesControllerImpl.SERIES_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSeriesDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location")).andReturn();

        String id = result.getResponse().getHeader("Location").split("/")[4];
        Optional<SeriesDTO> savedSeriesDTO = seriesRepository.findById(UUID.fromString(id)).map(seriesMapper::seriesToSeriesDto);
        assertTrue(savedSeriesDTO.isPresent());
        assertEquals(testSeriesDTO.getTitle(), savedSeriesDTO.get().getTitle());
    }

    @Test
    @Transactional
    void saveSeriesBadRequest() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        testSeriesDTO.setId(null);
        testSeriesDTO.setTitle(null);
        mockMvc.perform(post(SeriesControllerImpl.SERIES_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSeriesDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void testDeleteById() throws Exception {
        SeriesDTO testSeriesDTO = seriesMapper.seriesToSeriesDto(seriesRepository.findAll().get(0));
        mockMvc.perform(delete(SeriesControllerImpl.SERIES_PATH_ID, testSeriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        SeriesDTO deletedSeriesDTO = seriesRepository.findById(testSeriesDTO.getId())
                .map(seriesMapper::seriesToSeriesDto)
                .orElse(null);

        assertNull(deletedSeriesDTO);
    }

    @Test
    void testDeleteByIdNotFound() throws Exception {
        mockMvc.perform(delete(SeriesControllerImpl.SERIES_PATH_ID, UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteByIdBadRequest() throws Exception {
        mockMvc.perform(delete(SeriesControllerImpl.SERIES_PATH_ID, "not-a-uuid")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    void testFindAllEmptyList() throws Exception {
        seriesRepository.deleteAll();
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }

    @AfterEach
    void afterEach() {
        seriesRepository.deleteAll();
    }
}