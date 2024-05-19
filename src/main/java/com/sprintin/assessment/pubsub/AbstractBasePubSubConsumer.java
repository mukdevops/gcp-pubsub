package com.sprintin.assessment.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.function.Consumer;

@Slf4j
@Validated
@AllArgsConstructor
public abstract class AbstractBasePubSubConsumer<T> {

    private final ObjectMapper objectMapper;

    public abstract Class<T> getMessageClass();

    public abstract String getSubscription();

    public Consumer<BasicAcknowledgeablePubsubMessage> consumer() {
        return this::consume;
    }

    public abstract void processMessage(@Valid T dto);

    public void consume(final BasicAcknowledgeablePubsubMessage basicAcknowledgeablePubsubMessage) {

        log.info(" inside consume method ===== ");
        boolean ackMessage = true;

        try {
            final T message = objectMapper.readValue(basicAcknowledgeablePubsubMessage.getPubsubMessage().getData().toStringUtf8(),
                    getMessageClass());
            processMessage(message);
        } catch (final JsonProcessingException jsonProcessingException) {
            log.error("Failed to parse JSON" + jsonProcessingException.getMessage());
        } catch (final ConstraintViolationException constraintViolationException) {
            log.error("Invalid Pubsub entry" + constraintViolationException.getMessage());
        } catch (final Exception exception) {
            ackMessage = false;
            log.error("exception occurred while consuming Pub/Sub message");
        } finally {
            determineAckMessageResponse(basicAcknowledgeablePubsubMessage, ackMessage);
        }
    }

    protected void determineAckMessageResponse(final BasicAcknowledgeablePubsubMessage basicAcknowledgeablePubsubMessage,
                                               final boolean ackMessage) {
        if (ackMessage) {
            basicAcknowledgeablePubsubMessage.ack();
        } else {
            basicAcknowledgeablePubsubMessage.nack();
        }

    }
}
