package priv.jzy.front.util.network;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import priv.jzy.front.util.network.node.Method;

@AllArgsConstructor
@Component
public class RequestContentFactory {
    private final RequestContentBuilder requestContentBuilder;

    public Method builder() {
        return requestContentBuilder.build();
    }

}
