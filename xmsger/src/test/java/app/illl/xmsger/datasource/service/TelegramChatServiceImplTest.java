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
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class TelegramChatServiceImplTest {

    @Autowired
    private TelegramChatService telegramChatService;

    @Test
    public void findById() {
        TelegramChat telegramChat = telegramChatService.findById(123);
        Assert.assertNotNull(telegramChat);
    }

    @Test
    public void save() {
        TelegramChat telegramChat = new TelegramChat();
        telegramChat.setChatId(999999);
        telegramChat.setType("private");
        telegramChat.setUsername("test");
        telegramChat.setMessageId(777777);
        telegramChatService.save(telegramChat);
        Assert.assertTrue(true);
    }
}