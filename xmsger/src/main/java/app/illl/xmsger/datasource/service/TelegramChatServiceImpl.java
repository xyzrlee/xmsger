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

package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.TelegramChat;
import app.illl.xmsger.datasource.repository.TelegramChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class TelegramChatServiceImpl implements TelegramChatService {

    private final TelegramChatRepository telegramChatRepository;

    @Override
    public TelegramChat findById(Integer id) {
        return this.telegramChatRepository.findById(id).orElse(null);
    }

    @Override
    public void save(TelegramChat telegramChat) {
        this.telegramChatRepository.save(telegramChat);
    }
}
