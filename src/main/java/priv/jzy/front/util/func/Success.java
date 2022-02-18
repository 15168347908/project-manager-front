package priv.jzy.front.util.func;

import java.util.Objects;
import java.util.function.Function;

public class Success<T> implements Try<T> {
    private final T value;

    public Success(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public <N> Try<N> map(Function<T, Try<N>> mapping) {
        Objects.requireNonNull(mapping, "映射函数不能为空！");
        return mapping.apply(value);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFailure() {
        return false;
    }
}
