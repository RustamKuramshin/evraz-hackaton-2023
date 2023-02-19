package com.cupofcoffee.exhaustermonitoring;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;

public interface MetricsTimeSeriesService {
    void saveToInfluxDb(String metricsJson) throws JsonProcessingException, ParseException;
}
