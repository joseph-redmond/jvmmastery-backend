package com.josephredmond.jvmmastery.repositories.series;

import com.josephredmond.jvmmastery.domain.series.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeriesRepository extends JpaRepository<Series, UUID> {
}
