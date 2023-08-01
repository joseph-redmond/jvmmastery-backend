package com.josephredmond.jvmmastery.services.episode;

import com.josephredmond.jvmmastery.bootstrap.EpisodeBootstrap;
import com.josephredmond.jvmmastery.domain.episode.Episode;
import com.josephredmond.jvmmastery.dto.episode.EpisodeDTO;
import com.josephredmond.jvmmastery.mapper.episode.EpisodeMapper;
import com.josephredmond.jvmmastery.mapper.episode.EpisodeMapperImpl;
import com.josephredmond.jvmmastery.repositories.episode.EpisodeRepository;
import com.josephredmond.jvmmastery.services.episode.functions.*;
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
        EpisodeServiceJpa.class,
        EpisodeBootstrap.class,
        FindEpisodeById.class,
        FindAllEpisodes.class,
        SaveEpisode.class,
        UpdateEpisodeById.class,
        PatchEpisodeById.class,
        DeleteEpisodeById.class,
        EpisodeMapperImpl.class

})
class EpisodeServiceJpaTest {
    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Test
    @Order(0)
    void testFindEpisodeById() {
        Episode episode = episodeRepository.findAll().get(0);
        Optional<EpisodeDTO> episodeDTOOptional = episodeService.findById(episode.getId());
        assertTrue(episodeDTOOptional.isPresent());
        assertEquals(episode.getId(), episodeDTOOptional.get().getId());
    }

    @Test
    @Order(0)
    void testFindEpisodeByIdNotFound() {
        Optional<EpisodeDTO> episodeDTOOptional = episodeService.findById(UUID.randomUUID());
        assertTrue(episodeDTOOptional.isEmpty());
    }

    @Order(0)
    @Test
    void testFindEpisodeByIdNull() {
        Optional<EpisodeDTO> episodeDTOOptional = episodeService.findById(null);
        assertTrue(episodeDTOOptional.isEmpty());
    }

    @Order(1)
    @Test
    void testListAllEpisodes() {
        assertEquals(3, episodeService.findAll().size());
    }

    @Test
    @Order(2)
    void testUpdateEpisodeById() {
        Episode episode = episodeRepository.findAll().get(0);
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setTitle("new title");
        Boolean result = episodeService.updateById(episode.getId(), episodeDTO);
        assertTrue(result);
    }

    @Test
    @Order(2)
    void testUpdateEpisodeByIdNotFound() {
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setTitle("new title");
        Boolean result = episodeService.updateById(UUID.randomUUID(), episodeDTO);
        assertFalse(result);
    }

    @Test
    @Order(2)
    void testUpdateEpisodeByIdNull() {
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setTitle("new title");
        Boolean result = episodeService.updateById(null, episodeDTO);
        assertFalse(result);
    }

    @Test
    @Order(3)
    void testPatchEpisodeById() {
        Episode episode = episodeRepository.findAll().get(0);
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setTitle("new title");
        episodeDTO.setDescription("new description");
        episodeDTO.setEpisodeNumber(1);
        episodeDTO.setReleaseDate(LocalDate.now());
        episodeDTO.setVideoUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        Boolean result = episodeService.patchById(episode.getId(), episodeDTO);
        assertTrue(result);
    }

    @Test
    @Order(3)
    void testPatchEpisodeByIdNotFound() {
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setTitle("new title");
        Boolean result = episodeService.patchById(UUID.randomUUID(), episodeDTO);
        assertFalse(result);
    }

    @Test
    @Order(3)
    void testPatchEpisodeByIdNull() {
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setTitle("new title");
        Boolean result = episodeService.patchById(null, episodeDTO);
        assertFalse(result);
    }

    @Test
    @Order(4)
    void testSaveEpisode() {
        Episode episode1 = Episode.builder()
                .title("Episode 1")
                .description("Episode 1 description")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        EpisodeDTO savedEpisodeDTO = episodeService.save(episodeMapper.episodeToEpisodeDto(episode1));

        assertNotNull(savedEpisodeDTO.getId());
        assertEquals(episode1.getTitle(), savedEpisodeDTO.getTitle());
    }

    @Test
    @Order(4)
    void testSaveEpisodeNull() {
        EpisodeDTO savedEpisodeDTO = episodeService.save(null);
        assertNull(savedEpisodeDTO);
    }

    @Test
    @Order(999)
    void testDeleteEpisodeById() {
        Episode episode = episodeRepository.findAll().get(0);
        Boolean result = episodeService.deleteById(episode.getId());
        assertTrue(result);
    }

    @Test
    @Order(999)
    void testDeleteByIdNotFound() {
        Boolean result = episodeService.deleteById(UUID.randomUUID());
        assertFalse(result);
    }

    @Test
    @Order(999)
    void testDeleteByIdNull() {
        Boolean result = episodeService.deleteById(null);
        assertFalse(result);
    }
}