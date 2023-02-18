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

    // Принимает строки и переводит в нужнйый формат
    @Override
    public void saveMetricToInfluxDb(String key, Double value, String moment) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

        Date d = format.parse(moment);
        long milliseconds = d.getTime();

        Point point = Point.measurement("moment")
                .time(milliseconds, TimeUnit.MILLISECONDS)
                .addField(key, value)
                .build();

        influxDB.write(point);
    }
}
