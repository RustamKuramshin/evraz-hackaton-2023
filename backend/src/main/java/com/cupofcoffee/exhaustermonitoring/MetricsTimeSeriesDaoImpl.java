package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class MetricsTimeSeriesDaoImpl implements MetricsTimeSeriesDao {

    private final InfluxDB influxDB;
    
    private final CsvMappingLoader csvMappingLoader;

    @Override
    public void saveMetricToInfluxDb(String key, Double value, String moment) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

        Date d = format.parse(moment);
        long milliseconds = d.getTime();

        Map<String, SignalDto> signals = csvMappingLoader.getSignals();

        StringBuilder sb = new StringBuilder("Эксгаустер №");

        Point point = Point.measurement("moments")
                .time(milliseconds, TimeUnit.MILLISECONDS)
                .addField(key, value)
                .tag("Название Эксгаустера", sb.append(signals.get(key).getExhauster()).toString())
                .build();

        influxDB.write(point);
    }
}
