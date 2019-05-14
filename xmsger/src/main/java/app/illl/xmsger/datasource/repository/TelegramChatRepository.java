package app.illl.xmsger.datasource.repository;

import app.illl.xmsger.datasource.entity.TelegramChat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramChatRepository extends CrudRepository<TelegramChat, Integer> {
}