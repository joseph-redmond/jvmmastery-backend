package com.josephredmond.jvmmastery.services.series.functions;

import com.josephredmond.jvmmastery.repositories.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DeleteSeriesById implements Function<UUID, Boolean> {
    private final SeriesRepository seriesRepository;
    @Override
    public Boolean apply(UUID id) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (id == null) return result.get();
        seriesRepository.findById(id).ifPresent(series -> {
            seriesRepository.deleteById(series.getId());
            result.set(true);
        });
        return result.get();
    }
}
