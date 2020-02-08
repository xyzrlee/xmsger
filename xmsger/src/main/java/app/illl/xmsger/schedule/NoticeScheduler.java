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
