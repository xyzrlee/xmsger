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
    public void notice() {
        noticeMonthlyScheduler.notice();
        Assert.assertTrue(true);
    }

    @Test
    public void test2() {
        Assert.assertTrue(true);
    }
}