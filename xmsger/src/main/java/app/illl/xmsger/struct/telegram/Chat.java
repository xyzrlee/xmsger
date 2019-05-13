package app.illl.xmsger.struct.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class Chat implements Serializable {
    private static final long serialVersionUID = 319460851096239790L;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("username")
    private String username;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
}
