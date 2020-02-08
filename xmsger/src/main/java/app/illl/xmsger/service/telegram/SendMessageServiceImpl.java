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

package app.illl.xmsger.service.telegram;

import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.exception.BadRequestException;
import app.illl.xmsger.exception.InternalServerErrorException;
import app.illl.xmsger.service.HttpClientInitializerImpl;
import app.illl.xmsger.struct.telegram.request.SendMessageRequest;
import app.illl.xmsger.utility.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@SuppressWarnings("unused")
@Component
@Slf4j
@RequiredArgsConstructor
public class SendMessageServiceImpl implements SendMessageService {

    private final GetUrlService getUrlService;
    private final HttpClientInitializerImpl httpClientInitializer;

    @Override
    public void sendMessage(SendMessageRequest sendMessageRequest) {
        URI uri = URI.create(getUrlService.getUrl(Telegram.METHOD_SEND_MESSAGE));
        try (CloseableHttpClient httpClient = httpClientInitializer.getDefaultHttpClient()) {
            HttpPost request = new HttpPost();
            request.setURI(uri);
            request.setEntity(new StringEntity(JsonUtils.toJson(sendMessageRequest), ContentType.APPLICATION_JSON));
            try (CloseableHttpResponse httpResponse = httpClient.execute(request)) {
                HttpStatus httpStatus = HttpStatus.valueOf(httpResponse.getStatusLine().getStatusCode());
                if (!httpStatus.is2xxSuccessful()) {
                    throw new InternalServerErrorException("request failed, status: " + httpStatus.value());
                }
            }
        } catch (IOException e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public void sendPlainText(Integer chatId, String text) {
        this.sendPlainText(chatId, text, false);
    }

    @Override
    public void sendPlainText(Integer chatId, String text, Boolean disableNotification) {
        SendMessageRequest<String> sendMessageRequest = new SendMessageRequest<>();
        sendMessageRequest.setChatId(chatId);
        sendMessageRequest.setText(text);
        sendMessageRequest.setDisableNotification(disableNotification);
        this.sendMessage(sendMessageRequest);
    }

    public void replyPlainText(Integer chatId, String text, Integer replyToMessageId) {
        SendMessageRequest<String> sendMessageRequest = new SendMessageRequest<>();
        sendMessageRequest.setChatId(chatId);
        sendMessageRequest.setText(text);
        sendMessageRequest.setReplyToMessageId(replyToMessageId);
        this.sendMessage(sendMessageRequest);
    }

}