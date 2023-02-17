package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONSerializer;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.*;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class InfluxServiceImpl implements InfluxService {

  @Override
  public String parseString(String data) {

//    InfluxDB influxDB = InfluxDBFactory.connect("http://influxdb:8086", "admin", "admin");
    InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "admin", "admin");

    Pong response = influxDB.ping();
    if (response.getVersion().equalsIgnoreCase("unknown")) {
      log.error("Error pinging server.");
      return "Error on connection";
    }

    String dbName = "influx";
    influxDB.enableBatch(100, 200, TimeUnit.MILLISECONDS);
    influxDB.createDatabase(dbName);
    influxDB.createRetentionPolicy(
      "defaultPolicy", dbName, "30d", 1, true);

    influxDB.setRetentionPolicy("defaultPolicy");
    influxDB.setDatabase(dbName);

    influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);


    Point point = Point.measurement("memory")
      .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
      .addField("name", "server1")
      .addField("free", 4743656L)
      .addField("used", 1015096L)
      .addField("buffer", 1010467L)
      .build();


    influxDB.write(point);

    BatchPoints batchPoints = BatchPoints
      .database(dbName)
      .retentionPolicy("defaultPolicy")
      .build();

    Point point1 = Point.measurement("memory")
      .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
      .addField("name", "server1")
      .addField("free", 4743656L)
      .addField("used", 1015096L)
      .addField("buffer", 1010467L)
      .build();

    Point point2 = Point.measurement("memory")
      .time(System.currentTimeMillis() - 100, TimeUnit.MILLISECONDS)
      .addField("name", "server1")
      .addField("free", 4743696L)
      .addField("used", 1016096L)
      .addField("buffer", 1008467L)
      .build();

    batchPoints.point(point1);
    batchPoints.point(point2);
    influxDB.write(batchPoints);


    Query query = new Query("Select * from memory", dbName);

    QueryResult queryResult = influxDB.query(query);

    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
    List<MemoryPoint> memoryPointList = resultMapper.toPOJO(queryResult, MemoryPoint.class);

    return "";
  }
}
