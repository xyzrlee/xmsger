package app.illl.xmsger.service.telegram;

import app.illl.xmsger.cache.TokenCache;
import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.constant.TokenSites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUrlService {

    @Autowired
    TokenCache tokenCache;

    String getUrl(String method) {
        return Telegram.BASE_URL + tokenCache.getToken(TokenSites.TELEGRAM).getToken() + "/" + method;
    }

}
