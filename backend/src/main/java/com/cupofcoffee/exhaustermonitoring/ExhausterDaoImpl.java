package com.cupofcoffee.exhaustermonitoring;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.model.Filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.bson.conversions.Bson;
import org.bson.Document;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public List<Object> getAllExhausterMetricsForAllMachines(Consumer<Object> c) {
        return processChangeStream(mongoTemplate.getCollection(METRICS_COLLECTION_NAME).watch(), c);
    }

    @Override
    public Object getExhausterMetricsByExhausterId(Consumer<Object> c, String exhausterId) {
        Bson filter = Filters.eq(EXHAUSTER_ID_FIELD_NAME, exhausterId);
        return processChangeStream(mongoTemplate.getCollection(METRICS_COLLECTION_NAME).watch(List.of(filter)), c);
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

    private List<Object> processChangeStream(ChangeStreamIterable<Document> changeStream, Consumer<Object> c) {
        changeStream.forEach(c);
        List<Object> metrics = new ArrayList<>();
        changeStream.into(metrics);
        return metrics;
    }
}
