package app.illl.xmsger.schedule;

import app.illl.xmsger.datasource.service.TelegramRegisteredChatService;
import app.illl.xmsger.service.telegram.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
class NoticeScheduler {

    private static final int MORNING_NOTICE_DAYS = 3;
    private static final int ARFTERNOON_NOTICE_DAYS = 1;

    private final TelegramRegisteredChatService telegramRegisteredChatService;
    private final NoticeService noticeService;

    @Scheduled(cron = "0 0 8 * * *")
    int morningNotice() {
        int messageSent = 0;
        List<Integer> chatIds = telegramRegisteredChatService.getAllChatId();
        if (null == chatIds) return messageSent;
        for (Integer chatId : chatIds) {
            messageSent += noticeService.monthlyNotice(chatId, MORNING_NOTICE_DAYS);
        }
        return messageSent;
    }

    @Scheduled(cron = "0 0 13 * * *")
    int afternoonNotice() {
        int messageSent = 0;
        List<Integer> chatIds = telegramRegisteredChatService.getAllChatId();
        if (null == chatIds) return messageSent;
        for (Integer chatId : chatIds) {
            messageSent += noticeService.monthlyNotice(chatId, ARFTERNOON_NOTICE_DAYS);
        }
        return messageSent;
    }


}
