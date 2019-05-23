package app.illl.xmsger.datasource.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class TelegramRegisteredChatServiceImplTest {

    @Autowired
    private TelegramRegisteredChatService telegramRegisteredChatService;

    @Test
    public void getAllChatId() {
        List<Integer> ids = telegramRegisteredChatService.getAllChatId();
        log.info("ids:{}", ids);
        Assert.assertFalse(ids.isEmpty());
    }
}