package mutiny.demo;

import org.junit.jupiter.api.Test;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import java.util.concurrent.atomic.AtomicInteger;
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

    @Test
    void shouldFailWithAppException() {
        app.failedUni()
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertFailedWith(AppException.class, "Oops! Looks like we messed up something.");
    }

    @Test
    void shouldReturnANullItem() {
        app.nullItem()
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertItem(null)
                .assertCompleted();
    }

    @Test
    void shouldReturnAContinouslyIncrementingCounter() {

        AtomicInteger counter = new AtomicInteger();

        app.counter()
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertItem(counter.getAndIncrement())
                .assertCompleted();

        app.counter()
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertItem(counter.getAndIncrement())
                .assertCompleted();
    }
}
