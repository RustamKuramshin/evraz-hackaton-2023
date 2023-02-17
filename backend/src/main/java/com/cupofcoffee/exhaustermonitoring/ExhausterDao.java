package com.cupofcoffee.exhaustermonitoring;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface ExhausterDao {

    // Метод для подключения к change stream'у mongodb базы metrics
    // для получения метрик всех дымоходов по всем агломашинам
    // Consumer нужен для работы с либой которая работает с change stream
    void getAllExhausterMetricsForAllMachines(Consumer<Object> c);

    // Метод для подключения к change stream'у mongodb базы metrics
    // для получения метрик конкретного дымохода по его id
    // Consumer нужен для работы с либой которая работает с change stream
    void getExhausterMetricsByExhausterId(Consumer<Object> c, String exhausterId);

    // Метод для получения списка данных всех дымоходов по всем агломашинам
    List<Map> getExhausterInfoForAllMachines();

    // Метод для получения данных конкретного дымохода по всем агломашинам
    Map getExhausterInfoByExhausterId(String exhausterId);
}
