package priv.jzy.front.util.network.node;

public interface Headers {

    Headers addHeader(String name, String value);

    Params params();
}
