package com.cupofcoffee.exhaustermonitoring;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ExhausterMonitoringService {

    InfoDto getInfo();

    List<Map<String, String>> getMetricsJsonBetweenStartDateAndEndDate(LocalDateTime start, LocalDateTime end);


    List<String> getAllMetricsByMetricNameBetweenStartDateAndEndDate(String metricName, LocalDateTime start, LocalDateTime end);


    Map<String, String> getLastMetricsJson();


    String getLastMetricByMetricName(String metricName);
}