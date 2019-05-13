package app.illl.xmsger.service.telegram;

import app.illl.xmsger.config.HttpClientRequestConfig;
import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.exception.BadRequestException;
import app.illl.xmsger.struct.telegram.SendMessage;
import app.illl.xmsger.utility.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
@Slf4j
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private GetUrlService getUrlService;

    @Override
    public void sendMessage(SendMessage sendMessage) {
        URI uri = URI.create(getUrlService.getUrl(Telegram.METHOD_SEND_MESSAGE));
        HttpClientContext httpClientContext = HttpClientContext.create();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(HttpClientRequestConfig.DEFAULT)
                .build()
        ) {
            HttpPost request = new HttpPost();
            request.setURI(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
            request.setEntity(new StringEntity(JsonUtils.toJson(sendMessage)));
            httpClient.execute(request, httpClientContext);
        } catch (IOException e) {
            log.error("", e);
            throw new BadRequestException(e);
        }
    }
}