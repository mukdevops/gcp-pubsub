package com.sprintin.assessment.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintin.assessment.config.SprintInConfiguration;
import com.sprintin.assessment.dto.BucketNotification;
import com.sprintin.assessment.service.FileReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BucketNotificationPubSubConsumer extends AbstractBasePubSubConsumer<BucketNotification> {

    private final FileReaderService fileReaderService;

    private final SprintInConfiguration sprintInConfiguration;

    public BucketNotificationPubSubConsumer(final ObjectMapper objectMapper, final FileReaderService fileReaderService,
                                            final SprintInConfiguration sprintInConfiguration) {
        super(objectMapper);
        this.fileReaderService = fileReaderService;
        this.sprintInConfiguration = sprintInConfiguration;
    }

    @Override
    public Class<BucketNotification> getMessageClass() {
        return BucketNotification.class;
    }

    @Override
    public String getSubscription() {
        return sprintInConfiguration.getSubscriptionName();
    }

    @Override
    public void processMessage(final BucketNotification bucketNotification) {

        log.info(" Inside processMessage === test 1");
        System.out.println(bucketNotification.getBucket());
        System.out.println(bucketNotification.getName());
    }
}
