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
public class TweetProcessorFinderImpl implements TweetProcessorFinder, ApplicationListener<ContextRefreshedEvent> {

    private Map<String, IftttTweetProcessor> tweetProcessorMap;
    private IftttTweetProcessor defaultProcessor;

    public IftttTweetProcessor getProcessor(String username) {
        if (null == tweetProcessorMap) {
            return null;
        }
        return tweetProcessorMap.get(username);
    }

    public IftttTweetProcessor getDefaultProcessor() {
        return defaultProcessor;
    }

    @Override
    @Synchronized
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, IftttTweetProcessor> newServiceMap = new HashMap<>();
        IftttTweetProcessor newDefaultProcessor = null;
        ApplicationContext applicationContext = event.getApplicationContext();
        Map<String, Object> defaultProcessorBeansMap = applicationContext.getBeansWithAnnotation(DefaultTweetProcessor.class);
        if (defaultProcessorBeansMap.size() > 1) {
            throw new IllegalArgumentException("duplicate @DefaultTweetProcessor class found");
        }
        for (Map.Entry<String, Object> entry : defaultProcessorBeansMap.entrySet()) {
            newDefaultProcessor = (IftttTweetProcessor) entry.getValue();
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
        this.tweetProcessorMap = newServiceMap;
        this.defaultProcessor = newDefaultProcessor;
        log.debug("defaultTweetProcessor:{},serviceMap:{}", this.defaultProcessor, this.tweetProcessorMap.keySet());
    }
}
