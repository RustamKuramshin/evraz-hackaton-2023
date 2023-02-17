package com.cupofcoffee.exhaustermonitoring;

import com.mongodb.client.model.Filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.bson.conversions.Bson;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExhausterDaoImpl implements ExhausterDao {

    private static final String METRICS_COLLECTION_NAME = "metrics-0";

    private static final String EXHAUSTER_ID_FIELD_NAME = "exhausterId";

    private final MongoTemplate mongoTemplate;

    // НОВЫЕ МЕТОДЫ ДЛЯ РЕАЛИЗАЦИИ

    @Override
    public List<Map<String, String>> getMetricsJsonBetweenStartDateAndEndDate(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public List<String> getAllMetricsByMetricNameBetweenStartDateAndEndDate(String metricName, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public Map<String, String> getLastMetricsJson() {
        return null;
    }

    @Override
    public String getLastMetricByMetricName(String metricName) {
        return null;
    }

    @Override
    public void readChangeStream(Consumer<Object> consumer) {

    }

    // РЕАЛИЗАЦИЯ СТРАХ МЕТОДОВ (БРАТЬ ДЛЯ ПРИМЕРА)

    public void getExhausterMetricsByExhausterId(Consumer<Object> c, String exhausterId) {
        Bson filter = Filters.eq(EXHAUSTER_ID_FIELD_NAME, exhausterId);
        mongoTemplate.getCollection(METRICS_COLLECTION_NAME).watch(List.of(filter)).forEach(c);
    }

    public List<Map> getExhausterInfoForAllMachines() {
        return mongoTemplate.findAll(Map.class, METRICS_COLLECTION_NAME);
    }

    public void getAllExhausterMetricsForAllMachines(Consumer<Object> c) {
        mongoTemplate.getCollection(METRICS_COLLECTION_NAME).watch().forEach(c);
    }

    public Map getExhausterInfoByExhausterId(String exhausterId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(EXHAUSTER_ID_FIELD_NAME).is(exhausterId));
        return mongoTemplate.find(query, Map.class, METRICS_COLLECTION_NAME)
                .stream()
                .findFirst()
                .orElse(Map.of());
    }
}
