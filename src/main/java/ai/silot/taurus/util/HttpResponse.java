package ai.silot.taurus.util;

public class HttpResponse {
    int status;
    private String responseBody;

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public HttpResponse() {
    }

    public String body() {
        return responseBody;
    }
}
