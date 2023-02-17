package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class MetricsHandler extends TextWebSocketHandler {

    private final ExhausterDao exhausterDao;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        log.info("WS RECEIVED MESSAGE: {}", message.getPayload());

        exhausterDao.readChangeStream(data -> {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("WS MESSAGE SENT: {}", data);
                }

                session.sendMessage(new TextMessage(String.valueOf(data)));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        });

    }
}
