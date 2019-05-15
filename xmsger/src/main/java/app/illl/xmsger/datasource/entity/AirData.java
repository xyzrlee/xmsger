package app.illl.xmsger.datasource.entity;

import app.illl.xmsger.datasource.converter.AirDescriptionConverter;
import app.illl.xmsger.struct.AirDescription;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "air_data", indexes = {@Index(columnList = "city,message_time")})
@ToString
@Setter
@Getter
public class AirData implements Serializable {

    private static final long serialVersionUID = -1212684681390328438L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    private String city;

    @Column(name = "message_time")
    private LocalDateTime messageTime;

    @Convert(converter = AirDescriptionConverter.class)
    private AirDescription description;

}
