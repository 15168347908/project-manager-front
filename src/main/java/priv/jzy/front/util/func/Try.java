package priv.jzy.front.util.func;

import java.util.function.Function;

public interface Try<T> {

    T get() throws Exception;

    <N> Try<N> map(Function<T, Try<N>> mapping);

    boolean isSuccess();

    boolean isFailure();

    /**
     * 结合 isFailure 方法一起用，不然会抛出异常
     */
    default Failure<T> toFailure() {
        return (Failure<T>) this;
    }
}
