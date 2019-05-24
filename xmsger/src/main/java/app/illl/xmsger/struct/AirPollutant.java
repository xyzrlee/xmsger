package app.illl.xmsger.struct;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirPollutant implements Serializable {

    private static final long serialVersionUID = -8222388033472983824L;

    private String type;
    private BigDecimal concentration;

}
