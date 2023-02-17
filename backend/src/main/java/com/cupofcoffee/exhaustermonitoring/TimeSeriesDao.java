package com.cupofcoffee.exhaustermonitoring;

import java.util.Map;

public interface TimeSeriesDao {

    void saveToInfluxDb(Map<String, String> metrics);
}
