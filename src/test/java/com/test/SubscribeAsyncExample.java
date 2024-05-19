package com.test;


import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SubscribeAsyncExample {
    public static void main(final String... args) throws Exception {
        // TODO(developer): Replace these variables before running the sample.
        final String projectId = "hale-sentry-423219-k9";
        final String subscriptionId = "SUB_SPRINTIN_MESSAGE";

        subscribeAsyncExample(projectId, subscriptionId);
    }

    public static void subscribeAsyncExample(final String projectId, final String subscriptionId) {
        final ProjectSubscriptionName subscriptionName =
                ProjectSubscriptionName.of(projectId, subscriptionId);

        // Instantiate an asynchronous message receiver.

        final String messageTt = "";
        final MessageReceiver receiver =
                (final PubsubMessage message, final AckReplyConsumer consumer) -> {
                    // Handle incoming message, then ack the received message.
                    System.out.println("Id: " + message.getMessageId());
                    System.out.println("Data: " + message.getData().toStringUtf8());
                    //  messageTt = message.getData().toStringUtf8();
                    consumer.ack();
                };
        //System.out.println(message);

        // final BucketNotification bucketNotification = new ObjectMapper().readValue(message.)
        Subscriber subscriber = null;
        try {
            subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
            // Start the subscriber.
            subscriber.startAsync().awaitRunning();
            System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
            // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
            subscriber.awaitTerminated(30, TimeUnit.SECONDS);
        } catch (final TimeoutException timeoutException) {
            // Shut down the subscriber after 30s. Stop receiving messages.
            subscriber.stopAsync();
        }
    }
}