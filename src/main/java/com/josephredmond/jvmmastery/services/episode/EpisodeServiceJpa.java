package com.josephredmond.jvmmastery.services.episode;

import com.josephredmond.jvmmastery.dto.EpisodeDTO;
import com.josephredmond.jvmmastery.services.episode.functions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EpisodeServiceJpa implements EpisodeService {
    private final PatchEpisodeById patchEpisodeById;
    private final UpdateEpisodeById updateEpisodeById;
    private final DeleteEpisodeById deleteEpisodeById;
    private final SaveEpisode saveEpisode;
    private final FindAllEpisodes findAllEpisodes;
    private final FindEpisodeById findEpisodeById;

    @Override
    public Optional<EpisodeDTO> findById(UUID id) {
        return findEpisodeById.apply(id);
    }

    @Override
    public List<EpisodeDTO> findAll() {
        return findAllEpisodes.get();
    }

    @Override
    public EpisodeDTO save(EpisodeDTO episodeDTO) {
        return saveEpisode.apply(episodeDTO);
    }

    @Override
    public Boolean deleteById(UUID id) {
        return deleteEpisodeById.apply(id);
    }

    @Override
    public Boolean updateById(UUID id, EpisodeDTO episodeDTO) {
        return updateEpisodeById.apply(id, episodeDTO);
    }

    @Override
    public Boolean patchById(UUID id, EpisodeDTO episodeDTO) {
        return patchEpisodeById.apply(id, episodeDTO);
    }
}
