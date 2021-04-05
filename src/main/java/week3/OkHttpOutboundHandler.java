package week3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpOutboundHandler {

    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        System.out.println("okhttp print request: -----   " + request.headers());
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}

