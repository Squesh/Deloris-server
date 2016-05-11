package backend.server.rethink;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface CompletableFutureFactory {

    <T> CompletableFuture<T> supply(Supplier<T> supplier);

    CompletableFuture<Void> run(Runnable runnable);
}
