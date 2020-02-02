package ru.liboskat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.liboskat.dto.DailyStatisticsDto;
import ru.liboskat.dto.IntervalStatisticsDto;
import ru.liboskat.forms.SiteVisitForm;
import ru.liboskat.services.SiteStatisticsService;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class WebsiteStatisticsController {
    private final SiteStatisticsService service;

    @Autowired
    public WebsiteStatisticsController(SiteStatisticsService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<DailyStatisticsDto> registerVisit(@RequestBody @Valid SiteVisitForm visit,
                                                            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.registerVisit(visit));
        }
    }

    @GetMapping(value = "/statistics", params = {"from", "to"})
    public ResponseEntity<IntervalStatisticsDto> getStatistics(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        if (from.isBefore(to)) {
            return ResponseEntity.ok(service.getStatistics(from, to));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}