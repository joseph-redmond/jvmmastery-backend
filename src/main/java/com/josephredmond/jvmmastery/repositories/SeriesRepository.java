package com.josephredmond.jvmmastery.repositories;

import com.josephredmond.jvmmastery.domain.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeriesRepository extends JpaRepository<Series, UUID> {
}
