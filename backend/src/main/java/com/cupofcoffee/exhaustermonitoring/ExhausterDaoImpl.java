package com.cupofcoffee.exhaustermonitoring;

import com.mongodb.client.model.Filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.bson.conversions.Bson;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

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

    @Override
    public void getAllExhausterMetricsForAllMachines(Consumer<Object> c) {
        mongoTemplate.getCollection(METRICS_COLLECTION_NAME).watch().forEach(c);
    }

    @Override
    public void getExhausterMetricsByExhausterId(Consumer<Object> c, String exhausterId) {
        Bson filter = Filters.eq(EXHAUSTER_ID_FIELD_NAME, exhausterId);
        mongoTemplate.getCollection(METRICS_COLLECTION_NAME).watch(List.of(filter)).forEach(c);
    }

    @Override
    public List<Map> getExhausterInfoForAllMachines() {
        return mongoTemplate.findAll(Map.class, METRICS_COLLECTION_NAME);
    }

    @Override
    public Map getExhausterInfoByExhausterId(String exhausterId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(EXHAUSTER_ID_FIELD_NAME).is(exhausterId));
        return mongoTemplate.find(query, Map.class, METRICS_COLLECTION_NAME)
          .stream()
          .findFirst()
          .orElse(Map.of());
    }
}
