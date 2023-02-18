package com.cupofcoffee.exhaustermonitoring;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MetricsTimeSeriesService {
    void saveToInfluxDb(String metricsJson) throws JsonProcessingException;
}
