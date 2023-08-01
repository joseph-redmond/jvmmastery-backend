package com.josephredmond.jvmmastery.services.episode.functions;

import com.josephredmond.jvmmastery.dto.episode.EpisodeDTO;
import com.josephredmond.jvmmastery.mapper.episode.EpisodeMapper;
import com.josephredmond.jvmmastery.repositories.episode.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SaveEpisode implements Function<EpisodeDTO, EpisodeDTO> {
    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;
    @Override
    public EpisodeDTO apply(EpisodeDTO episodeDTO) {
        if (episodeDTO == null) return null;
        return episodeMapper.episodeToEpisodeDto(episodeRepository.save(episodeMapper.episodeDtoToEpisode(episodeDTO)));
    }
}
