package week3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import week3.filter.HttpRequestFilter;

import java.io.IOException;

public class OkHttpOutboundHandler {

    OkHttpClient client = new OkHttpClient();

    public String run(FullHttpRequest fullRequest, ChannelHandlerContext ctx, HttpRequestFilter filter) throws IOException {

        final String backendServerUrl = "http://localhost:8801";

        filter.filter(fullRequest, ctx);

        Request request = new Request.Builder()
                .addHeader("bosco", fullRequest.headers().get("bosco"))
                .url(backendServerUrl)
                .build();
        System.out.println("okhttp header --- " + request.headers());
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}

