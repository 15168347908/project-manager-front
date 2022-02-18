package priv.jzy.front.util.func;

import java.util.Optional;
import java.util.function.Function;

public class Failure<T> implements Try<T> {
    private final static Exception DEFAULT_EXCEPTION = new RuntimeException();

    private final Exception exception;

    public Failure(Exception exception) {
        this.exception = Optional.ofNullable(exception).orElse(DEFAULT_EXCEPTION);
    }

    @Override
    public T get() throws Exception {
        throw exception;
    }

    @Override
    public <N> Try<N> map(Function<T, Try<N>> mapping) {
        return new Failure<>(exception);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isFailure() {
        return true;
    }

    public String getErrorMessage() {
        return exception.getMessage();
    }

    public Exception getException() {
        return exception;
    }
}
