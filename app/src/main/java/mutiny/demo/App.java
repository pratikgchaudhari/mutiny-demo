package mutiny.demo;

import java.util.concurrent.atomic.AtomicInteger;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public class App {

    AtomicInteger counter = new AtomicInteger();

    public Uni<String> helloMutiny() {
        return Uni
                .createFrom()
                .item("hello")
                .onItem().transform(item -> item + " mutiny!")
                .onItem().transform(String::toUpperCase);
    }

    public Uni<String> failedUni() {
        return Uni.createFrom()
                .failure(new AppException("Oops! Looks like we messed up something."));
    }

    public Uni<Void> nullItem() {
        return Uni.createFrom().nullItem();
    }

    public Uni<Integer> counter() {
        return Uni.createFrom().item(() -> counter.getAndIncrement());
    }

    public Multi<Integer> intergerFromOneToFive() {
        return Multi.createFrom().items(1, 2, 3, 4, 5);
    }
}
