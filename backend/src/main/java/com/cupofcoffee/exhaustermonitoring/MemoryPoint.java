package com.cupofcoffee.exhaustermonitoring;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "memory")
public class MemoryPoint {

  @Column(name = "time")
  private Instant time;

  @Column(name = "name")
  private String name;

  @Column(name = "free")
  private Long free;

  @Column(name = "used")
  private Long used;

  @Column(name = "buffer")
  private Long buffer;
}