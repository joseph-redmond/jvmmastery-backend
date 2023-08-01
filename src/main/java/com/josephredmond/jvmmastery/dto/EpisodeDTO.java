package com.josephredmond.jvmmastery.dto;

import com.josephredmond.jvmmastery.domain.Series;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EpisodeDTO {
    private UUID id;
    private String title;
    private String description;
    private Integer episodeNumber;
    private String videoUrl;
    private Series series;
    private LocalDate releaseDate;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
