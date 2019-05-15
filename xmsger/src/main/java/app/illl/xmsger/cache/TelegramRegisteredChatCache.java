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
