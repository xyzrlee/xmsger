package app.illl.xmsger.struct.twitter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private LocalDateTime time;
    private BigDecimal fineParticulateMatter;
    private Integer aqi;
    private String comment;

    public static CGShanghaiAir of(String text) {
        String[] fields = StringUtils.split(text, ';');
        CGShanghaiAir cgShanghaiAir = new CGShanghaiAir();
        cgShanghaiAir.setTime(LocalDateTime.parse(fields[0], FORMATTER));
        cgShanghaiAir.setFineParticulateMatter(new BigDecimal(fields[2].trim()));
        cgShanghaiAir.setAqi(Integer.valueOf(fields[3].trim()));
        cgShanghaiAir.setComment(fields[4].trim());
        return cgShanghaiAir;
    }

}
