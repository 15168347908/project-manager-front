package priv.jzy.front.util.network;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import priv.jzy.front.util.network.enumeration.MethodType;
import priv.jzy.front.util.network.node.*;

import java.util.Map;
import java.util.Optional;

@Getter
@Slf4j
public class RequestContent implements Url, Method, Body, Headers, Params {
    private String url;
    private Map<String, String> params;
    private Map<String, String> header;
    private MethodType methodType = MethodType.GET;
    private RequestBody body;

    protected RequestContent() {
    }

    public Request getRequest() {
        Request.Builder builder = new Request.Builder()
                .url(buildingHttpUrl(this.url, params));
        if (header != null)
            builder.headers(okhttp3.Headers.of(header));

        if (methodType.equals(MethodType.GET)) {
            // GET 请求
            return builder.build();
        } else if (methodType.equals(MethodType.POST)) {
            // POST 请求
            return builder.post(body).build();
        } else {
            throw new RuntimeException("暂不支持的请求类型！");
        }
    }

    @Override
    public Headers addHeader(String name, String value) {
        header.put(name, value);
        return this;
    }

    @Override
    public Params addParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    @Override
    public Url url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public Headers headers() {
        header = Maps.newHashMap();
        return this;
    }

    @Override
    public Params params() {
        params = Maps.newHashMap();
        return this;
    }

    @Override
    public RequestContent build() {
        return this;
    }

    @Override
    public Url get() {
        this.methodType = MethodType.GET;
        return this;
    }

    @Override
    public Body post() {
        this.methodType = MethodType.POST;
        return this;
    }

    @Override
    public Url body(RequestBody body) {
        this.body = body;
        return this;
    }

    public static HttpUrl buildingHttpUrl(String url, Map<String, String> params) {
        Optional<HttpUrl.Builder> optionalBuilder = Optional.ofNullable(HttpUrl.parse(url))
                .map(HttpUrl::newBuilder);

        if (optionalBuilder.isEmpty())
            throw new RuntimeException("构建请求路径失败！");

        HttpUrl.Builder builder = optionalBuilder.get();
        if (params != null)
            params.forEach(builder::addQueryParameter);
        HttpUrl httpUrl = builder.build();
        log.info("请求 url:" + httpUrl.url().getPath());

        return httpUrl;
    }
}
