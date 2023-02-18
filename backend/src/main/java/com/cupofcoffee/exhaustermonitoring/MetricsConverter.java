package com.cupofcoffee.exhaustermonitoring;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.Map;

public interface MetricsConverter {

    HashMap<String,Object> convertMetricsToMap(String metrics) throws JsonProcessingException;
}
