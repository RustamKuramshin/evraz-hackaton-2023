package com.cupofcoffee.exhaustermonitoring.db.pg.repositories;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Notification;
import com.cupofcoffee.exhaustermonitoring.db.pg.entities.QNotification;
import com.cupofcoffee.exhaustermonitoring.db.pg.projections.NotificationInfo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "notifications", path = "notifications",
    excerptProjection = NotificationInfo.class)
public interface NotificationRepository extends
    PagingAndSortingRepository<Notification, UUID>,
    QuerydslPredicateExecutor<Notification>,
    QuerydslBinderCustomizer<QNotification> {

    default void customize(QuerydslBindings bindings, QNotification notification) {

    }
}