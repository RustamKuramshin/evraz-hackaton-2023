package com.cupofcoffee.exhaustermonitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class InfoHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        log.info("WS RECEIVED MESSAGE: {}", message.getPayload());

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {

            int num = getRandomNumber(10, 10000);
            InfoDto infoDto = InfoDto.builder().data(String.valueOf(num)).build();

            try {
                String msg = mapper.writeValueAsString(infoDto);
                log.info("WS MESSAGE SENT: {}", msg);
                session.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 1000, 500, TimeUnit.MILLISECONDS);
    }
}
