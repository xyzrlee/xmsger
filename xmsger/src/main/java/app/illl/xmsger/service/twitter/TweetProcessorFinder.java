package app.illl.xmsger.service.twitter;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TweetProcessorFinder implements ApplicationListener<ContextRefreshedEvent> {

    private static Map<String, IftttTweetProcessor> TWEET_PROCESSOR_MAP;
    private static IftttTweetProcessor DEFAULT_PROCESSOR;

    public static IftttTweetProcessor getProcessor(String username) {
        if (null == TWEET_PROCESSOR_MAP) {
            return null;
        }
        return TWEET_PROCESSOR_MAP.get(username);
    }

    public static IftttTweetProcessor getDefaultProcessor() {
        return DEFAULT_PROCESSOR;
    }

    @Override
    @Synchronized
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, IftttTweetProcessor> newServiceMap = new HashMap<>();
        IftttTweetProcessor defaultProcessor = null;
        ApplicationContext applicationContext = event.getApplicationContext();
        Map<String, Object> defaultProcessorBeansMap = applicationContext.getBeansWithAnnotation(DefaultTweetProcessor.class);
        if (defaultProcessorBeansMap.size() > 1) {
            throw new IllegalArgumentException("duplicate @DefaultTweetProcessor class found");
        }
        for (Map.Entry<String, Object> entry : defaultProcessorBeansMap.entrySet()) {
            defaultProcessor = (IftttTweetProcessor) entry.getValue();
        }
        Map<String, Object> beansMap = applicationContext.getBeansWithAnnotation(TweetProcessor.class);
        for (Map.Entry<String, Object> entry : beansMap.entrySet()) {
            Class targetClass = AopUtils.getTargetClass(entry.getValue());
            TweetProcessor tweetProcessor = AnnotationUtils.findAnnotation(targetClass, TweetProcessor.class);
            if (null != tweetProcessor) {
                String[] usernames = tweetProcessor.username();
                if (entry.getValue() instanceof IftttTweetProcessor) {
                    for (String username : usernames) {
                        newServiceMap.put(username, (IftttTweetProcessor) entry.getValue());
                    }
                }
            }
        }
        TWEET_PROCESSOR_MAP = newServiceMap;
        DEFAULT_PROCESSOR = defaultProcessor;
        log.debug("defaultTweetProcessor:{},serviceMap:{}", DEFAULT_PROCESSOR, TWEET_PROCESSOR_MAP.keySet());
    }
}
