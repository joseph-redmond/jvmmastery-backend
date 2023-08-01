package com.josephredmond.jvmmastery.dto.series;

import com.josephredmond.jvmmastery.dto.episode.EpisodeDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SeriesDTO {
    private UUID id;
    @NotNull
    @NotBlank
    private String title;
    private String description;
    private List<EpisodeDTO> episodes;
    private LocalDate releaseDate;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
