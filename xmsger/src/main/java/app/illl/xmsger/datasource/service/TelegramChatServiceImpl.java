package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.TelegramChat;
import app.illl.xmsger.datasource.repository.TelegramChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class TelegramChatServiceImpl implements TelegramChatService {

    private final TelegramChatRepository telegramChatRepository;

    @Override
    public TelegramChat findById(Integer id) {
        return this.telegramChatRepository.findById(id).orElse(null);
    }

    @Override
    public void save(TelegramChat telegramChat) {
        this.telegramChatRepository.save(telegramChat);
    }
}
