package com.cupofcoffee.exhaustermonitoring;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            Map<String, SignalMapping> collectedSignalMapping = readExcelMappings().stream()
                    .collect(Collectors.toMap(SignalMapping::getKafkaSignal, Function.identity()));

            this.signals = new CsvToBeanBuilder<SignalDto>(reader)
                    .withType(SignalDto.class)
                    .build()
                    .parse()
                    .stream()
                    .peek(signalDto -> {
                        SignalMapping signalMapping = collectedSignalMapping.get(signalDto.getPlace());
                        if (signalMapping == null) {
                            log.warn("Unknown mapping for place {}", signalDto.getPlace());
                        } else {
                            signalDto.setGear(signalMapping.getGear());
                            signalDto.setSignalMetrics(signalMapping.getSignalMetrics());
                            signalDto.setSignalTypeDescr(signalMapping.getSignalTypeDescr());
                            signalDto.setSignalType(signalMapping.getSignalType());
                        }

                    })
                    .collect(Collectors.toMap(SignalDto::getPlace, Function.identity()));

            int b = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<SignalMapping> readExcelMappings() throws IOException {
        FileInputStream file = new FileInputStream(ResourceUtils.getFile("classpath:signals_mapping.xlsx"));

        List<SignalMapping> signalMappings = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        for (int sn = 0; sn < workbook.getNumberOfSheets(); sn++) {
            XSSFSheet sheet = workbook.getSheetAt(sn);

            String group1 = null;
            String group2 = null;
            String group3 = null;
            for (int ri = 1; ri < sheet.getLastRowNum(); ri++) {
                XSSFRow row = sheet.getRow(ri);

                String cell1 = group1 = getCellValueOrDefault(row.getCell(0), group1);
                String cell2 = group2 = getCellValueOrDefault(row.getCell(1), group2);
                String cell3 = group3 = getCellValueOrDefault(row.getCell(2), group3);
                String cell4 = getCellValueOrDefault(row.getCell(3), null);
                String cell5 = getCellValueOrDefault(row.getCell(4), null);


                SignalMapping mapping = new SignalMapping(cell1, cell2, cell3, cell4, cell5);
                signalMappings.add(mapping);
            }
        }

        return signalMappings;
    }

    private String getCellValueOrDefault(XSSFCell cell, String defaultValue) {
        if (cell.getCellType() == CellType.BLANK) {
            return defaultValue;
        }
        return cell.getStringCellValue();
    }

    public Map<String, SignalDto> getSignals() {
        return signals;
    }

    @Data
    @AllArgsConstructor
    private class SignalMapping {
        private String gear;
        private String signalMetrics;
        private String signalTypeDescr;
        private String signalType;
        private String kafkaSignal;

    }
}
