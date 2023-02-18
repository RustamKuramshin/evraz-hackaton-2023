package com.cupofcoffee.exhaustermonitoring;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class CsvMappingLoader {

    private Map<String, SignalDto> signals = new HashMap<>();

    @PostConstruct
    public void init() throws FileNotFoundException {

        File fileWithSignals = ResourceUtils.getFile("classpath:signals_kafka.csv");
        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileWithSignals))
                .withCSVParser(rfc4180Parser)
                .build()
        ) {
            this.signals = new CsvToBeanBuilder<SignalDto>(reader)
                    .withType(SignalDto.class)
                    .build()
                    .parse()
                    .stream()
                    .collect(Collectors.toMap(SignalDto::getPlace, Function.identity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, SignalDto> getSignals() {
        return signals;
    }
}
