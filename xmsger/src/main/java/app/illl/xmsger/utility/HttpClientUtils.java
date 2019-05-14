package app.illl.xmsger.utility;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

@SuppressWarnings("WeakerAccess")
public class HttpClientUtils {

    private HttpClientUtils() {
    }

    public static RequestConfig getDefaultRequestConfig() {
        return RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
    }

    public static CloseableHttpClient getDefaultHttpClient() {
        return HttpClientBuilder.create()
                .useSystemProperties()
                .setDefaultRequestConfig(getDefaultRequestConfig())
                .build();
    }

}
