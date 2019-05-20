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

package app.illl.xmsger.controller.twitter;

import app.illl.xmsger.constant.TweetProcessorId;
import app.illl.xmsger.constant.Twitter;
import app.illl.xmsger.service.twitter.IftttTweetProcessor;
import app.illl.xmsger.service.twitter.TweetProcessor;
import app.illl.xmsger.struct.twitter.IftttTweet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Twitter.PATH_TWEET)
@Slf4j
@RequiredArgsConstructor
public class Tweet implements ApplicationListener<ContextRefreshedEvent> {

    private Map<String, IftttTweetProcessor> serviceMap;

    @PostMapping
    public void post(@RequestBody IftttTweet iftttTweet) {
        IftttTweetProcessor processor = serviceMap.get(iftttTweet.getUsername());
        if (null == processor) {
            processor = this.serviceMap.get(TweetProcessorId.DEFAULT_ANALYSE);
        }
        if (null == processor) {
            log.warn("no processor, username:{}", iftttTweet.getUsername());
            return;
        }
        processor.process(iftttTweet);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, IftttTweetProcessor> newServiceMap = new HashMap<>();
        ApplicationContext applicationContext = event.getApplicationContext();
        Map<String, Object> beansMap = applicationContext.getBeansWithAnnotation(TweetProcessor.class);
        for (Map.Entry<String, Object> entry : beansMap.entrySet()) {
            TweetProcessor tweetProcessor = AnnotationUtils.findAnnotation(AopUtils.getTargetClass(entry.getValue()), TweetProcessor.class);
            if (null == tweetProcessor) continue;
            String[] usernames = tweetProcessor.username();
            if (entry.getValue() instanceof IftttTweetProcessor) {
                for (String username : usernames) {
                    newServiceMap.put(username, (IftttTweetProcessor) entry.getValue());
                }
            }
        }
        this.serviceMap = newServiceMap;
        log.debug("serviceMap:{}", this.serviceMap.keySet());
    }
}
