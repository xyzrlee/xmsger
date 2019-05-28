package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.NoticeMonthly;
import app.illl.xmsger.datasource.repository.NoticeMonthlyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoticeMonthlyServiceImpl implements NoticeMonthlyService {

    private static final String COLUMN_CHAT_ID = "chatId";
    private final NoticeMonthlyRepository noticeMonthlyRepository;

    @Override
    public void save(NoticeMonthly noticeMonthly) {
        noticeMonthlyRepository.save(noticeMonthly);
    }

    @Override
    public List<NoticeMonthly> getByChatId(Integer chatId) {
        Specification<NoticeMonthly> specification =
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(COLUMN_CHAT_ID), chatId);
        return noticeMonthlyRepository.findAll(specification);
    }
}
