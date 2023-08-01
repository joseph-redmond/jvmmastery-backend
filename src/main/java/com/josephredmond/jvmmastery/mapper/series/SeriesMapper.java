package com.josephredmond.jvmmastery.mapper.series;

import com.josephredmond.jvmmastery.domain.series.Series;
import com.josephredmond.jvmmastery.dto.series.SeriesDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SeriesMapper {
    Series seriesDtoToSeries(SeriesDTO seriesDto);
    SeriesDTO seriesToSeriesDto(Series series);
}
