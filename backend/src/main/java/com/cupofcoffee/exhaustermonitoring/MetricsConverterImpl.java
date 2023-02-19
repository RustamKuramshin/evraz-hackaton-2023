package com.cupofcoffee.exhaustermonitoring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class MetricsConverterImpl implements MetricsConverter {

    private final ObjectMapper mapper;

    @Override
    public HashMap<String,Object> convertMetricsToMap(String metrics) throws JsonProcessingException {
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<>() {};
        return mapper.readValue(metrics, typeRef);
    }
}
