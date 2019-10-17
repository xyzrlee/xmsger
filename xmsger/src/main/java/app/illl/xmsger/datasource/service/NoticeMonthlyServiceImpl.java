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
