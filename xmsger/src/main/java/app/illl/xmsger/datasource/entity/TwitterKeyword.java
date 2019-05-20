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

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "twitter_keyword", indexes = {@Index(columnList = "username,keyword")})
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TwitterKeyword implements Serializable {

    public static final String STATUS_IS_ACTIVATED = "ACTIVATED";

    private static final long serialVersionUID = 1339031298792407540L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    private String username;

    private String keyword;

    private String status;

    public boolean isActivated() {
        return StringUtils.equals(this.status, STATUS_IS_ACTIVATED);
    }

}
