package com.josephredmond.jvmmastery.repositories.episode;

import com.josephredmond.jvmmastery.domain.episode.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {
}
