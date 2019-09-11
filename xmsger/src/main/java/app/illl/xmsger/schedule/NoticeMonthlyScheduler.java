package app.illl.xmsger.schedule;

import app.illl.xmsger.datasource.entity.NoticeMonthly;
import app.illl.xmsger.datasource.service.NoticeMonthlyService;
import app.illl.xmsger.datasource.service.TelegramRegisteredChatService;
import app.illl.xmsger.service.telegram.SendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
class NoticeMonthlyScheduler {

    private final NoticeMonthlyService noticeMonthlyService;
    private final TelegramRegisteredChatService telegramRegisteredChatService;
    private final SendMessageService sendMessageService;

    @Scheduled(cron = "0 0 8 * * *")
    void notice() {
        Integer day = ZonedDateTime.now(ZoneId.systemDefault()).getDayOfMonth();
        List<Integer> chatIds = telegramRegisteredChatService.getAllChatId();
        if (null == chatIds) return;
        for (Integer chatId : chatIds) {
            notice(chatId, day);
        }
    }

    private void notice(Integer chatId, Integer dayOfMonth) {
        LocalDate date2 = LocalDate.now();
        LocalDate date1 = date2.minusDays(3);
        LocalDate date3 = LocalDate.now().withDayOfMonth(dayOfMonth);
        if (date3.isBefore(date2)) {
            date3 = date3.plusMonths(1);
        }
        if (date3.isBefore(date1) || date3.isAfter(date2)) {
            return;
        }
        List<String> noticeMessageList = new LinkedList<>();
        List<NoticeMonthly> notices = noticeMonthlyService.getByChatId(chatId);
        for (NoticeMonthly notice : notices) {
            if (NumberUtils.compare(dayOfMonth, notice.getDayOfMonth()) == 0 && notice.getMessage() != null) {
                noticeMessageList.add(notice.getMessage());
            }
        }
        if (!noticeMessageList.isEmpty()) {
            String message = StringUtils.join(noticeMessageList, ", ");
            sendMessageService.sendPlainText(chatId, message);
        }
    }

}
