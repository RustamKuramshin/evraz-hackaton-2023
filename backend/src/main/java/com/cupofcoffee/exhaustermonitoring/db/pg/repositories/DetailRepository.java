package com.cupofcoffee.exhaustermonitoring.db.pg.repositories;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Detail;
import com.cupofcoffee.exhaustermonitoring.db.pg.entities.QDetail;
import com.cupofcoffee.exhaustermonitoring.db.pg.projections.DetailInfo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "details", path = "details",
        excerptProjection = DetailInfo.class)
public interface DetailRepository extends
        PagingAndSortingRepository<Detail, UUID>,
        QuerydslPredicateExecutor<Detail>,
        QuerydslBinderCustomizer<QDetail> {

    default void customize(QuerydslBindings bindings, QDetail notification) {

    }

}
