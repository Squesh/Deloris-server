package backend.server.rethink;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

public class SimpleCompletableFutureFactory implements CompletableFutureFactory {

    private final Executor executor;

    public SimpleCompletableFutureFactory(Executor executor) {
        this.executor = executor;
    }

    @Override
    public <T> CompletableFuture<T> supply(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, executor);
    }

    @Override
    public CompletableFuture<Void> run(Runnable runnable) {
        return CompletableFuture.runAsync(runnable, executor);
    }
}