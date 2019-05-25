package app.illl.xmsger.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScheduleConfigTest {

    private ScheduleConfig scheduleConfig;

    @Before
    public void beforeTest() throws Exception {
        this.scheduleConfig = new ScheduleConfig();
        this.scheduleConfig.afterPropertiesSet();
    }

    @Test
    public void destroy() throws Exception {
        this.scheduleConfig.destroy();
        Assert.assertTrue(true);
    }
}