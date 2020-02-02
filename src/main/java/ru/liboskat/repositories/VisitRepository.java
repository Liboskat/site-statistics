package ru.liboskat.repositories;

import ru.liboskat.dto.IntervalStatisticsDto;
import ru.liboskat.dto.DailyStatisticsDto;

import java.sql.Timestamp;

public interface VisitRepository {
    DailyStatisticsDto insertVisit(Long userId, Long pageId, Timestamp timestamp);
    IntervalStatisticsDto getStatistics(Timestamp from, Timestamp to);
}
