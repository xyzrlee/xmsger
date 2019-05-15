package app.illl.xmsger.struct;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirDescription implements Serializable {
    private static final long serialVersionUID = 5730585897874242640L;
    @JsonProperty("PM 2.5")
    private BigDecimal fineParticulateMatter;
    @JsonProperty("AQI")
    private Integer aqi;
    private String comment;
}
