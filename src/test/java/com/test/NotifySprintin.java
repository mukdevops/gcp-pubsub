package com.test;

import com.google.cloud.functions.CloudEventsFunction;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import io.cloudevents.CloudEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotifySprintin implements CloudEventsFunction {

    private static final String PROJECT_ID = System.getenv("hale-sentry-423219-k9");

    private static final Logger logger = Logger.getLogger(NotifySprintin.class.getName());

    @Override
    public void accept(final CloudEvent event) throws IOException {
        logger.info("Cloud Event data: " + new String(event.getData().toBytes()));

        final MessageTemplate message = new MessageTemplate("test", "test1", "test2");
        final String jsonBody = new Gson().toJson(message);

        final ByteString byteStr = ByteString.copyFrom(jsonBody, StandardCharsets.UTF_8);
        final PubsubMessage pubsubApiMessage = PubsubMessage.newBuilder().setData(byteStr).build();

        final Publisher publisher = Publisher.newBuilder(
                ProjectTopicName.of(PROJECT_ID, "TPC_SPRINTIN_MESSAGE")).build();

        // Attempt to publish the message
        String responseMessage;
        try {
            publisher.publish(pubsubApiMessage).get();
            responseMessage = "Message published.";
        } catch (final InterruptedException | ExecutionException e) {
            logger.log(Level.SEVERE, "Error publishing Pub/Sub message: " + e.getMessage(), e);
            responseMessage = "Error publishing Pub/Sub message; see logs for more info.";
        }

    }

    public class MessageTemplate {
        String message;
        String fileName;
        String filePath;

        MessageTemplate(final String message, final String fileName, final String filePath) {
            this.message = message;
            this.fileName = fileName;
            this.filePath = filePath;

        }
    }
}