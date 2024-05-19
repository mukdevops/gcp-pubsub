package com.sprintin.assessment.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.sprintin.assessment.pubsub.AbstractBasePubSubConsumer;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class PubSubConfig {

    private final PubSubTemplate pubSubTemplate;


    private final Set<AbstractBasePubSubConsumer<?>> pubSubConsumers;

    @EventListener(ApplicationReadyEvent.class)
    public void handleSubscriptions() {
    final ExecutorService executorService = Executors.newFixedThreadPool(2);
        pubSubTemplate.getPubSubSubscriberTemplate().setAsyncPullExecutor(executorService);
        pubSubConsumers.forEach(this::subscribe);
    }

  public void subscribe(final AbstractBasePubSubConsumer<?> consumer) {
        pubSubTemplate.subscribe(consumer.getSubscription(), consumer.consumer());
    }

}
