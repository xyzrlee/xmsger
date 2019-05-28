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

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoticeMonthlySchedulerTest {

    private static boolean initialized = false;
    @Autowired
    private NoticeMonthlyService noticeMonthlyService;
    @Autowired
    private NoticeMonthlyScheduler noticeMonthlyScheduler;

    @Before
    @Synchronized
    public void beforeClass() {
        if (initialized) return;
        Integer day = ZonedDateTime.now(ZoneId.systemDefault()).getDayOfMonth();
        Integer nextDay = ZonedDateTime.now(ZoneId.systemDefault()).plusDays(1).getDayOfMonth();
        NoticeMonthly noticeMonthly = new NoticeMonthly();
        noticeMonthly.setChatId(1);
        noticeMonthly.setDayOfMonth(day);
        noticeMonthly.setMessage("test01");
        noticeMonthlyService.save(noticeMonthly);
        noticeMonthly = new NoticeMonthly();
        noticeMonthly.setChatId(1);
        noticeMonthly.setDayOfMonth(day);
        noticeMonthly.setMessage("test02");
        noticeMonthlyService.save(noticeMonthly);
        noticeMonthly = new NoticeMonthly();
        noticeMonthly.setChatId(1);
        noticeMonthly.setDayOfMonth(nextDay);
        noticeMonthly.setMessage("test03");
        noticeMonthlyService.save(noticeMonthly);
        initialized = true;
    }

    @Test
    public void notice() {
        noticeMonthlyScheduler.notice();
        Assert.assertTrue(true);
    }

    @Test
    public void test2() {
        Assert.assertTrue(true);
    }
}