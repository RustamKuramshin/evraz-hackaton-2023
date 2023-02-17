package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExhausterDaoImpl {

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
}
