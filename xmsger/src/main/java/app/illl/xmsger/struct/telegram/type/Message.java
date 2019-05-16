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
