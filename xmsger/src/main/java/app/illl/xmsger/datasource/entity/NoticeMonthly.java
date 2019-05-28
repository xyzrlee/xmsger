package app.illl.xmsger.datasource.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "notice_monthly"
        , indexes = {
        @Index(name = "notice_monthly_chat_id", columnList = "chat_id")
}
)
@ToString
@Setter
@Getter
@EqualsAndHashCode
public class NoticeMonthly {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;
    @Column(name = "chat_id")
    private Integer chatId;
    @Column(name = "day_of_month")
    private Integer dayOfMonth;
    private String message;
}
