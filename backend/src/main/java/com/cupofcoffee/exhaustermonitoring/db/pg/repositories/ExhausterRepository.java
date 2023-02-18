package com.cupofcoffee.exhaustermonitoring.db.pg.repositories;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Exhauster;
import com.cupofcoffee.exhaustermonitoring.db.pg.entities.QExhauster;
import com.cupofcoffee.exhaustermonitoring.db.pg.projections.ExhausterInfo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "exhausters", path = "exhausters",
        excerptProjection = ExhausterInfo.class)
public interface ExhausterRepository extends
        PagingAndSortingRepository<Exhauster, UUID>,
        QuerydslPredicateExecutor<Exhauster>,
        QuerydslBinderCustomizer<QExhauster> {

    default void customize(QuerydslBindings bindings, QExhauster notification) {

    }

}
