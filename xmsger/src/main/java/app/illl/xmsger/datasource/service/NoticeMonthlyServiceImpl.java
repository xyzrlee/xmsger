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

import app.illl.xmsger.datasource.entity.NoticeMonthly;
import app.illl.xmsger.datasource.repository.NoticeMonthlyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NoticeMonthlyServiceImpl implements NoticeMonthlyService {

    private static final String COLUMN_CHAT_ID = "chatId";
    private static final String COLUMN_DAY_OF_MONTH = "dayOfMonth";
    private final NoticeMonthlyRepository noticeMonthlyRepository;

    @Override
    public void save(NoticeMonthly noticeMonthly) {
        noticeMonthlyRepository.save(noticeMonthly);
    }

    @Override
    public List<NoticeMonthly> getByChatIdAndDay(Integer chatId, List<Integer> days) {
        Specification<NoticeMonthly> specification =
                (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get(COLUMN_CHAT_ID), chatId));
                    predicates.add(root.get(COLUMN_DAY_OF_MONTH).in(days));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                };
        return noticeMonthlyRepository.findAll(specification);
    }
}
