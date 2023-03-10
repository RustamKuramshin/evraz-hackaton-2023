package com.cupofcoffee.exhaustermonitoring;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.Subscription;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class MetricsHandler extends TextWebSocketHandler {

    @Value("${mongo.collection}")
    private String collection;

    @Value("${spring.data.mongodb.database}")
    private String db;

    private final MessageListenerContainer container;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        log.info("WS RECEIVED MESSAGE: {}", message.getPayload());

        container.start();

        MessageListener<ChangeStreamDocument<Document>, SecurityProperties.User> listener = message1 -> {

            try {
                Document fullDocument = message1.getRaw().getFullDocument();
                fullDocument.remove("_id");
                String metrics = fullDocument.toJson();
                log.info("WS MESSAGE SENT: {}", metrics);
                session.sendMessage(new TextMessage(metrics));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        };

        ChangeStreamRequest.ChangeStreamRequestOptions options =
            new ChangeStreamRequest.ChangeStreamRequestOptions(
                db,
                collection,
                ChangeStreamOptions.empty());

        Subscription subscription = container.register(
            new ChangeStreamRequest<>(listener, options),
            SecurityProperties.User.class);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable t) {
        log.error("WS TRANSPORT ERROR", t);
        container.stop();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        log.info("WS CONNECTION CLOSED");
        container.stop();
    }
}
