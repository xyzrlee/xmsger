package app.illl.xmsger.schedule;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RefreshCacheTest {

    @Autowired
    private RefreshCache refreshCache;

    @Test
    public void defaultRefresh() {
        refreshCache.defaultRefresh();
        Assert.assertTrue(true);
    }
}