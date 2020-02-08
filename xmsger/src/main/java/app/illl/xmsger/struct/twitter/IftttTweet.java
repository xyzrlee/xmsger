/*
 * XMsger
 * Copyright (C) 2020  Ricky Li
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package app.illl.xmsger.struct.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IftttTweet {

    private static final DateTimeFormatter FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendText(ChronoField.MONTH_OF_YEAR)
                    .appendLiteral(' ')
                    .appendValue(ChronoField.DAY_OF_MONTH)
                    .appendLiteral(", ")
                    .appendValue(ChronoField.YEAR)
                    .appendLiteral(" at ")
                    .appendValue(ChronoField.CLOCK_HOUR_OF_AMPM, 2)
                    .appendLiteral(':')
                    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                    .appendText(ChronoField.AMPM_OF_DAY, TextStyle.SHORT)
                    .toFormatter(Locale.ENGLISH);

    private String username;
    private String text;
    private String link;
    private ZonedDateTime created;

    @JsonSetter("created")
    private void convertCreated(String createdString) {
        this.created = ZonedDateTime.of(LocalDateTime.parse(createdString, FORMATTER), ZoneId.systemDefault());
    }

    public String toNoticeMessage() {
        return String.format(
                "%s tweeted at %s%n%s",
                this.username, this.created, this.link
        );
    }

}
