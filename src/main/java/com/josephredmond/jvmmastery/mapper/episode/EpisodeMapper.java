package com.josephredmond.jvmmastery.mapper.episode;

import com.josephredmond.jvmmastery.domain.episode.Episode;
import com.josephredmond.jvmmastery.dto.episode.EpisodeDTO;
import org.mapstruct.Mapper;

@Mapper
public interface EpisodeMapper {
    Episode episodeDtoToEpisode(EpisodeDTO episodeDto);
    EpisodeDTO episodeToEpisodeDto(Episode episode);
}
