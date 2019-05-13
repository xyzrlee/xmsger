package app.illl.xmsger.struct.telegram;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message implements Serializable {
    private static final long serialVersionUID = -3139194352255073448L;
    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("from")
    private User from;
    @JsonProperty("date")
    private ZonedDateTime date;
    @JsonProperty("chat")
    private Chat chat;
    @JsonProperty("text")
    private String text;

    @JsonSetter("date")
    public void decodeDate(long epochSeconds) {
        this.date = LocalDateTime.ofEpochSecond(epochSeconds, 0,
                OffsetDateTime.now(ZoneId.systemDefault()).getOffset()).atZone(ZoneId.systemDefault());
    }

    @JsonGetter("date")
    public Long encodeDate() {
        return this.date.toInstant().getEpochSecond();
    }

}
