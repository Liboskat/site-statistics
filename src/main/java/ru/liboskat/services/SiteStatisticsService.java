package ru.liboskat.services;

import ru.liboskat.dto.DailyStatisticsDto;
import ru.liboskat.dto.IntervalStatisticsDto;
import ru.liboskat.forms.SiteVisitForm;

import java.time.LocalDate;

public interface SiteStatisticsService {
    DailyStatisticsDto registerVisit(SiteVisitForm dto);
    IntervalStatisticsDto getStatistics(LocalDate from, LocalDate to);
}
