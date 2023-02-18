package com.cupofcoffee.exhaustermonitoring;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignalDto {

    private String type;

    private String comment;

    private String exhauster;

    private String active;
}