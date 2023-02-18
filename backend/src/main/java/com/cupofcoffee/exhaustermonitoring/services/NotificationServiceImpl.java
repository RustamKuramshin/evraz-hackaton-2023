package com.cupofcoffee.exhaustermonitoring.services;

import com.cupofcoffee.exhaustermonitoring.db.pg.projections.NotificationGroupInfo;
import com.cupofcoffee.exhaustermonitoring.db.pg.repositories.NotificationRepository;
import com.cupofcoffee.exhaustermonitoring.mvc.dto.ExhausterError;
import com.cupofcoffee.exhaustermonitoring.mvc.dto.ExhausterNotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository repository;

    @Override
    public List<ExhausterNotificationDto> getGroupedNotifications(LocalDateTime dateFrom) {
        Map<String, List<NotificationGroupInfo>> notificationByExhauster = repository.getGroupedNotifications(dateFrom)
                .stream().collect(Collectors.groupingBy(NotificationGroupInfo::getExhausterName));

        List<ExhausterNotificationDto> resultList = new ArrayList<>();
        notificationByExhauster.forEach((k, v) -> {

            List<ExhausterError> errors = v.stream()
                    .map(i -> new ExhausterError(
                            i.getCount(), i.getFirstDate(), i.getLastDate(),
                            String.format("У датели: '%s' обнаружена проблема: '%s'. Проведите ремонтные работы",
                                    i.getDetailName(), i.getNotificationType().getDescription())
                            ))
                    .sorted(Comparator.comparing(ExhausterError::getLastDate).reversed())
                    .collect(Collectors.toList());

            resultList.add(new ExhausterNotificationDto(k, errors));
        });

        return resultList;
    }

}
