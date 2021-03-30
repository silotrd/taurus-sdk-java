package ai.silot.taurus.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequest {
    private Method method = Method.GET;
    private final Map<String, List<String>> headers = new HashMap<>();
    private final String url;
    private String jsonBody;

    public HttpRequest(String url) {
        this.url = url;
    }

    public HttpRequest header(String name, String value) {
        if (null != name && null != value) {
            final ArrayList<String> valueList = new ArrayList<>();
            valueList.add(value);
            this.headers.put(name, valueList);
        }
        return this;
    }

    public HttpRequest header(Map<String, List<String>> headers) {
        if (headers == null || headers.isEmpty()) {
            return this;
        }
        this.headers.putAll(headers);
        return this;
    }

    public HttpRequest body(String jsonBody) {
        this.jsonBody = jsonBody;
        return this;
    }

    public HttpRequest method(Method method) {
        this.method = method;
        return this;
    }

    public static HttpRequest get(String url) {
        return new HttpRequest(url).method(Method.GET);
    }

    public static HttpRequest post(String url) {
        return new HttpRequest(url).method(Method.POST);
    }

    public HttpResponse execute() throws IOException {
        URL url = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(30000);
        connection.setUseCaches(false);
        connection.setRequestMethod(this.method.name());

        for (Entry<String, List<String>> entry : headers.entrySet()) {
            for (String value : entry.getValue()) {
                connection.setRequestProperty(entry.getKey(), value);
            }
        }

        String params = "";
        if (Method.POST.equals(this.method)
                || Method.PUT.equals(this.method)
                || Method.DELETE.equals(this.method)) {
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            OutputStream stream = connection.getOutputStream();
            if (jsonBody != null) {
                params = jsonBody;
            }
            stream.write(params.getBytes(StandardCharsets.UTF_8));
            stream.close();
        }

        int responseCode = connection.getResponseCode();
        String responseBody;

        InputStream inputStream;
        if (responseCode >= 200 && responseCode < 300) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        responseBody = sb.toString();
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setResponseBody(responseBody);
        return httpResponse;
    }
}
