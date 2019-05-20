package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.Token;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class TokenServiceImplTest {

    @Autowired
    private TokenService tokenService;

    @Test
    public void findAll() {
        Iterable<Token> tokens = tokenService.findAll();
        log.info("tokens:{}", tokens);
    }
}