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

package app.illl.xmsger.service.twitter;

import app.illl.xmsger.cache.TelegramRegisteredChatCache;
import app.illl.xmsger.datasource.service.TwitterKeywordService;
import app.illl.xmsger.service.telegram.SendMessageService;
import app.illl.xmsger.struct.twitter.IftttTweet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@DefaultTweetProcessor
@Slf4j
@RequiredArgsConstructor
public class DefaultAnalyseProcessorImpl implements DefaultAnalyseProcessor {

    private final TwitterKeywordService twitterKeywordService;
    private final SendMessageService sendMessageService;
    private final TelegramRegisteredChatCache telegramRegisteredChatCache;

    @Override
    public void process(IftttTweet iftttTweet) {
        if (containsKeyword(iftttTweet)) {
            for (Integer chatId : telegramRegisteredChatCache.getChatIds()) {
                sendMessageService.sendPlainText(chatId, iftttTweet.toNoticeMessage(), true);
            }
        }
    }

    private boolean containsKeyword(IftttTweet iftttTweet) {
        Iterable<String> twitterKeywords = twitterKeywordService.getKeywordByUsername(iftttTweet.getUsername());
        for (String keyword : twitterKeywords) {
            if (StringUtils.containsIgnoreCase(iftttTweet.getText(), keyword)) {
                log.trace("hit keyword \"{}\"", keyword);
                return true;
            }
        }
        log.trace("no keyword hit");
        return false;
    }

}
