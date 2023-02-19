package com.cupofcoffee.exhaustermonitoring;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ExhausterDao {

  /**
   * Метод, который возвращает список json'ов с метриками за интервал дат
   * @param start - начальная дата-время
   * @param end - конечная дата-время
   * @return список json'ов с метриками за период времени
   */
  List<Map<String, String>> getMetricsJsonBetweenStartDateAndEndDate(LocalDateTime start, LocalDateTime end);

  /**
   * Метод, который возвращает список значений конкретной метрики за интервал дат
   * @param metricName - название метрики, например "SM_Exgauster\\[0:10]"
   * @param start - начальная дата-время
   * @param end - конечная дата-время
   * @return список значений метрики за период времени
   */
  List<String> getAllMetricsByMetricNameBetweenStartDateAndEndDate(String metricName, LocalDateTime start, LocalDateTime end);

  /**
   * Метод возвращает последний по времени в поле moment json с метриками
   * @return последний json с метриками
   */
  Map<String, String> getLastMetricsJson();

  /**
   * Метод возвращает последнее значение метрики по её имени
   * @return последнее значение метрики по ее имени
   */
  String getLastMetricByMetricName(String metricName);
}
