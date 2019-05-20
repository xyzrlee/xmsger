package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.TwitterKeyword;
import app.illl.xmsger.datasource.repository.TwitterKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TwitterKeywordServiceImpl implements TwitterKeywordService {

    private final TwitterKeywordRepository twitterKeywordRepository;

    @Override
    @Cacheable(
            value = "TwitterKeyword",
            key = "#username",
            condition = "#username != null"
    )
    public List<String> getKeywordByUsername(String username) {
        Iterable<TwitterKeyword> twitterKeywords = twitterKeywordRepository.findByUsername(username);
        List<String> result = new LinkedList<>();
        for (TwitterKeyword twitterKeyword : twitterKeywords) {
            if (twitterKeyword.isActivated()) {
                result.add(twitterKeyword.getKeyword());
            }
        }
        return result;
    }
}
