package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.TelegramRegisteredChat;
import app.illl.xmsger.datasource.repository.TelegramRegisteredChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TelegramRegisteredChatServiceImpl implements TelegramRegisteredChatService {

    private final TelegramRegisteredChatRepository telegramRegisteredChatRepository;

    @Override
    public List<Integer> getAllChatId() {
        Iterable<TelegramRegisteredChat> chats = telegramRegisteredChatRepository.findAll();
        List<Integer> result = new LinkedList<>();
        for (TelegramRegisteredChat chat : chats) {
            result.add(chat.getChatId());
        }
        return result;
    }
}
