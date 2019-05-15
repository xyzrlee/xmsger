package app.illl.xmsger.struct.twitter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IftttTweet {

    private static final DateTimeFormatter FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendText(ChronoField.MONTH_OF_YEAR, TextStyle.SHORT)
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
    private LocalDateTime created;

    @JsonSetter("created")
    private void convertCreated(String createdString) {
        this.created = LocalDateTime.parse(createdString, FORMATTER);
    }

}
