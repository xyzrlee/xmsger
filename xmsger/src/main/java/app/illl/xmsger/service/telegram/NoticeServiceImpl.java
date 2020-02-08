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

package app.illl.xmsger.service.telegram;

import app.illl.xmsger.datasource.entity.NoticeMonthly;
import app.illl.xmsger.datasource.service.NoticeMonthlyService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMonthlyService noticeMonthlyService;
    private final SendMessageService sendMessageService;

    @Override
    public int monthlyNotice(Integer chatId, int daysInAdvance) {
        int messageSent = 0;
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
                messageSent++;
            }
            date = date.plusDays(1);
        }
        return messageSent;
    }
}
