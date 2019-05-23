package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.TwitterKeyword;
import app.illl.xmsger.datasource.repository.TwitterKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TwitterKeywordServiceImpl implements TwitterKeywordService {

    private final TwitterKeywordRepository twitterKeywordRepository;

    private static final String COLUMN_USERNAME = "username";

    @Override
    @Cacheable(
            value = "TwitterKeyword",
            key = "#username",
            condition = "#username != null"
    )
    public List<String> getKeywordByUsername(String username) {
        Specification<TwitterKeyword> specification =
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(COLUMN_USERNAME), username);
        List<String> result = new LinkedList<>();
        for (int page = 0; ; page++) {
            Pageable pageable = PageRequest.of(page, 100);
            Page<TwitterKeyword> twitterKeywords = twitterKeywordRepository.findAll(specification, pageable);
            if (twitterKeywords.isEmpty()) {
                break;
            }
            for (TwitterKeyword twitterKeyword : twitterKeywords) {
                if (twitterKeyword.isActivated()) {
                    result.add(twitterKeyword.getKeyword());
                }
            }
        }
        return result;
    }
}
