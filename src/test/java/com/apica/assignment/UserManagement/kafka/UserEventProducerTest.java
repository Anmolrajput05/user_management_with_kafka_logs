package com.apica.assignment.UserManagement.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;

public class UserEventProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private UserEventProducer userEventProducer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendUserEvent() {
        // Mocking the message
        String message = "Test message";

        // Calling the sendUserEvent method
        userEventProducer.sendUserEvent(message);

        // Verifying that kafkaTemplate.send() is called with the correct parameters
        verify(kafkaTemplate, times(1)).send(eq("user-events"), eq(message));
    }
}
