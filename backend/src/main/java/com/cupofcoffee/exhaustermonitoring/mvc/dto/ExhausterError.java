package com.cupofcoffee.exhaustermonitoring.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExhausterError {
    private Long count;
    private LocalDateTime firstDate;
    private LocalDateTime lastDate;
    private String description;

}
