package com.cupofcoffee.exhaustermonitoring.db.pg.projections;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Notification;
import com.cupofcoffee.exhaustermonitoring.db.pg.entities.NotificationType;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.UUID;

@Projection(name = "notificationInfo", types = {Notification.class})
public interface NotificationInfo {

    UUID getUuid();

    LocalDateTime getCreatedAt();

    String getExhausterId();

    String getPartId();

    NotificationType getNotificationType();

    String getDescription();
}