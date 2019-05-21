package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.TelegramChat;

public interface TelegramChatService {
    TelegramChat findById(Integer id);
    void save(TelegramChat telegramChat);
}
