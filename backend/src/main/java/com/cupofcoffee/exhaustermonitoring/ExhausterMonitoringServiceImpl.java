package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExhausterMonitoringServiceImpl implements ExhausterMonitoringService {

    private final ExhausterDao exhausterDao;

    @Override
    public InfoDto getInfo() {

        return InfoDto.builder().data("TEST").build();
    }

    @Override
    public List<Map<String, String>> getMetricsJsonBetweenStartDateAndEndDate(LocalDateTime start, LocalDateTime end) {

        return exhausterDao.getMetricsJsonBetweenStartDateAndEndDate(start, end);
    }

    @Override
    public List<String> getAllMetricsByMetricNameBetweenStartDateAndEndDate(String metricName,
                                                                            LocalDateTime start,
                                                                            LocalDateTime end) {

        return exhausterDao.getAllMetricsByMetricNameBetweenStartDateAndEndDate(metricName, start, end);
    }

    @Override
    public Map<String, String> getLastMetricsJson() {

        return exhausterDao.getLastMetricsJson();
    }

    @Override
    public String getLastMetricByMetricName(String metricName) {

        return exhausterDao.getLastMetricByMetricName(metricName);
    }
}