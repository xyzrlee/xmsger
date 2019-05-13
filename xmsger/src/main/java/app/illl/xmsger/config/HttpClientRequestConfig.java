package app.illl.xmsger.config;

import org.apache.http.client.config.RequestConfig;

public class HttpClientRequestConfig {

    public static final RequestConfig DEFAULT = RequestConfig.custom()
            .setSocketTimeout(5000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .build();

}
