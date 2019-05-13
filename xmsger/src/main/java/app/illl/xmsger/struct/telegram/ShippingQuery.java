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
public class ShippingQuery implements Serializable {
    private static final long serialVersionUID = 2192603196094051667L;
}
