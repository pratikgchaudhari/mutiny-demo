package mutiny.demo;

import org.junit.jupiter.api.Test;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.BeforeEach;

class AppTest {

    private App app = null;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    void shouldProduceHelloMutinyAsOutput() {

        app.helloMutiny()
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertCompleted()
                .assertItem("HELLO MUTINY!");
    }
}
