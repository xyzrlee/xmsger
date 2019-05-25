package app.illl.xmsger.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AsyncConfigTest {

    private AsyncConfig asyncConfig;

    @Before
    public void beforeTest() throws Exception {
        this.asyncConfig = new AsyncConfig();
        this.asyncConfig.afterPropertiesSet();
    }

    @Test
    public void destroy() throws Exception {
        this.asyncConfig.destroy();
        Assert.assertTrue(true);
    }

}