package app.illl.xmsger.schedule;

import app.illl.xmsger.cache.TelegramRegisteredChatCache;
import app.illl.xmsger.cache.TokenCache;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshCache {

    private final TokenCache tokenCache;
    private final TelegramRegisteredChatCache telegramRegisteredChatCache;

    @Scheduled(cron = "0 */10 * * * *")
    @Synchronized
    private void defaultRefresh() {
        this.tokenCache.refresh();
        this.telegramRegisteredChatCache.refresh();
    }

}
