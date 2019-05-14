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

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramRegisteredChatCache implements InitializingBean {

    @Autowired
    private TelegramRegisteredChatRepository telegramRegisteredChatRepository;
    @Getter
    private List<Integer> chatIdList;

    @Scheduled(cron = "0 */10 * * * *")
    @Synchronized
    private void refresh() {
        List<Integer> dataList = new ArrayList<>();
        Iterable<TelegramRegisteredChat> chats = this.telegramRegisteredChatRepository.findAll();
        for (TelegramRegisteredChat chat : chats) {
            dataList.add(chat.getChatId());
        }
        this.chatIdList = dataList;
        if (log.isDebugEnabled()) {
            log.debug("telegramRegisteredChatCache:{}", dataList.size());
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void afterPropertiesSet() throws Exception {
        this.refresh();
    }
}
