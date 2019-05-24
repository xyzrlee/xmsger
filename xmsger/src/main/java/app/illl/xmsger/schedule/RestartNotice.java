package app.illl.xmsger.schedule;

import app.illl.xmsger.cache.TokenCache;
import app.illl.xmsger.datasource.entity.Token;
import app.illl.xmsger.service.telegram.SendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestartNotice implements ApplicationListener<ContextRefreshedEvent> {

    private final SendMessageService sendMessageService;
    private final TokenCache tokenCache;

    private static final String NOTICE_MESSAGE = "xmsger restarted";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Token adminChatIdToken = tokenCache.getToken("adminChatId");
        if (null == adminChatIdToken || StringUtils.isBlank(adminChatIdToken.getData())) {
            return;
        }
        String[] chatIds = StringUtils.split(adminChatIdToken.getData(), ',');
        int i = 0;
        for (String chatId : chatIds) {
            try {
                Integer id = Integer.valueOf(chatId.trim());
                log.trace("sending restart notice to [{}]", i++);
                sendMessageService.sendPlainText(id, NOTICE_MESSAGE + " at " + ZonedDateTime.now().toString());
            } catch (Exception e) {
                // do nothing
            }
        }
    }
}
