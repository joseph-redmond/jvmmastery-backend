package com.josephredmond.jvmmastery.repositories;

import com.josephredmond.jvmmastery.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {
}
