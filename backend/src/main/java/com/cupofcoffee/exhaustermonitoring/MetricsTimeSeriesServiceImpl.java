package com.cupofcoffee.exhaustermonitoring;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class MetricsTimeSeriesServiceImpl implements MetricsTimeSeriesService {

    private final MetricsTimeSeriesDao metricsTimeSeriesDao;

    private final MetricsConverter metricsConverter;

    private final CsvMappingLoader csvMappingLoader;

    private final InfluxDB influxDB;

    @Value("${influx-db.policy}")
    private String influxDbPolicy;

    @Value("${influx-db.dbname}")
    private String influxDbDbname;

    @Async
    @Override
    public void saveToInfluxDb(String metricsJson) throws JsonProcessingException, ParseException {
        HashMap<String, Object> metricsMap = metricsConverter.convertMetricsToMap(metricsJson);

        String moment = (String) metricsMap.get("moment");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

        Date d = format.parse(moment);
        long milliseconds = d.getTime();

        Map<String, SignalDto> signals = csvMappingLoader.getSignals();

        metricsMap.remove("moment");

        BatchPoints batchPoints = BatchPoints
                .database(influxDbDbname)
                .retentionPolicy(influxDbPolicy)
                .build();

        metricsMap.forEach((k, v) -> {

            SignalDto oneSignal = signals.get(k);

            if (oneSignal != null) {

                Point.Builder pointBuilder = Point.measurement("metrics")
                        .time(milliseconds + 3 * 3600000, TimeUnit.MILLISECONDS)
                        .addField(k, (Double) v);

                if (oneSignal.getExhauster() != null) {
                    pointBuilder.tag("Название Эксгаустера", String.format("Эксгаустер №%s", oneSignal.getExhauster()));
                }

                if (oneSignal.getSignalMetrics() != null) {
                    pointBuilder.tag("Метрика", oneSignal.getSignalMetrics());
                }

                if (oneSignal.getType() != null) {
                    pointBuilder.tag("Тип", oneSignal.getType());
                }

                if (oneSignal.getComment() != null) {
                    pointBuilder.tag("Название", oneSignal.getComment());
                }

                if (oneSignal.getActive() != null) {
                    pointBuilder.tag("Активность", oneSignal.getActive());
                }

                if (oneSignal.getGear() != null) {
                    pointBuilder.tag("Узел", oneSignal.getGear());
                }

                if (oneSignal.getSignalType() != null) {
                    pointBuilder.tag("Тип сигнала", oneSignal.getSignalType());
                }

                if (oneSignal.getSignalTypeDescr() != null) {
                    pointBuilder.tag("Описание тип сигнала", oneSignal.getSignalTypeDescr());
                }


                batchPoints.point(pointBuilder.build());
            }
        });

        influxDB.write(batchPoints);
    }
}
