package app.illl.xmsger.struct.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class SendMessage<T> implements Serializable {
    private static final long serialVersionUID = 5952926161239436547L;
    @JsonProperty("chat_id")
    private Integer chatId;
    @JsonProperty("text")
    private T text;
    @JsonProperty("parse_mode")
    private String parseMode;
    @JsonProperty("disable_web_page_preview")
    private Boolean disableWebPagePreview;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;
}
