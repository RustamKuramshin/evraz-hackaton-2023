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

    private final MetricsConverter metricsConverter;

    private final CsvMappingLoader csvMappingLoader;

    private final InfluxDB influxDB;

    @Value("${influx-db.policy}")
    private String influxDbPolicy;

    @Value("${influx-db.dbname}")
    private String influxDbDbname;

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
                        .time(milliseconds, TimeUnit.MILLISECONDS)
                        .addField(k, (Double) v);

                if (oneSignal.getExhauster() != null) {
                    pointBuilder.tag("???????????????? ??????????????????????", String.format("???????????????????? ???%s", oneSignal.getExhauster()));
                }

                if (oneSignal.getSignalMetrics() != null) {
                    pointBuilder.tag("??????????????", oneSignal.getSignalMetrics());
                }

                if (oneSignal.getType() != null) {
                    pointBuilder.tag("??????", oneSignal.getType());
                }

                if (oneSignal.getComment() != null) {
                    pointBuilder.tag("????????????????", oneSignal.getComment());
                }

                if (oneSignal.getActive() != null) {
                    pointBuilder.tag("????????????????????", oneSignal.getActive());
                }

                if (oneSignal.getGear() != null) {
                    pointBuilder.tag("????????", oneSignal.getGear());
                }

                if (oneSignal.getSignalType() != null) {
                    pointBuilder.tag("?????? ??????????????", oneSignal.getSignalType());
                }

                if (oneSignal.getSignalTypeDescr() != null) {
                    pointBuilder.tag("???????????????? ?????? ??????????????", oneSignal.getSignalTypeDescr());
                }


                batchPoints.point(pointBuilder.build());
            }
        });

        influxDB.write(batchPoints);
    }
}
