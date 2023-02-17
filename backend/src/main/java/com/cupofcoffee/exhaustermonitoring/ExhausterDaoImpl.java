package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExhausterDaoImpl implements ExhausterDao {

//  @Override
  public List<Object> getAllExhausterMetricsForAllMachines(Consumer<Object> c) {
    return null;
  }

//  @Override
  public Object getExhausterMetricsByExhausterId(Consumer<Object> c, String exhausterId) {
    return null;
  }

//  @Override
  public List<Object> getExhausterInfoForAllMachines() {
    return null;
  }

//  @Override
  public Object getExhausterInfoByExhausterId(String exhausterId) {
    return null;
  }

  @Override
  public List<Map<String, String>> getMetricsJsonBetweenStartDateAndEndDate(LocalDateTime start, LocalDateTime end) {
    return null;
  }

  @Override
  public List<String> getAllMetricsByMetricNameBetweenStartDateAndEndDate(String metricName, LocalDateTime start, LocalDateTime end) {
    return null;
  }

  @Override
  public Map<String, String> getLastMetricsJson() {
    return null;
  }

  @Override
  public String getLastMetricByMetricName(String metricName) {
    return null;
  }

  @Override
  public void readChangeStream(Consumer<Object> consumer) {

  }
}
