package com.josephredmond.jvmmastery.services.episode.functions;

import com.josephredmond.jvmmastery.dto.EpisodeDTO;
import com.josephredmond.jvmmastery.mapper.EpisodeMapper;
import com.josephredmond.jvmmastery.repositories.EpisodeRepository;
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
