package app.illl.xmsger.schedule;

import app.illl.xmsger.datasource.entity.NoticeMonthly;
import app.illl.xmsger.datasource.service.NoticeMonthlyService;
import app.illl.xmsger.datasource.service.TelegramRegisteredChatService;
import app.illl.xmsger.service.telegram.SendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
class NoticeMonthlyScheduler {

    private static final int NOTICE_DAYS = 3;

    private final NoticeMonthlyService noticeMonthlyService;
    private final TelegramRegisteredChatService telegramRegisteredChatService;
    private final SendMessageService sendMessageService;

    @Scheduled(cron = "0 0 8 * * *")
    void notice() {
        List<Integer> chatIds = telegramRegisteredChatService.getAllChatId();
        if (null == chatIds) return;
        for (Integer chatId : chatIds) {
            notice(chatId, NOTICE_DAYS);
        }
    }

    private void notice(Integer chatId, @SuppressWarnings("SameParameterValue") int daysInAdvance) {
        LocalDate date = LocalDate.now();
        String dateMsg = "today";
        for (int i = 0; i < daysInAdvance; i++) {
            List<Integer> days = new ArrayList<>(4);
            days.add(date.getDayOfMonth());
            if (date.getDayOfMonth() == date.lengthOfMonth()) {
                for (int x = date.getDayOfMonth() + 1; x <= 31; x++) {
                    days.add(x);
                }
            }
            List<NoticeMonthly> notices = noticeMonthlyService.getByChatIdAndDay(chatId, days);
            List<String> noticeMessageList = new LinkedList<>();
            for (NoticeMonthly notice : notices) {
                if (notice.getMessage() != null) {
                    noticeMessageList.add(notice.getMessage());
                }
            }
            if (i > 0) {
                dateMsg = "+" + i + "d";
            }
            if (!noticeMessageList.isEmpty()) {
                String message = "[" + dateMsg + "] " + StringUtils.join(noticeMessageList, ", ");
                sendMessageService.sendPlainText(chatId, message);
            }
            date = date.plusDays(1);
        }
    }

}
