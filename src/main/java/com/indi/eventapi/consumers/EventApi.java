package com.indi.eventapi.consumers;

import com.indi.eventapi.service.StockUpdateIndexer;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@AllArgsConstructor
public class EventApi {
    private final StockUpdateIndexer indexer;

    private final String CONSUMER_INDEX = "1";

    @KafkaListener(groupId = "stock-update-" + CONSUMER_INDEX, topics = "stock-updates", containerFactory = "containerFactory")
    public void consume(@Payload String message, Acknowledgment ack) {
        indexer.indexUserUpdate(message, ack);
        RequestContextHolder.resetRequestAttributes();
    }
}