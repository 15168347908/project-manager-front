package priv.jzy.front.util.network;


import org.springframework.stereotype.Component;

@Component
public class RequestContentBuilder {

    public RequestContent build() {
        return new RequestContent();
    }
}
