package app.illl.xmsger.struct.telegram;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Poll implements Serializable {
    private static final long serialVersionUID = -8434309432192999035L;
}
