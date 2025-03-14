package org.acme;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("/orders")
public class OrderResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderFirebase> getOrders() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreOptions.getDefaultInstance().getService();
        QuerySnapshot querySnapshot = db.collection("orders").get().get();

        List<OrderFirebase> orders = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot) {
            OrderFirebase order = document.toObject(OrderFirebase.class);
            orders.add(order);
        }
        return orders;
    }
}
