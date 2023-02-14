package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController("exhauster-monitoring-controller")
@RequestMapping(value = "/api/v1")
public class ExhausterMonitoringController {

    private final ExhausterMonitoringService exhausterMonitoringService;

    @RequestMapping(method = RequestMethod.GET, value = "/info")
    @ResponseStatus(HttpStatus.OK)
    public InfoDto getInfo() {
        return exhausterMonitoringService.getInfo();
    }
}
