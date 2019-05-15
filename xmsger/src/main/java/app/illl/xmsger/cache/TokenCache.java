/*
 *     XMsger
 *     Copyright (C) 2019  Ricky Li
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package app.illl.xmsger.cache;

import app.illl.xmsger.datasource.entity.Token;
import app.illl.xmsger.datasource.repository.TokenRepository;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TokenCache implements InitializingBean {

    @Autowired
    private TokenRepository tokenRepository;
    private Map<String, Token> dataMap;

    public Token getToken(String site) {
        return dataMap.get(site);
    }

    @Scheduled(cron = "0 */10 * * * *")
    @Synchronized
    private void refresh() {
        Map<String, Token> tempMap = new HashMap<>();
        Iterable<Token> tokens = tokenRepository.findAll();
        for (Token token : tokens) {
            tempMap.put(token.getSite(), token);
        }
        this.dataMap = tempMap;
        if (log.isDebugEnabled()) {
            log.debug("tokenCache:{}", dataMap.size());
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void afterPropertiesSet() throws Exception {
        this.refresh();
    }
}
