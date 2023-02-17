package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExhausterMonitoringServiceImpl implements ExhausterMonitoringService {

    private final ExhausterDao exhausterDao;

    @Override
    public InfoDto getInfo() {
        return InfoDto.builder().data("TEST").build();
    }
}
