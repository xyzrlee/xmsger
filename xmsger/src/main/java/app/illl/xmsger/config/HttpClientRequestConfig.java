package app.illl.xmsger.config;

import org.apache.http.client.config.RequestConfig;

public class HttpClientRequestConfig {

    public static final RequestConfig DEFAULT = RequestConfig
            .custom()
            .setSocketTimeout(10000)
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000)
            .build();

}
