package ai.silot.taurus.util;

public class HttpResponse {
    
    private String responseBody;

    public HttpResponse() {
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String body() {
        return responseBody;
    }
}
