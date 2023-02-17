package com.cupofcoffee.exhaustermonitoring;

import java.util.Map;

public interface MetricsConverter {

    Map<String, String> convertMetricsToMap(String metrics);
}
