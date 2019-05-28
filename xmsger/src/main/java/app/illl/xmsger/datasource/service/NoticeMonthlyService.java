package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.NoticeMonthly;

import java.util.List;

public interface NoticeMonthlyService {
    void save(NoticeMonthly noticeMonthly);

    List<NoticeMonthly> getByChatId(Integer chatId);
}
