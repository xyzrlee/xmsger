package app.illl.xmsger.service;

import org.apache.http.impl.client.CloseableHttpClient;

public interface HttpClientInitializer {
    CloseableHttpClient getDefaultHttpClient();

    void enableProxy(String host, int port);

    void disableProxy();
}
