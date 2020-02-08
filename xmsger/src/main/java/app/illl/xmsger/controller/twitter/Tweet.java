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

package app.illl.xmsger.controller.twitter;

import app.illl.xmsger.constant.Twitter;
import app.illl.xmsger.service.twitter.IftttTweetProcessor;
import app.illl.xmsger.service.twitter.TweetProcessorFinder;
import app.illl.xmsger.struct.twitter.IftttTweet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Twitter.PATH_TWEET)
@Slf4j
@RequiredArgsConstructor
public class Tweet {

    private final TweetProcessorFinder tweetProcessorFinder;

    @PostMapping
    public void post(@RequestBody IftttTweet iftttTweet) {
        IftttTweetProcessor processor = tweetProcessorFinder.getProcessor(iftttTweet.getUsername());
        if (null == processor) {
            processor = tweetProcessorFinder.getDefaultProcessor();
        }
        if (null == processor) {
            log.warn("no processor, username:{}", iftttTweet.getUsername());
            return;
        }
        processor.process(iftttTweet);
    }

}
