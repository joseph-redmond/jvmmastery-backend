package com.josephredmond.jvmmastery.controllers.series;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import com.josephredmond.jvmmastery.repositories.series.SeriesRepository;
import com.josephredmond.jvmmastery.services.series.SeriesService;
import com.josephredmond.jvmmastery.services.series.SeriesServiceTestImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SeriesControllerImpl.class)
class SeriesControllerImplTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SeriesService seriesService;

    @MockBean
    SeriesRepository seriesRepository;

    SeriesServiceTestImpl seriesServiceTestImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<SeriesDTO> seriesDTOArgumentCaptor;

    @BeforeEach
    void setUp() {
        seriesServiceTestImpl = new SeriesServiceTestImpl();
    }

    @Test
    void getSeriesById() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        given(seriesService.findById(seriesDTO.getId())).willReturn(seriesServiceTestImpl.findById(seriesDTO.getId()));
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId()))
                .andExpect(status().isOk());

        verify(seriesService).findById(uuidArgumentCaptor.capture());

        assertThat(uuidArgumentCaptor.getValue()).isEqualTo(seriesDTO.getId());
    }

    @Test
    void getSeriesByIdNotFound() throws Exception {
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());

        verify(seriesService).findById(uuidArgumentCaptor.capture());

        assertThat(uuidArgumentCaptor.getValue()).isNotEqualTo(UUID.randomUUID());
    }

    @Test
    void getSeriesByIdBadRequest() throws Exception {
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH_ID, "not-a-uuid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getListOfSeries() throws Exception {
        List<SeriesDTO> seriesDTOList = seriesServiceTestImpl.findAll();
        given(seriesService.findAll()).willReturn(seriesDTOList);
        mockMvc.perform(get(SeriesControllerImpl.SERIES_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));

        verify(seriesService).findAll();
    }

    @Test
    void updateSeriesById() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        given(seriesService.updateById(any(), any())).willReturn(true);
        mockMvc.perform(put(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seriesDTO)))
                .andExpect(status().isNoContent());

        verify(seriesService).updateById(uuidArgumentCaptor.capture(), seriesDTOArgumentCaptor.capture());

        assertThat(uuidArgumentCaptor.getValue()).isEqualTo(seriesDTO.getId());
        assertThat(seriesDTOArgumentCaptor.getValue()).isEqualTo(seriesDTO);
    }

    @Test
    void updateSeriesByIdNotFound() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        seriesDTO.setId(UUID.randomUUID());
        given(seriesService.updateById(any(), any())).willReturn(false);
        mockMvc.perform(put(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seriesDTO)))
                .andExpect(status().isNotFound());

        verify(seriesService).updateById(uuidArgumentCaptor.capture(), seriesDTOArgumentCaptor.capture());

        assertThat(uuidArgumentCaptor.getValue()).isEqualTo(seriesDTO.getId());
        assertThat(seriesDTOArgumentCaptor.getValue()).isEqualTo(seriesDTO);
    }

    @Test
    void updateSeriesByIdBadRequest() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        seriesDTO.setTitle(null);
        mockMvc.perform(put(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seriesDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void patchSeriesById() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        given(seriesService.patchById(any(), any())).willReturn(true);
        mockMvc.perform(patch(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seriesDTO)))
                .andExpect(status().isNoContent());

        verify(seriesService).patchById(uuidArgumentCaptor.capture(), seriesDTOArgumentCaptor.capture());

        assertThat(uuidArgumentCaptor.getValue()).isEqualTo(seriesDTO.getId());
        assertThat(seriesDTOArgumentCaptor.getValue()).isEqualTo(seriesDTO);
    }

    @Test
    void patchSeriesByIdNotFound() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        seriesDTO.setId(UUID.randomUUID());
        given(seriesService.patchById(any(), any())).willReturn(false);
        mockMvc.perform(patch(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seriesDTO)))
                .andExpect(status().isNotFound());

        verify(seriesService).patchById(uuidArgumentCaptor.capture(), seriesDTOArgumentCaptor.capture());

        assertThat(uuidArgumentCaptor.getValue()).isEqualTo(seriesDTO.getId());
        assertThat(seriesDTOArgumentCaptor.getValue()).isEqualTo(seriesDTO);
    }

    @Test
    void patchSeriesByIdBadRequest() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        seriesDTO.setTitle(null);
        mockMvc.perform(patch(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seriesDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteSeriesById() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        given(seriesService.deleteById(any())).willReturn(true);
        mockMvc.perform(delete(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId()))
                .andExpect(status().isNoContent());

        verify(seriesService).deleteById(uuidArgumentCaptor.capture());

        assertThat(uuidArgumentCaptor.getValue()).isEqualTo(seriesDTO.getId());
    }

    @Test
    void deleteSeriesByIdNotFound() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        seriesDTO.setId(UUID.randomUUID());
        given(seriesService.deleteById(any())).willReturn(false);
        mockMvc.perform(delete(SeriesControllerImpl.SERIES_PATH_ID, seriesDTO.getId()))
                .andExpect(status().isNotFound());

        verify(seriesService).deleteById(uuidArgumentCaptor.capture());

        assertThat(uuidArgumentCaptor.getValue()).isEqualTo(seriesDTO.getId());
    }

    @Test
    void deleteSeriesByIdBadRequest() throws Exception {
        mockMvc.perform(delete(SeriesControllerImpl.SERIES_PATH_ID, "bad request"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveSeries() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        seriesDTO.setId(null);
        given(seriesService.save(any())).willReturn(seriesDTO);
        mockMvc.perform(post(SeriesControllerImpl.SERIES_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seriesDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        verify(seriesService).save(seriesDTOArgumentCaptor.capture());

        assertThat(seriesDTOArgumentCaptor.getValue()).isEqualTo(seriesDTO);
    }

    @Test
    void saveSeriesBadRequest() throws Exception {
        SeriesDTO seriesDTO = seriesServiceTestImpl.findAll().get(0);
        seriesDTO.setId(null);
        seriesDTO.setTitle(null);
        mockMvc.perform(post(SeriesControllerImpl.SERIES_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seriesDTO)))
                .andExpect(status().isBadRequest());
    }
}