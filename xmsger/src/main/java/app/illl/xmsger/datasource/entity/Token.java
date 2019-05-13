package app.illl.xmsger.datasource.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "token")
@ToString
public class Token implements Serializable {

    private static final long serialVersionUID = 1757200744714460797L;

    @Id
    @Getter
    @Setter
    private String site;

    @Getter
    @Setter
    private String token;

}