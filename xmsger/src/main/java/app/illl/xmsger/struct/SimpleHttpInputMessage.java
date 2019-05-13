package app.illl.xmsger.struct;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SimpleHttpInputMessage implements HttpInputMessage {

    private HttpHeaders httpHeaders;
    private InputStream inputStream;

    public SimpleHttpInputMessage(HttpInputMessage httpInputMessage, String message) {
        this.httpHeaders = httpInputMessage.getHeaders();
        this.inputStream = IOUtils.toInputStream(message, StandardCharsets.UTF_8);
    }

    @Override
    public InputStream getBody() throws IOException {
        return this.inputStream;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }
}
