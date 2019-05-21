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

package app.illl.xmsger.struct.twitter;

import app.illl.xmsger.constant.ZoneIds;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

@ToString
@Getter
@Setter
public class CGShanghaiAir implements Serializable {

    private static final long serialVersionUID = 4590068789519494506L;

    private CGShanghaiAir() {
    }

    private static final DateTimeFormatter FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                    .appendLiteral('-')
                    .appendValue(ChronoField.DAY_OF_MONTH, 2)
                    .appendLiteral('-')
                    .appendValue(ChronoField.YEAR)
                    .appendLiteral(" ")
                    .appendValue(ChronoField.HOUR_OF_DAY, 2)
                    .appendLiteral(':')
                    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                    .toFormatter(Locale.ENGLISH);

    private ZonedDateTime time;
    private BigDecimal fineParticulateMatter;
    private Integer aqi;
    private String comment;

    public static CGShanghaiAir of(String text) {
        String[] fields = StringUtils.split(text, ';');
        CGShanghaiAir cgShanghaiAir = new CGShanghaiAir();
        cgShanghaiAir.setTime(ZonedDateTime.of(LocalDateTime.parse(fields[0], FORMATTER), ZoneIds.ASIA_SHANGHAI));
        cgShanghaiAir.setFineParticulateMatter(new BigDecimal(fields[2].trim()));
        cgShanghaiAir.setAqi(Integer.valueOf(fields[3].trim()));
        cgShanghaiAir.setComment(fields[4].trim());
        return cgShanghaiAir;
    }

    public String toDetailMessage() {
        return String.format(
                "PM 2.5: %s, AQI: %s, %s",
                getFineParticulateMatter(), getAqi(), getComment()
        );
    }

}
