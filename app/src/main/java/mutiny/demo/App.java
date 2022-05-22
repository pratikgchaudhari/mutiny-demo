package mutiny.demo;

import io.smallrye.mutiny.Uni;

public class App {
    public Uni<String> helloMutiny() {
        return Uni
                .createFrom()
                .item("hello")
                .onItem().transform(item -> item + " mutiny!")
                .onItem().transform(String::toUpperCase);
    }
}
