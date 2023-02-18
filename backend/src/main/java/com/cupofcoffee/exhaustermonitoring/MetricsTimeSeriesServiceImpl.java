package com.cupofcoffee.exhaustermonitoring;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class MetricsTimeSeriesServiceImpl implements MetricsTimeSeriesService {

    private final MetricsTimeSeriesDao metricsTimeSeriesDao;

    private final MetricsConverter metricsConverter;

    @Async
    @Override
    public void saveToInfluxDb(String metricsJson) throws JsonProcessingException {
        HashMap<String, Object> metricsMap = metricsConverter.convertMetricsToMap(metricsJson);

        String moment = (String) metricsMap.get("moment");

        metricsMap.remove("moment");

        metricsMap.forEach((metricsKey, metricsValue) -> {

            try {
                metricsTimeSeriesDao.saveMetricToInfluxDb(metricsKey, (Double) metricsValue, moment);
            } catch (ParseException ex) {
                log.error(ex.getMessage());
            }
        });
    }
}
