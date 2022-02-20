package priv.jzy.front.util;


import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import priv.jzy.front.entity.R;
import priv.jzy.front.util.func.Failure;
import priv.jzy.front.util.func.Success;
import priv.jzy.front.util.func.Try;
import priv.jzy.front.util.network.RequestContent;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static priv.jzy.front.util.network.RequestContent.buildingHttpUrl;
import static priv.jzy.front.util.network.MediaTypeContainer.APPLICATION_JSON;

@Component
@Slf4j
public class Netter {

    private final static long CONNECT_TIME_OUT = 1000;

    private final OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(CONNECT_TIME_OUT * 3, TimeUnit.MILLISECONDS)
            .build();

    public Try<R<JSONObject>> get(String url, Map<String, String> params, Map<String, String> headerMap) {
        HttpUrl httpUrl = buildingHttpUrl(url, params);

        Request request = new Request.Builder()
                .url(httpUrl)
                .headers(Headers.of(headerMap))
                .build();

        return execute(request);
    }

    public Try<R<JSONObject>> post(String url,
                                Map<String, String> headerMap,
                                Map<String, String> params,
                                String body,
                                MediaType mediaType) {
        HttpUrl httpUrl = buildingHttpUrl(url, params);

        Request request = new Request.Builder()
                .url(httpUrl)
                .headers(Headers.of(headerMap))
                .post(RequestBody.create(body, mediaType))
                .build();

        return execute(request);
    }

    public Try<R<JSONObject>> post(String url,
                                Map<String, String> headerMap,
                                Map<String, String> params,
                                String body) {
        return this.post(url, headerMap, params, body, APPLICATION_JSON);
    }

    @SneakyThrows
    public Try<R<JSONObject>> execute(Request request) {
        log.info("请求头：" + request);

        if (request == null)
            throw new RuntimeException("执行失败，请求体为空！");

        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            String stringBody = body != null ? body.string() : null;

            log.info("返回的请求体：" + stringBody);
            R<JSONObject> res = JSONObject.<R<JSONObject>>parseObject(stringBody, R.class);
            return new Success<>(res);
        } catch (Exception error) {
            error.printStackTrace();
            return new Failure<>(error);
        }
    }

    @SneakyThrows
    public Try<R<JSONObject>> execute(RequestContent requestContent) {
        if (requestContent == null)
            return new Failure<>(new RuntimeException("请求内容不能为空！"));

        return execute(requestContent.getRequest());
    }
}
