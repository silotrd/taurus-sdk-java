package ai.silot.taurus.util;

import ai.silot.taurus.config.Taurus;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

public class TaurusHttpUtil {
    private static String getAuthorization() {
        return "Basic " + Base64.encode(Taurus.apiKey + ":");
    }

    public static <T> T get(String url, Type type) throws IOException {
        String response = HttpRequest.get(url)
                .header("Authorization", getAuthorization())
                .execute().body();
        return new Gson().fromJson(response, type);
    }

    public static <T> T post(String url, Type type, String jsonBody) throws IOException {
        String response = HttpRequest.post(url)
                .header("Authorization", getAuthorization())
                .body(jsonBody)
                .execute().body();
        return new Gson().fromJson(response, type);
    }
}
