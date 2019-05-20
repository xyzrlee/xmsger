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

package app.illl.xmsger.datasource.entity;

import app.illl.xmsger.datasource.converter.AirDescriptionConverter;
import app.illl.xmsger.struct.AirDescription;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "air_data", indexes = {@Index(columnList = "city,message_time")})
@ToString
@Setter
@Getter
@EqualsAndHashCode
public class AirData implements Serializable {

    private static final long serialVersionUID = -1212684681390328438L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    private String city;

    @Column(name = "message_time")
    private ZonedDateTime messageTime;

    @Convert(converter = AirDescriptionConverter.class)
    private AirDescription description;

}
