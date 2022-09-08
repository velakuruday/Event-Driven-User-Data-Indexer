package com.indi.eventapi.integration;

import com.indi.eventapi.dto.UserUpdateDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Durations.FIVE_SECONDS;
import static org.hamcrest.Matchers.equalTo;

class EventApiIntegrationTest extends IntegrationTest {

    @Test
    public void testSuccessfulIndex() throws IOException {
        String message = parseJson("src/test/resources/user_update_test_data.json");
        ProducerRecord<String, String> record = new ProducerRecord<>("user.Updates", message);

        producer.send(record);

        await().atLeast(Durations.TWO_HUNDRED_MILLISECONDS)
                .atMost(FIVE_SECONDS)
                .with()
                .pollDelay(Durations.TWO_HUNDRED_MILLISECONDS)
                .pollInterval(Durations.TWO_HUNDRED_MILLISECONDS)
                .until(() -> elasticsearchHelper.findById("4JY0RBSXVL"
                ).map(UserUpdateDto::getId).orElse(null), equalTo("4JY0RBSXVL"));
    }
}