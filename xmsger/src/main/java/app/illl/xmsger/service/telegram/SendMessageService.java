/*
 *     XMsger
 *     Copyright (C) 2019  Ricky Li
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

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
        this.sendPlainText(chatId, text, false);
    }

    public void sendPlainText(Integer chatId, String text, Boolean disableNotification) {
        SendMessage<String> sendMessage = new SendMessage<>();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setDisableNotification(disableNotification);
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