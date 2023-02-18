package com.cupofcoffee.exhaustermonitoring.db.pg.repositories;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Notification;
import com.cupofcoffee.exhaustermonitoring.db.pg.entities.QNotification;
import com.cupofcoffee.exhaustermonitoring.db.pg.projections.NotificationGroupInfo;
import com.cupofcoffee.exhaustermonitoring.db.pg.projections.NotificationInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "notifications", path = "notifications",
    excerptProjection = NotificationInfo.class)
public interface NotificationRepository extends
    PagingAndSortingRepository<Notification, UUID>,
    QuerydslPredicateExecutor<Notification>,
    QuerydslBinderCustomizer<QNotification> {

    default void customize(QuerydslBindings bindings, QNotification notification) {

    }

    @Query(value = "select " +
            "       e.uuid as exhausterId, " +
            "       e.name as exhausterName, " +
            "       d.uuid as detailId, " +
            "       d.name as detailName, " +
            "       n.notificationType as notificationType, " +
            "       min(n.createdAt) as firstDate, " +
            "       max(n.createdAt) as lastDate, " +
            "       count(n)        as count " +
            "from notification n " +
            "    left join detail d on d.uuid = n.detail.uuid " +
            "    left join exhauster e on e.uuid = d.exhauster.uuid " +
            "where n.createdAt > :dateFrom " +
            "group by e.uuid, d.uuid, n.notificationType")
    List<NotificationGroupInfo> getGroupedNotifications(LocalDateTime dateFrom);

}
