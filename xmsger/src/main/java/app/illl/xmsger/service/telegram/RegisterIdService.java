package app.illl.xmsger.service.telegram;

import app.illl.xmsger.datasource.entity.TelegramChat;
import app.illl.xmsger.datasource.repository.TelegramChatRepository;
import app.illl.xmsger.struct.telegram.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class RegisterIdService {

    @Autowired
    private TelegramChatRepository telegramChatRepository;

    @Async
    @Transactional
    public void registerId(Message message) {
        if (message == null) return;
        if (message.getChat() == null) return;
        Integer chatId = message.getChat().getId();
        String username = message.getChat().getUsername();
        String type = message.getChat().getType();
        Integer messageId = message.getMessageId();
        TelegramChat telegramChat = telegramChatRepository.findById(chatId).orElse(null);
        if (null != telegramChat && telegramChat.getChatId() >= chatId) {
            return;
        }
        if (null == telegramChat) {
            telegramChat = new TelegramChat();
            telegramChat.setChatId(chatId);
            telegramChat.setUsername(username);
            telegramChat.setType(type);
        }
        telegramChat.setMessageId(messageId);
        telegramChatRepository.save(telegramChat);
    }

}
