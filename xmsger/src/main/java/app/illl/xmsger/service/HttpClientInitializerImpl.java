/*
 * XMsger
 * Copyright (C) 2020  Ricky Li
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package app.illl.xmsger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class HttpClientInitializerImpl implements HttpClientInitializer {

    private boolean isProxyEnabled = false;
    private String proxyHost;
    private int proxyPort;

    private RequestConfig getDefaultRequestConfig() {
        return RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
    }

    public CloseableHttpClient getDefaultHttpClient() {
        HttpClientBuilder builder = HttpClientBuilder.create()
                .useSystemProperties()
                .setDefaultRequestConfig(getDefaultRequestConfig());
        if (isProxyEnabled) {
            builder.setProxy(new HttpHost(proxyHost, proxyPort));
        }
        return builder.build();
    }

    public void enableProxy(String host, int port) {
        this.isProxyEnabled = true;
        this.proxyHost = host;
        this.proxyPort = port;
    }

    public void disableProxy() {
        this.isProxyEnabled = false;
        this.proxyHost = "";
        this.proxyPort = 0;
    }

}
