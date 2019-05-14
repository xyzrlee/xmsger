package app.illl.xmsger.datasource.repository;

import app.illl.xmsger.datasource.entity.TelegramRegisteredChat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRegisteredChatRepository extends CrudRepository<TelegramRegisteredChat, Integer> {
}