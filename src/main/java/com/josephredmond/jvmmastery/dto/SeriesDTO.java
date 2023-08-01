package com.josephredmond.jvmmastery.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SeriesDTO {
    private UUID id;
    private String title;
    private String description;
    private List<EpisodeDTO> episodes;
    private LocalDate releaseDate;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
