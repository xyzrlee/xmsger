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

package app.illl.xmsger.cache;

import app.illl.xmsger.datasource.service.TelegramRegisteredChatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramRegisteredChatCache implements InitializingBean {

    private final TelegramRegisteredChatService telegramRegisteredChatService;
    @Getter
    private Set<Integer> chatIds;

    @Synchronized
    public void refresh() {
        Set<Integer> data = new HashSet<>();
        Iterable<Integer> ids = this.telegramRegisteredChatService.getAllChatId();
        for (Integer chatId : ids) {
            data.add(chatId);
        }
        this.chatIds = data;
        log.debug("telegramRegisteredChatCache:{}", data.size());
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void afterPropertiesSet() throws Exception {
        this.refresh();
    }
}
