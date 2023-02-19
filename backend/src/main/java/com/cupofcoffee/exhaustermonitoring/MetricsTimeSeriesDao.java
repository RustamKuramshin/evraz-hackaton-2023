package com.cupofcoffee.exhaustermonitoring;

import java.text.ParseException;
import java.util.Map;

public interface MetricsTimeSeriesDao {

    void saveMetricToInfluxDb(String key, Double value, String moment) throws ParseException;
}
