package app.illl.xmsger.utility;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Assert;
import org.junit.Test;

public class HttpClientUtilsTest {

    @Test
    public void getDefaultRequestConfig() {
        RequestConfig requestConfig = HttpClientUtils.getDefaultRequestConfig();
        Assert.assertNotNull(requestConfig);
    }

    @Test
    public void getDefaultHttpClient() {
        CloseableHttpClient httpClient = HttpClientUtils.getDefaultHttpClient();
        Assert.assertNotNull(httpClient);
    }
}