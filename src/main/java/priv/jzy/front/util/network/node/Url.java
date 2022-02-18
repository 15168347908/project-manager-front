package priv.jzy.front.util.network.node;

import priv.jzy.front.util.network.RequestContent;

public interface Url {

    Url url(String url);

    Headers headers();

    Params params();

    RequestContent build();
}
