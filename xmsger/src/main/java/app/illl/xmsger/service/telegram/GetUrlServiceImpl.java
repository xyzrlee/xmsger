/*
 * XMsger
 * Copyright (C) 2020  Ricky Li
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package app.illl.xmsger.service.telegram;

import app.illl.xmsger.cache.TokenCache;
import app.illl.xmsger.constant.Telegram;
import app.illl.xmsger.constant.TokenSites;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUrlServiceImpl implements GetUrlService {

    private final TokenCache tokenCache;

    public String getUrl(String method) {
        return Telegram.BASE_URL + tokenCache.getToken(TokenSites.TELEGRAM).getData() + "/" + method;
    }

}
