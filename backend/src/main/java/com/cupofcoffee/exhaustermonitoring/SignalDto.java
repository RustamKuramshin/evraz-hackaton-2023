package com.cupofcoffee.exhaustermonitoring;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class SignalDto {

    @CsvBindByName(column = "place")
    private String place;

    @CsvBindByName(column = "type")
    private String type;

    @CsvBindByName(column = "comment")
    private String comment;

    @CsvBindByName(column = "exhauster")
    private String exhauster;

    @CsvBindByName(column = "active")
    private String active;
}