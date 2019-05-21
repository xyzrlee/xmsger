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
    }
}