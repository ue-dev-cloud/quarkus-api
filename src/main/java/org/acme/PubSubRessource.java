package org.acme;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Path("/publish")
public class PubSubRessource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void publishMessage(Order order) throws IOException, ExecutionException, InterruptedException {
        String projectId = "ue-dev-cloud";
        String topicId = "test-topic-1";

        ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
        Publisher publisher = Publisher.newBuilder(topicName).build();

        String message = "{\"name\": \"" + order.getName() + "\", \"address\": \"" + order.getAddress() + "\", \"product\": \"" + order.getProduct() + "\"}";
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

        publisher.publish(pubsubMessage).get();
    }
}
