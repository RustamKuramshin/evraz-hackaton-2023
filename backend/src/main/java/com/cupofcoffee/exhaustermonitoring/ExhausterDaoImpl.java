package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExhausterDaoImpl implements ExhausterDao {

    @Value("${mongo.collection}")
    private String metricsCollectionName;

    @Value("${mongo.moment-field}")
    private String momentFieldName;

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Map<String, String>> getMetricsJsonBetweenStartDateAndEndDate(LocalDateTime start, LocalDateTime end) {
        AggregationOperation match = Aggregation.match(
                Criteria.where(momentFieldName).gte(start).and(momentFieldName).lte(end)
        );
        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, momentFieldName);
        TypedAggregation<Map> pipeline = Aggregation.newAggregation(Map.class, match, sort);
        return mongoTemplate.aggregate(pipeline, metricsCollectionName, Map.class)
                .getMappedResults()
                .stream()
                .map(this::convertResult)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllMetricsByMetricNameBetweenStartDateAndEndDate(String metricName, LocalDateTime start, LocalDateTime end) {
        AggregationOperation match = Aggregation.match(
                Criteria.where(momentFieldName).gte(start).and(momentFieldName).lte(end)
        );
        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, momentFieldName);
        AggregationOperation project = Aggregation.project(metricName);
        TypedAggregation<String> pipeline = Aggregation.newAggregation(String.class, match, sort, project);
        return mongoTemplate.aggregate(pipeline, metricsCollectionName, String.class).getMappedResults();
    }

    @Override
    public Map<String, String> getLastMetricsJson() {
        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, momentFieldName);
        TypedAggregation<Map> pipeline = Aggregation.newAggregation(Map.class, sort);
        return mongoTemplate.aggregate(pipeline, metricsCollectionName, Map.class)
                .getMappedResults()
                .stream()
                .findFirst()
                .map(this::convertResult)
                .orElse(Map.of());
    }

    @Override
    public String getLastMetricByMetricName(String metricName) {
        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, momentFieldName);
        AggregationOperation project = Aggregation.project(metricName);
        TypedAggregation<String> pipeline = Aggregation.newAggregation(String.class, sort, project);
        return mongoTemplate.aggregate(pipeline, metricsCollectionName, String.class)
                .getMappedResults()
                .stream()
                .findFirst()
                .orElse("");
    }

    private Map<String, String> convertResult(Map map) {
        Map<String, String> result = new HashMap<>();
        for (Object key: map.keySet()) {
            result.put(key.toString(), map.get(key).toString());
        }
        return result;
    }
}
