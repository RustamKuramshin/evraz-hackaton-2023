package com.cupofcoffee.exhaustermonitoring.db.pg.projections;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public interface NotificationGroupInfo {
    UUID getExhausterId();
    String getExhausterName();
    UUID getDetailId();
    String getDetailName();
    NotificationType getNotificationType();
    LocalDateTime getFirstDate();
    LocalDateTime getLastDate();
    Long getCount();
}
