package app.illl.xmsger.service.telegram;

import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.exception.BadRequestException;
import app.illl.xmsger.exception.InternalServerErrorException;
import app.illl.xmsger.struct.telegram.SendMessage;
import app.illl.xmsger.utility.HttpClientUtils;
import app.illl.xmsger.utility.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@SuppressWarnings("unused")
@Component
@Slf4j
public class SendMessageService {

    @Autowired
    private GetUrlService getUrlService;

    private void sendMessage(SendMessage sendMessage) {
        URI uri = URI.create(getUrlService.getUrl(Telegram.METHOD_SEND_MESSAGE));
        try (CloseableHttpClient httpClient = HttpClientUtils.getDefaultHttpClient()) {
            HttpPost request = new HttpPost();
            request.setURI(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
            request.setEntity(new StringEntity(JsonUtils.toJson(sendMessage)));
            try (CloseableHttpResponse httpResponse = httpClient.execute(request)) {
                HttpStatus httpStatus = HttpStatus.valueOf(httpResponse.getStatusLine().getStatusCode());
                if (!httpStatus.is2xxSuccessful()) {
                    throw new InternalServerErrorException("request failed, status: " + httpStatus.value());
                }
            }
        } catch (IOException e) {
            log.error("", e);
            throw new BadRequestException(e);
        }
    }

    public void sendPlainText(Integer chatId, String text) {
        SendMessage<String> sendMessage = new SendMessage<>();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        this.sendMessage(sendMessage);
    }

    public void replyPlainText(Integer chatId, String text, Integer replyToMessageId) {
        SendMessage<String> sendMessage = new SendMessage<>();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyToMessageId(replyToMessageId);
        this.sendMessage(sendMessage);
    }

}