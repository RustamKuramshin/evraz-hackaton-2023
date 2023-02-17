package com.cupofcoffee.exhaustermonitoring;

import java.util.Map;

public interface MetricsTimeSeriesDao {

    void saveToInfluxDb(Map<String, String> metrics);
}
