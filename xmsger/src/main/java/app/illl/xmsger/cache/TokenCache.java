package app.illl.xmsger.cache;

import app.illl.xmsger.datasource.entity.Token;
import app.illl.xmsger.datasource.service.TokenService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class TokenCache implements InitializingBean {

    @Autowired
    private TokenService tokenService;
    private Map<String, Token> dataMap;

    public Token getToken(String site) {
        return dataMap.get(site);
    }

    @Scheduled(cron = "0 */10 * * * *")
    @Synchronized
    private void refresh() {
        Map<String, Token> tempMap = new HashMap<>();
        List<Token> tokens = tokenService.findAll();
        for (Token token : tokens) {
            tempMap.put(token.getSite(), token);
        }
        this.dataMap = tempMap;
        if (log.isDebugEnabled()) {
            log.debug("dataMap:{}", dataMap.keySet());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.refresh();
    }
}
