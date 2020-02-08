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

import app.illl.xmsger.datasource.entity.NoticeMonthly;
import app.illl.xmsger.datasource.service.NoticeMonthlyService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoticeSchedulerTest {

    private static boolean initialized = false;
    @Autowired
    private NoticeMonthlyService noticeMonthlyService;
    @Autowired
    private NoticeScheduler noticeScheduler;

    @Before
    @Synchronized
    public void beforeClass() {
        if (initialized) return;
        LocalDate now = LocalDate.now();
        LocalDate time = LocalDate.now().minusDays(4);
        LocalDate end = LocalDate.now().plusDays(5);
        while (time.isBefore(end)) {
            for (int i = 0; i < 2; i++) {
                NoticeMonthly noticeMonthly = new NoticeMonthly();
                noticeMonthly.setChatId(1);
                noticeMonthly.setDayOfMonth(time.getDayOfMonth());
                noticeMonthly.setMessage("text " + time.getDayOfMonth() + " " + i);
                noticeMonthlyService.save(noticeMonthly);
            }
            time = time.plusDays(1);
        }
        initialized = true;
    }

    @Test
    public void morningNotice() {
        int num = noticeScheduler.morningNotice();
        Assert.assertEquals(3, num);
    }

    @Test
    public void afternoonNotice() {
        int num = noticeScheduler.afternoonNotice();
        Assert.assertEquals(1, num);
    }

    @Test
    public void test2() {
        Assert.assertTrue(true);
    }
}