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

package app.illl.xmsger.cache;

import app.illl.xmsger.datasource.entity.TelegramRegisteredChat;
import app.illl.xmsger.datasource.repository.TelegramRegisteredChatRepository;
import lombok.Getter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class TelegramRegisteredChatCache implements InitializingBean {

    @Autowired
    private TelegramRegisteredChatRepository telegramRegisteredChatRepository;
    @Getter
    private Set<Integer> chatIds;

    @Scheduled(cron = "0 */10 * * * *")
    @Synchronized
    private void refresh() {
        Set<Integer> data = new HashSet<>();
        Iterable<TelegramRegisteredChat> chats = this.telegramRegisteredChatRepository.findAll();
        for (TelegramRegisteredChat chat : chats) {
            data.add(chat.getChatId());
        }
        this.chatIds = data;
        if (log.isDebugEnabled()) {
            log.debug("telegramRegisteredChatCache:{}", data.size());
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void afterPropertiesSet() throws Exception {
        this.refresh();
    }
}
