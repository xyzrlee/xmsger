/*
 *     XMsger
 *     Copyright (C) 2019  Ricky Li
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package app.illl.xmsger.struct.telegram.type;

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
