package com.sprintin.assessment.pubsub;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class SprintinEventListener {

//    private final Storage storage;
//
//    private SprintInConfiguration sprintInConfiguration;
//
//    private String projectId;
//
//    private String sprintinTopicName;
//
//    private PubSubTemplate pubSubTemplate;
//
//    private Executor pubSubExecutor;
//
//    private Set<AbstractBasePubSubConsumer<?>> pubSubConsumers;
//
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void handleSubscriptions() {
//        pubSubTemplate.getPubSubSubscriberTemplate().setAckExecutor(pubSubExecutor);
//        pubSubConsumers.forEach(this::subscribe);
//    }
//
//    public void subscribe(final AbstractBasePubSubConsumer<?> consumer) {
//        pubSubTemplate.subscribe(consumer.getSubscription(), consumer.consumer());
//    }

}
