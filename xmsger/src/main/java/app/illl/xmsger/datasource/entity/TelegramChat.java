package app.illl.xmsger.datasource.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "telegram_chat", indexes = {@Index(columnList = "username")})
@ToString
public class TelegramChat implements Serializable {

    private static final long serialVersionUID = -407528148302585947L;

    @Id
    @Column(name = "chat_id")
    @Getter
    @Setter
    private Integer chatId;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String type;

    @Column(name = "message_id")
    @Getter
    @Setter
    private Integer messageId;

}