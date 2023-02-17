package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController("influx-controller")
@RequestMapping(value = "/api/v1/influx")
public class InfluxController {

    private final InfluxService influxService;

    @RequestMapping(method = RequestMethod.POST, value = "/input")
    @ResponseStatus(HttpStatus.OK)
    public String parseString(@RequestBody String data) {
      return influxService.parseString(data);
    }



}
