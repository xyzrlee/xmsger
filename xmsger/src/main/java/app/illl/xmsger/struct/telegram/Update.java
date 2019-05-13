package app.illl.xmsger.struct.telegram;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Update implements Serializable {
    private static final long serialVersionUID = 3590869455751822946L;
    @JsonProperty("update_id")
    private int updateId;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("edited_message")
    private Message editedMessage;
    @JsonProperty("channel_post")
    private Message channelPost;
    @JsonProperty("edited_channel_post")
    private Message editedChannelPost;
    @JsonProperty("inline_query")
    private InlineQuery inlineQuery;
    @JsonProperty("chosen_inline_result")
    private ChosenInlineResult chosenInlineResult;
    @JsonProperty("callback_query")
    private CallbackQuery callbackQuery;
    @JsonProperty("shipping_query")
    private ShippingQuery shippingQuery;
    @JsonProperty("pre_checkout_query")
    private PreCheckoutQuery preCheckoutQuery;
    @JsonProperty("poll")
    private Poll poll;
}
