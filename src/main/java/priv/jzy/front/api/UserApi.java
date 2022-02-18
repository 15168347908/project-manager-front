package priv.jzy.front.api;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;
import priv.jzy.front.Env;
import priv.jzy.front.entity.User;
import priv.jzy.front.util.Netter;
import priv.jzy.front.util.func.Try;
import priv.jzy.front.util.network.RequestContent;
import priv.jzy.front.util.network.RequestContentFactory;

import static priv.jzy.front.util.network.MediaTypeContainer.APPLICATION_JSON;


@Component
@AllArgsConstructor
public class UserApi {

    private final RequestContentFactory factory;
    private final Netter request;

    public static final String MODULE = Env.CORE + "/user";

    public Try<JSONObject> register(User user) {
        RequestContent requestContent = factory.builder().post()
                .body(RequestBody.create(JSONObject.toJSONString(user), APPLICATION_JSON))
                .url(MODULE + "/register")
                .build();
        return request.execute(requestContent);
    }
}
