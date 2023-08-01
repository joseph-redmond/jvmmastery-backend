package com.josephredmond.jvmmastery.mapper;

import com.josephredmond.jvmmastery.domain.Episode;
import com.josephredmond.jvmmastery.dto.EpisodeDTO;
import org.mapstruct.Mapper;

@Mapper
public interface EpisodeMapper {
    Episode episodeDtoToEpisode(EpisodeDTO episodeDto);
    EpisodeDTO episodeToEpisodeDto(Episode episode);
}
