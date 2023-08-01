package com.josephredmond.jvmmastery.mapper;

import com.josephredmond.jvmmastery.domain.Series;
import com.josephredmond.jvmmastery.dto.SeriesDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SeriesMapper {
    Series seriesDtoToSeries(SeriesDTO seriesDto);
    SeriesDTO seriesToSeriesDto(Series series);
}
