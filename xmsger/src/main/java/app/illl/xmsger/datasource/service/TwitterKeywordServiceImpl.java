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
