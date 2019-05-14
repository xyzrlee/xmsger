package app.illl.xmsger.datasource.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "telegram_registered_chat")
@ToString
public class TelegramRegisteredChat implements Serializable {

    private static final long serialVersionUID = -7122130317829720849L;

    @Id
    @Column(name = "chat_id")
    @Getter
    @Setter
    private Integer chatId;

}