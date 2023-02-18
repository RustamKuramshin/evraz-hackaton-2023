package com.cupofcoffee.exhaustermonitoring.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExhausterNotificationDto {
    private String name;
    private List<ExhausterError> errors;

}
