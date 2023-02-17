package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController("exhauster-monitoring-controller")
@RequestMapping(value = "/api/v1")
public class ExhausterMonitoringController {

    private final ExhausterMonitoringService exhausterMonitoringService;

    @RequestMapping(method = RequestMethod.GET, value = "/info")
    @ResponseStatus(HttpStatus.OK)
    public InfoDto getInfo() {
        return exhausterMonitoringService.getInfo();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/metrics")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, String>> getMetricsJsonBetweenStartDateAndEndDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/metrics/values")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllMetricsByMetricNameBetweenStartDateAndEndDate(
            @RequestParam String metricName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/metrics/json/last")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> getLastMetricsJson() {
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/metrics/value/last")
    @ResponseStatus(HttpStatus.OK)
    public String getLastMetricByMetricName(@RequestParam String metricName) {
        return null;
    }
}
