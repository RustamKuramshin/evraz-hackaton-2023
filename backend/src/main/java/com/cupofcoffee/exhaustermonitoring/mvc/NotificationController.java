package com.cupofcoffee.exhaustermonitoring.mvc;

import com.cupofcoffee.exhaustermonitoring.services.NotificationService;
import com.cupofcoffee.exhaustermonitoring.mvc.dto.ExhausterNotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController("notification-controller")
@RequestMapping(value = "/api/v1")
public class NotificationController {

    private final NotificationService notificationService;

    @RequestMapping(method = RequestMethod.GET, value = "/notifications-grouped")
    @ResponseStatus(HttpStatus.OK)
    public List<ExhausterNotificationDto> getGroupedNotifications(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom) {

        return notificationService.getGroupedNotifications(dateFrom);
    }

}
