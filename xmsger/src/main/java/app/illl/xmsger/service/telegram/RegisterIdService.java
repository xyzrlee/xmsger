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

import app.illl.xmsger.datasource.entity.TelegramChat;
import app.illl.xmsger.datasource.repository.TelegramChatRepository;
import app.illl.xmsger.struct.telegram.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class RegisterIdService {

    @Autowired
    private TelegramChatRepository telegramChatRepository;

    @Transactional
    private void registerId(Message message) {
        if (message == null) return;
        if (message.getChat() == null) return;
        Integer chatId = message.getChat().getId();
        String username = message.getChat().getUsername();
        String type = message.getChat().getType();
        Integer messageId = message.getMessageId();
        TelegramChat telegramChat = telegramChatRepository.findById(chatId).orElse(null);
        if (null != telegramChat && telegramChat.getChatId() >= chatId) {
            return;
        }
        if (null == telegramChat) {
            telegramChat = new TelegramChat();
            telegramChat.setChatId(chatId);
        }
        telegramChat.setUsername(username);
        telegramChat.setType(type);
        telegramChat.setMessageId(messageId);
        telegramChatRepository.save(telegramChat);
    }

    @Async
    public void registerIdAsync(Message message) {
        this.registerId(message);
    }

}
