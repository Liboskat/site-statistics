package ru.liboskat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liboskat.dto.DailyStatisticsDto;
import ru.liboskat.dto.IntervalStatisticsDto;
import ru.liboskat.forms.SiteVisitForm;
import ru.liboskat.repositories.VisitRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class SiteStatisticsServiceImpl implements SiteStatisticsService {

    private final VisitRepository repository;

    @Autowired
    public SiteStatisticsServiceImpl(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public DailyStatisticsDto registerVisit(SiteVisitForm dto) {
        return repository.insertVisit(dto.getUserId(), dto.getPageId(), Timestamp.from(Instant.now()));
    }

    @Override
    public IntervalStatisticsDto getStatistics(LocalDate from, LocalDate to) {
        return repository.getStatistics(Timestamp.valueOf(from.atStartOfDay()),
                Timestamp.valueOf(to.atTime(LocalTime.MAX)));
    }
}
