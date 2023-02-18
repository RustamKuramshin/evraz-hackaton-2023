package com.cupofcoffee.exhaustermonitoring.services;

import com.cupofcoffee.exhaustermonitoring.mvc.dto.ExhausterNotificationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {
    List<ExhausterNotificationDto> getGroupedNotifications(LocalDateTime dateFrom);
}
