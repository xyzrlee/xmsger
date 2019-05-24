package app.illl.xmsger.service.telegram;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class GetUrlServiceImplTest {

    @Autowired
    private GetUrlService getUrlService;

    @Test
    public void getUrl() {
        log.info("url:{}", getUrlService.getUrl("adsf"));
    }
}