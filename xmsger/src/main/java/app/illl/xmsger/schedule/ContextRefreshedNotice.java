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

package app.illl.xmsger.schedule;

import app.illl.xmsger.cache.TokenCache;
import app.illl.xmsger.datasource.entity.Token;
import app.illl.xmsger.service.telegram.SendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContextRefreshedNotice implements ApplicationListener<ContextRefreshedEvent> {

    private final SendMessageService sendMessageService;
    private final TokenCache tokenCache;

    private static final String NOTICE_MESSAGE = "xmsger restarted";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Token adminChatIdToken = tokenCache.getToken("adminChatId");
        if (null == adminChatIdToken || StringUtils.isBlank(adminChatIdToken.getData())) {
            return;
        }
        String[] chatIds = StringUtils.split(adminChatIdToken.getData(), ',');
        int i = 0;
        for (String chatId : chatIds) {
            try {
                Integer id = Integer.valueOf(chatId.trim());
                log.trace("sending restart notice to [{}]", i++);
                sendMessageService.sendPlainText(id, NOTICE_MESSAGE + " at " + ZonedDateTime.now().toString());
            } catch (Exception e) {
                // do nothing
            }
        }
    }
}
